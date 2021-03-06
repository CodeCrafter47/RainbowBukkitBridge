package PluginBukkitBridge.block;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.inventory.FakeDropperInventory;
import org.bukkit.block.Dropper;
import org.bukkit.inventory.Inventory;

/**
 * Created by florian on 14.10.14.
 */
public class FakeDropper extends FakeContainerBlockState implements Dropper {
    public FakeDropper(FakeBlock arg) {
        super(arg);
    }

    @Override
    public void drop() {
        MyPlugin.fixme();
    }

    @Override
    public Inventory getInventory() {
        return new FakeDropperInventory(this, 16);
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

    @Override
    public Inventory getSnapshotInventory() {
        MyPlugin.fixme();
        return null;
    }
}
