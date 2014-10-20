package PluginBukkitBridge.block;

import PluginBukkitBridge.inventory.UndefinedFakeInventory;
import org.bukkit.block.Hopper;
import org.bukkit.inventory.Inventory;

/**
 * Created by florian on 14.10.14.
 */
public class FakeHopper extends FakeBlockState implements Hopper {
    public FakeHopper(FakeBlock arg) {
        super(arg);
    }

    @Override
    public Inventory getInventory() {
        return new UndefinedFakeInventory();
    }
}
