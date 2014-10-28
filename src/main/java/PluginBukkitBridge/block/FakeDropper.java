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
}
