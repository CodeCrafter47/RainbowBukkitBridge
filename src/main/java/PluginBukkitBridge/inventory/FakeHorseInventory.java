package PluginBukkitBridge.inventory;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Horse;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by florian on 13.10.14.
 */
public class FakeHorseInventory implements HorseInventory {

    MC_Horse horse;

    public FakeHorseInventory(MC_Horse horse) {
        this.horse = horse;
    }

    @Override
    public ItemStack getSaddle() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public ItemStack getArmor() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setSaddle(ItemStack itemStack) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public void setArmor(ItemStack itemStack) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public int getSize() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public int getMaxStackSize() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setMaxStackSize(int i) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public String getName() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public ItemStack getItem(int i) {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack... itemStacks) throws IllegalArgumentException {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack... itemStacks) throws IllegalArgumentException {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public ItemStack[] getContents() {
        MyPlugin.fixme("stub method");
        return new ItemStack[0];
    }

    @Override
    public void setContents(ItemStack[] itemStacks) throws IllegalArgumentException {
        MyPlugin.fixme("stub method");
    }

    @Override
    public ItemStack[] getStorageContents() {
        MyPlugin.fixme();
        return new ItemStack[0];
    }

    @Override
    public void setStorageContents(ItemStack[] itemStacks) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public boolean contains(int i) {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public boolean contains(Material material) throws IllegalArgumentException {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public boolean contains(ItemStack itemStack) {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public boolean contains(int i, int i2) {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public boolean contains(Material material, int i) throws IllegalArgumentException {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public boolean contains(ItemStack itemStack, int i) {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public boolean containsAtLeast(ItemStack itemStack, int i) {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(int i) {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(ItemStack itemStack) {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public int first(int i) {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public int first(Material material) throws IllegalArgumentException {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public int first(ItemStack itemStack) {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public int firstEmpty() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void remove(int i) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public void remove(Material material) throws IllegalArgumentException {
        MyPlugin.fixme("stub method");

    }

    @Override
    public void remove(ItemStack itemStack) {
        MyPlugin.fixme("stub method");

    }

    @Override
    public void clear(int i) {
        MyPlugin.fixme("stub method");

    }

    @Override
    public void clear() {
        MyPlugin.fixme("stub method");

    }

    @Override
    public List<HumanEntity> getViewers() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public String getTitle() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public InventoryType getType() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public InventoryHolder getHolder() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public ListIterator<ItemStack> iterator() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public ListIterator<ItemStack> iterator(int i) {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public Location getLocation() {
        MyPlugin.fixme();
        return null;
    }
}
