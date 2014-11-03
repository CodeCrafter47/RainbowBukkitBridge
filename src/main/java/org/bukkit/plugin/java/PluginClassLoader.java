package org.bukkit.plugin.java;

import org.apache.commons.lang.Validate;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.PluginDescriptionFile;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.commons.RemappingClassAdapter;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.Permissions;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A ClassLoader for plugins, to allow shared classes across multiple plugins
 */
final class PluginClassLoader extends URLClassLoader {
    private final JavaPluginLoader loader;
    private final Map<String, Class<?>> classes = new java.util.concurrent.ConcurrentHashMap<String, Class<?>>(); // Spigot
    private final PluginDescriptionFile description;
    private final File dataFolder;
    private final File file;
    final JavaPlugin plugin;
    private JavaPlugin pluginInit;
    private IllegalStateException pluginState;
    RelocatorRemapper relocatorRemapper = new RelocatorRemapper();

    // Spigot Start
    static
    {
        try
        {
            java.lang.reflect.Method method = ClassLoader.class.getDeclaredMethod( "registerAsParallelCapable" );
            if ( method != null )
            {
                boolean oldAccessible = method.isAccessible();
                method.setAccessible( true );
                method.invoke( null );
                method.setAccessible( oldAccessible );
                org.bukkit.Bukkit.getLogger().log( java.util.logging.Level.INFO, "Set PluginClassLoader as parallel capable" );
            }
        } catch ( NoSuchMethodException ex )
        {
            // Ignore
        } catch ( Exception ex )
        {
            org.bukkit.Bukkit.getLogger().log( java.util.logging.Level.WARNING, "Error setting PluginClassLoader as parallel capable", ex );
        }
    }
    // Spigot End
    
    PluginClassLoader(final JavaPluginLoader loader, final ClassLoader parent, final PluginDescriptionFile description, final File dataFolder, final File file) throws InvalidPluginException, MalformedURLException {
        super(new URL[] {file.toURI().toURL()}, parent);
        Validate.notNull(loader, "Loader cannot be null");

        this.loader = loader;
        this.description = description;
        this.dataFolder = dataFolder;
        this.file = file;

        try {
            Class<?> jarClass;
            try {
                jarClass = Class.forName(description.getMain(), true, this);
            } catch (ClassNotFoundException ex) {
                throw new InvalidPluginException("Cannot find main class `" + description.getMain() + "'", ex);
            }

            Class<? extends JavaPlugin> pluginClass;
            try {
                pluginClass = jarClass.asSubclass(JavaPlugin.class);
            } catch (ClassCastException ex) {
                throw new InvalidPluginException("main class `" + description.getMain() + "' does not extend JavaPlugin", ex);
            }

            plugin = pluginClass.newInstance();
        } catch (IllegalAccessException ex) {
            throw new InvalidPluginException("No public constructor", ex);
        } catch (InstantiationException ex) {
            throw new InvalidPluginException("Abnormal plugin type", ex);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return findClass(name, true);
    }

    Class<?> findClass(String name, boolean checkGlobal) throws ClassNotFoundException {
        if (name.startsWith("org.bukkit.") || name.startsWith("net.minecraft.")) {
            throw new ClassNotFoundException(name);
        }
        Class<?> result = classes.get(name);

        if (result == null) {
            if (checkGlobal) {
                result = loader.getClassByName(name);
            }

            if (result == null) {
                result = loadAndRemapClass(name);

                if (result != null) {
                    loader.setClass(name, result);
                }
            }

            if(result == null)throw new ClassNotFoundException(name);

            classes.put(name, result);
        }

        if(result == null)throw new ClassNotFoundException(name);

        return result;
    }

    private Class<?> loadAndRemapClass(String name) throws ClassNotFoundException {
        try {
            name = name.replace('.', '/');
            InputStream inputStream = getResourceAsStream(name + ".class");
            if(inputStream == null)
                throw new ClassNotFoundException(name + " not found in " + this);
            ClassReader classReader = new ClassReader(inputStream);
            ClassWriter classWriter = new ClassWriter(0);

            final String pkg = name.substring( 0, name.lastIndexOf( '/' ) + 1 );
            ClassVisitor cv = new RemappingClassAdapter( classWriter, relocatorRemapper )
            {
                @Override
                public void visitSource( final String source, final String debug )
                {
                    if ( source == null )
                    {
                        super.visitSource( source, debug );
                    }
                    else
                    {
                        final String fqSource = pkg + source;
                        final String mappedSource = remapper.map( fqSource );
                        final String filename = mappedSource.substring( mappedSource.lastIndexOf( '/' ) + 1 );
                        super.visitSource( filename, debug );
                    }
                }
            };
            classReader.accept( cv, ClassReader.EXPAND_FRAMES );
            byte[] b = classWriter.toByteArray();
            String packageName = null;
            int lastDot = name.lastIndexOf('/');
            if (lastDot != -1)
                packageName = name.substring(0, lastDot).replace('/', '.');
            if(packageName != null && getPackage(packageName) == null)
                definePackage(packageName, null, null, null, null, null, null, null);
            return defineClass(name.replace('/', '.'), b, 0, b.length,
                    getDomain(name));
        }catch (Exception ex){
            if(!(ex instanceof ClassNotFoundException))ex.printStackTrace();
            throw new ClassNotFoundException(name + " not found in " + this);
        }
    }

    private final ProtectionDomain getDomain(String name) {
        CodeSource code = new CodeSource(getResource(name + ".class"), (Certificate[]) null);
        return new ProtectionDomain(code, getPermissions());
    }

    private final Permissions getPermissions() {
        Permissions permissions = new Permissions();
        permissions.add(new AllPermission());
        return permissions;
    }

    Set<String> getClasses() {
        return classes.keySet();
    }

    synchronized void initialize(JavaPlugin javaPlugin) {
        Validate.notNull(javaPlugin, "Initializing plugin cannot be null");
        Validate.isTrue(javaPlugin.getClass().getClassLoader() == this, "Cannot initialize plugin outside of this class loader");
        if (this.plugin != null || this.pluginInit != null) {
            throw new IllegalArgumentException("Plugin already initialized!", pluginState);
        }

        pluginState = new IllegalStateException("Initial initialization");
        this.pluginInit = javaPlugin;

        javaPlugin.init(loader, loader.server, description, dataFolder, file, this);
    }

    class RelocatorRemapper
            extends Remapper
    {
        private final Pattern classPattern = Pattern.compile("(\\[*)?L(.+);");
        public String map( String name )
        {
            String value = name;
            String prefix = "";
            String suffix = "";
            Matcher m = classPattern.matcher( name );
            if ( m.matches() )
            {
                prefix = m.group( 1 ) + "L";
                suffix = ";";
                name = m.group( 2 );
            }
            // *MAGIC*
            if(name.contains("google") && !name.contains("gson") && !name.contains("voxelsniper")){
                value = prefix + "remapped/" + name + suffix;
            }
            return value;
        }
    }

}
