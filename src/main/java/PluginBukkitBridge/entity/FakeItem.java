package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

/**
 * Created by florian on 12.10.14.
 */
public class FakeItem extends FakeEntity implements Item{
    public FakeItem(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public ItemStack getItemStack() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setItemStack(ItemStack itemStack) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public int getPickupDelay() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setPickupDelay(int i) {
        MyPlugin.fixme("stub method");
    }
}
