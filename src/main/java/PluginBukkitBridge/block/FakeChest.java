package PluginBukkitBridge.block;

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
        return new FakeChestInventory(blk);
    }

    @Override
    public Inventory getInventory() {
        return new FakeChestInventory(blk);
    }
}
