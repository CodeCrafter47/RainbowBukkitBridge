package PluginBukkitBridge.block;

import PluginBukkitBridge.Util;
import PluginReference.MC_Sign;
import org.bukkit.block.Sign;

import java.util.List;

/**
 * Created by florian on 14.10.14.
 */
public class FakeSign extends FakeBlockState implements Sign{
    public FakeSign(FakeBlock arg) {
        super(arg);
    }

    private MC_Sign getSign(){
        return blk.world.getSignAt(Util.getLocation(blk.getLocation()));
    }

    @Override
    public String[] getLines() {
        return new String[]{getLine(0), getLine(1), getLine(2), getLine(3)};
    }

    @Override
    public String getLine(int i) throws IndexOutOfBoundsException {
        return getSign().getLines().get(i);
    }

    @Override
    public void setLine(int i, String s) throws IndexOutOfBoundsException {
        List<String> lines = getSign().getLines();
        lines.remove(i);
        lines.add(i, s);
        getSign().setLines(lines);
    }
}
