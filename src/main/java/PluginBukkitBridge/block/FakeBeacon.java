package PluginBukkitBridge.block;

import PluginBukkitBridge.inventory.FakeBeaconInventory;
import org.bukkit.block.Beacon;
import org.bukkit.inventory.Inventory;

public class FakeBeacon extends FakeBlockState implements Beacon {
    public FakeBeacon(FakeBlock arg) {
        super(arg);
    }

    @Override
    public Inventory getInventory() {
        return new FakeBeaconInventory();
    }
}
