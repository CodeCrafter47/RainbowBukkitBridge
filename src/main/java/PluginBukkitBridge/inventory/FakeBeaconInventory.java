package PluginBukkitBridge.inventory;

import PluginBukkitBridge.block.FakeContainerBlockState;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.BeaconInventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by florian on 20.10.14.
 */
public class FakeBeaconInventory extends FakeContainerInventory implements BeaconInventory {

    public FakeBeaconInventory(FakeContainerBlockState blockState, int maxStackSize) {
        super(blockState, maxStackSize);
    }

    @Override
    public void setItem(ItemStack itemStack) {
        setItem(0, itemStack);
    }

    @Override
    public ItemStack getItem() {
        return getItem(0);
    }

    @Override
    public InventoryType getType() {
        return InventoryType.BEACON;
    }
}
