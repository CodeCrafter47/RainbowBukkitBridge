package PluginBukkitBridge.block;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.inventory.FakeChestInventory;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;

/**
 * Created by florian on 14.10.14.
 */
public class FakeChest extends FakeBlockState implements Chest{
    public FakeChest(FakeBlock arg) {
        super(arg);
    }

    @Override
    public Inventory getBlockInventory() {
        return new FakeChestInventory((FakeBlock) getBlock());
    }

    @Override
    public Inventory getInventory() {
        return new FakeChestInventory((FakeBlock) getBlock());
    }

    @Override
    public boolean isLocked() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public String getLock() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setLock(String s) {
        MyPlugin.fixme();
    }

    @Override
    public String getCustomName() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setCustomName(String arg0) {
        MyPlugin.fixme(); 
    }
}
