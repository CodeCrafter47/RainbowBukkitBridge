package PluginBukkitBridge.block;

import PluginBukkitBridge.MyPlugin;
import org.bukkit.block.Beacon;
import org.bukkit.inventory.Inventory;

/**
 * Created by florian on 14.10.14.
 */
public class FakeBeacon extends FakeBlockState implements Beacon {
    public FakeBeacon(FakeBlock arg) {
        super(arg);
    }

    @Override
    public Inventory getInventory() {
        MyPlugin.fixme();
        return null;
    }
}
