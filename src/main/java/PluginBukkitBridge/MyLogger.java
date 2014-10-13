package PluginBukkitBridge;

import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Created by florian on 13.10.14.
 */
public class MyLogger extends Logger {
    protected MyLogger(String name, String resourceBundleName) {
        super(name, resourceBundleName);
    }

    @Override
    public void log(LogRecord record) {
        System.out.println("[BukkitBridge] " + record.getLevel().getName() + ": " + record.getMessage());
        if(record.getThrown() != null){
            record.getThrown().printStackTrace();
        }
    }
}
