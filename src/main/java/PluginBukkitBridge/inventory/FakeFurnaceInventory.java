package PluginBukkitBridge.inventory;

import PluginBukkitBridge.block.FakeContainerBlockState;
import org.bukkit.block.Furnace;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;


public class FakeFurnaceInventory extends FakeContainerInventory implements FurnaceInventory{

    public FakeFurnaceInventory(FakeContainerBlockState blockState, int maxStackSize) {
        super(blockState, maxStackSize);
    }

    public ItemStack getResult() {
        return getItem(2);
    }

    public ItemStack getFuel() {
        return getItem(1);
    }

    public ItemStack getSmelting() {
        return getItem(0);
    }

    public void setFuel(ItemStack stack) {
        setItem(1,stack);
    }

    public void setResult(ItemStack stack) {
        setItem(2,stack);
    }

    public void setSmelting(ItemStack stack) {
        setItem(0,stack);
    }

    @Override
    public InventoryType getType() {
        return InventoryType.FURNACE;
    }

    @Override
    public Furnace getHolder() {
        return (Furnace) super.getHolder();
    }
}
