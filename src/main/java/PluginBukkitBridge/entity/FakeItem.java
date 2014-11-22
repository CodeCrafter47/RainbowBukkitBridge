package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.Util;
import PluginReference.MC_ItemEntity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

/**
 * Created by florian on 12.10.14.
 */
public class FakeItem extends FakeEntity implements Item{
	MC_ItemEntity itemEntity;

    public FakeItem(MC_ItemEntity argEnt) {
        super(argEnt);
		itemEntity = argEnt;
    }

    @Override
    public ItemStack getItemStack() {
        return Util.getItemStack(itemEntity.getItemStack());
    }

    @Override
    public void setItemStack(ItemStack itemStack) {
        itemEntity.setItemStack(Util.getItemStack(itemStack));
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
