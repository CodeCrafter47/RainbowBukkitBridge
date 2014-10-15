package PluginBukkitBridge.logging;

import org.bukkit.plugin.Plugin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by florian on 14.10.14.
 */
public class PluginLogger extends org.bukkit.plugin.PluginLogger {
    String pluginName;
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    public PluginLogger(Plugin context) {
        super(context);
        String prefix = context.getDescription().getPrefix();
        pluginName = prefix != null ? "[" + prefix + "] " : "[" + context.getDescription().getName() + "] ";
        setParent(context.getServer().getLogger());
        setLevel(Level.ALL);
    }

    @Override
    public void log(LogRecord record) {
        System.out.println("[" + format.format(Calendar.getInstance().getTime()) + "] [" + record.getLevel().getName() + "][BukkitBridge] " + pluginName + record.getMessage());
        if(record.getThrown() != null){
            record.getThrown().printStackTrace();
        }
    }
}
