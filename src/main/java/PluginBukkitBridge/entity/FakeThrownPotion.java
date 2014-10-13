package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;

/**
 * Created by florian on 12.10.14.
 */
public class FakeThrownPotion extends FakeProjectile implements ThrownPotion {
    public FakeThrownPotion(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public Collection<PotionEffect> getEffects() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public ItemStack getItem() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setItem(ItemStack itemStack) {
        MyPlugin.fixme("stub method");
    }
}
