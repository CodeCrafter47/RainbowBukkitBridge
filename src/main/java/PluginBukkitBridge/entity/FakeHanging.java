package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Hanging;

/**
 * Created by florian on 12.10.14.
 */
public class FakeHanging extends FakeEntity implements Hanging{
    public FakeHanging(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public boolean setFacingDirection(BlockFace blockFace, boolean b) {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public BlockFace getAttachedFace() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setFacingDirection(BlockFace blockFace) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public BlockFace getFacing() {
        MyPlugin.fixme("stub method");
        return null;
    }
}
