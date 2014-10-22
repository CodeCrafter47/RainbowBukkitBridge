package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.ReflectionUtil;
import PluginBukkitBridge.Util;
import PluginReference.MC_Entity;
import PluginReference.MC_ItemStack;
import org.bukkit.Material;
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
        MC_ItemStack itemStack = ReflectionUtil.getItemStackOfEntityItem(m_ent);
        if(itemStack == null){
            MyPlugin.logger.warning("itemstack is null");
            return new ItemStack(Material.AIR);
        }
        return Util.getItemStack(itemStack);
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
