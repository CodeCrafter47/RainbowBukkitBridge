package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;

/**
 * Created by florian on 12.10.14.
 */
public class FakeFallingBlock extends FakeEntity implements FallingBlock {
    public FakeFallingBlock(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public Material getMaterial() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public int getBlockId() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public byte getBlockData() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public boolean getDropItem() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setDropItem(boolean b) {
        MyPlugin.fixme("stub method");
    }
}
