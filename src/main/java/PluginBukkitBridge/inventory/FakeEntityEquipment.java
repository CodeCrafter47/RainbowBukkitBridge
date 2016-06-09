package PluginBukkitBridge.inventory;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.Util;
import PluginReference.MC_Entity;
import PluginReference.MC_ItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by florian on 19.10.14.
 */
public class FakeEntityEquipment implements EntityEquipment {

    MC_Entity entity;

    public FakeEntityEquipment(MC_Entity entity) {
        this.entity = entity;
    }

    @Override
    public ItemStack getItemInMainHand() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setItemInMainHand(ItemStack itemStack) {
        MyPlugin.fixme();
    }

    @Override
    public ItemStack getItemInOffHand() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setItemInOffHand(ItemStack itemStack) {
        MyPlugin.fixme();
    }

    @Override
    public ItemStack getItemInHand() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setItemInHand(ItemStack itemStack) {
        MyPlugin.fixme();
    }

    @Override
    public ItemStack getHelmet() {
        return getArmorContents()[3];
    }

    @Override
    public ItemStack getChestplate() {
        return getArmorContents()[2];
    }

    @Override
    public ItemStack getLeggings() {
        return getArmorContents()[1];
    }

    @Override
    public ItemStack getBoots() {
        return getArmorContents()[0];
    }

    @Override
    public void setHelmet(ItemStack helmet) {
        ItemStack[] items = getArmorContents();
        items[3] = helmet;
        setArmorContents(items);
    }

    @Override
    public void setChestplate(ItemStack chestplate) {
        ItemStack[] items = getArmorContents();
        items[2] = chestplate;
        setArmorContents(items);
    }

    @Override
    public void setLeggings(ItemStack leggings) {
        ItemStack[] items = getArmorContents();
        items[1] = leggings;
        setArmorContents(items);
    }

    @Override
    public void setBoots(ItemStack boots) {
        ItemStack[] items = getArmorContents();
        items[0] = boots;
        setArmorContents(items);
    }

    @Override
    public ItemStack[] getArmorContents() {
        ItemStack[] stacks = new ItemStack[4];
        int i = 0;
        for (MC_ItemStack is : entity.getArmor()) {
            stacks[i++] = Util.getItemStack(is);
        }
        return stacks;
    }

    @Override
    public void setArmorContents(ItemStack[] itemStacks) {
        MC_ItemStack[] stacks = new MC_ItemStack[4];
        int i = 0;
        for (ItemStack is : itemStacks) {
            stacks[i++] = Util.getItemStack(is);
        }
        entity.setArmor(Arrays.asList(stacks));
    }

    @Override
    public void clear() {
        setArmorContents(new ItemStack[4]);
    }

    @Override
    public float getItemInHandDropChance() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setItemInHandDropChance(float v) {
        MyPlugin.fixme();
    }

    @Override
    public float getItemInMainHandDropChance() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setItemInMainHandDropChance(float v) {
        MyPlugin.fixme();
    }

    @Override
    public float getItemInOffHandDropChance() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setItemInOffHandDropChance(float v) {
        MyPlugin.fixme();
    }

    @Override
    public float getHelmetDropChance() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setHelmetDropChance(float v) {
        MyPlugin.fixme();
    }

    @Override
    public float getChestplateDropChance() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setChestplateDropChance(float v) {
        MyPlugin.fixme();
    }

    @Override
    public float getLeggingsDropChance() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setLeggingsDropChance(float v) {
        MyPlugin.fixme();
    }

    @Override
    public float getBootsDropChance() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setBootsDropChance(float v) {
        MyPlugin.fixme();
    }

    @Override
    public Entity getHolder() {
        return Util.wrapEntity(entity);
    }
}
