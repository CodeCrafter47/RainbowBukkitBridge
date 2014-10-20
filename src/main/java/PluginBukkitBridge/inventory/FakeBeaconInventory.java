package PluginBukkitBridge.inventory;

import PluginBukkitBridge.MyPlugin;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.BeaconInventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by florian on 20.10.14.
 */
public class FakeBeaconInventory implements BeaconInventory {
    @Override
    public void setItem(ItemStack itemStack) {
        MyPlugin.fixme();
    }

    @Override
    public ItemStack getItem() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public int getSize() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int getMaxStackSize() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setMaxStackSize(int i) {
        MyPlugin.fixme();
    }

    @Override
    public String getName() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public ItemStack getItem(int i) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        MyPlugin.fixme();
    }

    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack... itemStacks) throws IllegalArgumentException {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack... itemStacks) throws IllegalArgumentException {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public ItemStack[] getContents() {
        MyPlugin.fixme();
        return new ItemStack[0];
    }

    @Override
    public void setContents(ItemStack[] itemStacks) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public boolean contains(int i) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean contains(Material material) throws IllegalArgumentException {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean contains(ItemStack itemStack) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean contains(int i, int i2) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean contains(Material material, int i) throws IllegalArgumentException {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean contains(ItemStack itemStack, int i) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean containsAtLeast(ItemStack itemStack, int i) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(int i) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(ItemStack itemStack) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public int first(int i) {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int first(Material material) throws IllegalArgumentException {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int first(ItemStack itemStack) {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int firstEmpty() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void remove(int i) {
        MyPlugin.fixme();

    }

    @Override
    public void remove(Material material) throws IllegalArgumentException {
        MyPlugin.fixme();

    }

    @Override
    public void remove(ItemStack itemStack) {
        MyPlugin.fixme();
    }

    @Override
    public void clear(int i) {
        MyPlugin.fixme();
    }

    @Override
    public void clear() {
        MyPlugin.fixme();
    }

    @Override
    public List<HumanEntity> getViewers() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public String getTitle() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public InventoryType getType() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public InventoryHolder getHolder() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public ListIterator<ItemStack> iterator() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public ListIterator<ItemStack> iterator(int i) {
        MyPlugin.fixme();
        return null;
    }
}
