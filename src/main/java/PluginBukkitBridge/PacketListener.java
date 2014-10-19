package PluginBukkitBridge;

import PluginReference.MC_EventInfo;
import PluginReference.MC_Player;
import PluginReference.MC_PlayerPacketListener;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;

import java.nio.ByteBuffer;

/**
 * Created by florian on 17.10.14.
 */
public class PacketListener implements MC_PlayerPacketListener {
    @Override
    public byte[] handleRawPacket(MC_Player player, int i, byte[] bytes, String s, MC_EventInfo mc_eventInfo) {
        if(i == 0x3F) {
            if(MyPlugin.DebugMode)System.out.println("Packet received");
            ByteBuffer buf = ByteBuffer.wrap(bytes);
            String tag = readString(buf);
            byte[] data = new byte[buf.remaining()];
            buf.get(data);
            MyPlugin.instance.handlePluginMessage(player, tag, data, mc_eventInfo);
        }
        return bytes;
    }
    public static String readString(ByteBuffer buf)
    {
        int len = readVarInt( buf );
        Preconditions.checkArgument(len <= Short.MAX_VALUE, "Cannot receive string longer than Short.MAX_VALUE (got %s characters)", len);
        byte[] b = new byte[ len ];
        buf.get(b);
        return new String( b, Charsets.UTF_8 );
    }
    public static int readVarInt(ByteBuffer input)
    {
        return readVarInt( input, 5 );
    }
    public static int readVarInt(ByteBuffer input, int maxBytes)
    {
        int out = 0;
        int bytes = 0;
        byte in;
        while ( true )
        {
            in = input.get();
            out |= ( in & 0x7F ) << ( bytes++ * 7 );
            if ( bytes > maxBytes )
            {
                throw new RuntimeException( "VarInt too big" );
            }
            if ( ( in & 0x80 ) != 0x80 )
            {
                break;
            }
        }
        return out;
    }

}
