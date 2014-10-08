package PluginBukkitBridge;

import java.text.SimpleDateFormat;
import java.util.logging.*;


class MyLogFormatter extends Formatter {
 
    public String format(LogRecord record) {
    	return "[BridgeConsole] " + formatMessage(record) + "\n";
    }
 
    public String getHead(Handler h) {
        return super.getHead(h);
    }
 
    public String getTail(Handler h) {
        return super.getTail(h);
    }
}
