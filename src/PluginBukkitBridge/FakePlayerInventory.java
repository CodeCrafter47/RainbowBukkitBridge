package PluginBukkitBridge;

import PluginReference.MC_ItemStack;
import PluginReference.MC_Player;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by florian on 06.10.14.
 */
public class FakePlayerInventory implements PlayerInventory{

    MC_Player player;

    public FakePlayerInventory(MC_Player player) {
        this.player = player;
    }

    @Override
    public ItemStack[] getArmorContents() {
        ItemStack[] stacks = new ItemStack[4];
        int i = 0;
        for(MC_ItemStack is: player.getArmor()){
            stacks[i++] = Util.getItemStack(is);
        }
        return stacks;
    }

    @Override
    public ItemStack getHelmet() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public ItemStack getChestplate() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public ItemStack getLeggings() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public ItemStack getBoots() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setArmorContents(ItemStack[] items) {
        MyPlugin.fixme();

    }

    @Override
    public void setHelmet(ItemStack helmet) {
        MyPlugin.fixme();

    }

    @Override
    public void setChestplate(ItemStack chestplate) {
        MyPlugin.fixme();

    }

    @Override
    public void setLeggings(ItemStack leggings) {
        MyPlugin.fixme();

    }

    @Override
    public void setBoots(ItemStack boots) {
        MyPlugin.fixme();

    }

    @Override
    public ItemStack getItemInHand() {
        return Util.getItemStack(player.getItemInHand());
    }

    @Override
    public void setItemInHand(ItemStack stack) {
        player.setItemInHand(Util.getItemStack(stack));
    }

    @Override
    public int getHeldItemSlot() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setHeldItemSlot(int slot) {
        MyPlugin.fixme();

    }

    @Override
    public int clear(int id, int data) {
        MyPlugin.fixme();
        return 0;
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
    public void setMaxStackSize(int size) {
        MyPlugin.fixme();

    }

    @Override
    public String getName() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public ItemStack getItem(int index) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setItem(int index, ItemStack item) {
        MyPlugin.fixme();

    }

    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack... items) throws IllegalArgumentException {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public ItemStack[] getContents() {
        MyPlugin.fixme();
        return new ItemStack[0];
    }

    @Override
    public void setContents(ItemStack[] items) throws IllegalArgumentException {
        MyPlugin.fixme();

    }

    @Override
    public boolean contains(int materialId) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean contains(Material material) throws IllegalArgumentException {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean contains(ItemStack item) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean contains(int materialId, int amount) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean contains(Material material, int amount) throws IllegalArgumentException {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean contains(ItemStack item, int amount) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean containsAtLeast(ItemStack item, int amount) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(int materialId) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(ItemStack item) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public int first(int materialId) {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int first(Material material) throws IllegalArgumentException {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int first(ItemStack item) {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int firstEmpty() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void remove(int materialId) {
        MyPlugin.fixme();

    }

    @Override
    public void remove(Material material) throws IllegalArgumentException {
        MyPlugin.fixme();

    }

    @Override
    public void remove(ItemStack item) {
        MyPlugin.fixme();

    }

    @Override
    public void clear(int index) {
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
    public HumanEntity getHolder() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public ListIterator<ItemStack> iterator() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public ListIterator<ItemStack> iterator(int index) {
        MyPlugin.fixme();
        return null;
    }
}
