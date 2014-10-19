package PluginBukkitBridge.logging;

import org.apache.logging.log4j.LogManager;

import java.text.SimpleDateFormat;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by florian on 14.10.14.
 */
public class MyLogHandler extends Handler{
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    @Override
    public void publish(LogRecord record) {
        /*
        System.out.println("[" + format.format(Calendar.getInstance().getTime()) + "] [" + record.getLevel().getName() + "][BukkitBridge] " + record.getMessage());
        if(record.getThrown() != null){
            record.getThrown().printStackTrace();
        }
        */
        if(record.getLevel() == Level.SEVERE){
            LogManager.getLogger("BukkitBridge").error(record.getMessage(), record.getThrown());
        }
        else if(record.getLevel() == Level.WARNING){
            LogManager.getLogger("BukkitBridge").warn(record.getMessage(), record.getThrown());
        }
        else{
            LogManager.getLogger("BukkitBridge").info(record.getMessage(), record.getThrown());
        }
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }
}
