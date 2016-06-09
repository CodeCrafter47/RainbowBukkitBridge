package PluginBukkitBridge.inventory;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.block.FakeContainerBlockState;
import org.bukkit.block.BrewingStand;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by florian on 20.10.14.
 */
public class FakeBrewerInventory extends FakeContainerInventory implements BrewerInventory{

    public FakeBrewerInventory(FakeContainerBlockState blockState, int maxStackSize) {
        super(blockState, maxStackSize);
    }

    @Override
    public ItemStack getIngredient() {
        return getItem(3);
    }

    @Override
    public void setIngredient(ItemStack itemStack) {
        setItem(3, itemStack);
    }

    @Override
    public ItemStack getFuel() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setFuel(ItemStack itemStack) {
        MyPlugin.fixme();
    }

    @Override
    public InventoryType getType() {
        return InventoryType.BREWING;
    }

    @Override
    public BrewingStand getHolder() {
        return (BrewingStand) super.getHolder();
    }
}
