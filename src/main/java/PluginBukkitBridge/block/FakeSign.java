package PluginBukkitBridge.block;

import PluginBukkitBridge.Util;
import org.bukkit.block.Sign;

import java.util.List;

/**
 * Created by florian on 14.10.14.
 */
public class FakeSign extends FakeBlockState implements Sign{

    List<String> lines;

    public FakeSign(FakeBlock blk) {
        super(blk);
        lines = blk.world.getSignAt(Util.getLocation(blk.getLocation())).getLines();
    }

    @Override
    public String[] getLines() {
        return new String[]{getLine(0), getLine(1), getLine(2), getLine(3)};
    }

    @Override
    public String getLine(int i) throws IndexOutOfBoundsException {
        return lines.get(i);
    }

    @Override
    public void setLine(int i, String s) throws IndexOutOfBoundsException {
        lines.remove(i);
        lines.add(i, s);
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        if (super.update(force, applyPhysics)){
            ((FakeBlock)getBlock()).world.getSignAt(Util.getLocation(getLocation())).setLines(lines);
            return true;
        }
        return false;
    }
}
