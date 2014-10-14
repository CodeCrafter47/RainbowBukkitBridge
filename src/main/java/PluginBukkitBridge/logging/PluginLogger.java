package PluginBukkitBridge.logging;

import org.bukkit.plugin.Plugin;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by florian on 14.10.14.
 */
public class PluginLogger extends org.bukkit.plugin.PluginLogger {
    String pluginName;

    public PluginLogger(Plugin context) {
        super(context);
        String prefix = context.getDescription().getPrefix();
        pluginName = prefix != null ? "[" + prefix + "] " : "[" + context.getDescription().getName() + "] ";
        setParent(context.getServer().getLogger());
        setLevel(Level.ALL);
    }

    @Override
    public void log(LogRecord record) {
        System.out.println("[" + record.getLevel().getName() + "][BukkitBridge]" + pluginName + record.getMessage());
        if(record.getThrown() != null){
            record.getThrown().printStackTrace();
        }
    }
}
