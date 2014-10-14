package PluginBukkitBridge.logging;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by florian on 14.10.14.
 */
public class MyLogHandler extends Handler{
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    @Override
    public void publish(LogRecord record) {
        System.out.println("[" + format.format(Calendar.getInstance().getTime()) + "] [" + record.getLevel().getName() + "][BukkitBridge] " + record.getMessage());
        if(record.getThrown() != null){
            record.getThrown().printStackTrace();
        }
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }
}
