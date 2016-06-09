package PluginBukkitBridge.block;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.Util;
import PluginBukkitBridge.inventory.FakeFurnaceInventory;
import PluginReference.MC_Container;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.FurnaceInventory;

/**
 * Created by florian on 14.10.14.
 */
public class FakeFurnace extends FakeContainerBlockState implements Furnace {

    public FakeFurnace(FakeBlock arg) {
        super(arg);
    }

    @Override
    public short getBurnTime() {
        MyPlugin.fixme();
        return (short) 0;
    }

    @Override
    public void setBurnTime(short i) {
        MyPlugin.fixme();
    }

    @Override
    public short getCookTime() {
        MyPlugin.fixme();
        return (short) 0;
    }

    @Override
    public void setCookTime(short i) {
        MyPlugin.fixme();
    }

    @Override
    public FurnaceInventory getInventory() {
        return new FakeFurnaceInventory(this, 64);
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        if(super.update(force, applyPhysics)){
            return true;
        }
        return false;
    }
}
