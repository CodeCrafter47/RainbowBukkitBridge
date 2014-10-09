package PluginBukkitBridge;

import PluginReference.MC_ItemStack;
import PluginReference.MC_Player;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.InventoryIterator;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.*;

/**
 * Created by florian on 06.10.14.
 */
public class FakePlayerInventory implements PlayerInventory {

    MC_Player player;

    public FakePlayerInventory(MC_Player player) {
        this.player = player;
    }

    @Override
    public ItemStack[] getArmorContents() {
        ItemStack[] stacks = new ItemStack[4];
        int i = 0;
        for (MC_ItemStack is : player.getArmor()) {
            stacks[i++] = Util.getItemStack(is);
        }
        return stacks;
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
    public void setArmorContents(ItemStack[] items) {
        MC_ItemStack[] stacks = new MC_ItemStack[4];
        int i = 0;
        for (ItemStack is : items) {
            stacks[i++] = Util.getItemStack(is);
        }
        player.setArmor(Arrays.asList(stacks));
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
        int count = 0;
        ItemStack[] items = getContents();
        ItemStack[] armor = getArmorContents();
        int armorSlot = getSize();

        for (int i = 0; i < items.length; i++) {
            ItemStack item = items[i];
            if (item == null) continue;
            if (id > -1 && item.getTypeId() != id) continue;
            if (data > -1 && item.getData().getData() != data) continue;

            count += item.getAmount();
            setItem(i, null);
        }

        for (ItemStack item : armor) {
            if (item == null) continue;
            if (id > -1 && item.getTypeId() != id) continue;
            if (data > -1 && item.getData().getData() != data) continue;

            count += item.getAmount();
            setItem(armorSlot++, null);
        }
        return count;
    }

    @Override
    public int getSize() {
        return player.getInventory().size();
    }

    @Override
    public int getMaxStackSize() {
        return 64;
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
        List<MC_ItemStack> inv = player.getInventory();
        return Util.getItemStack(inv.get(index));
    }

    @Override
    public void setItem(int index, ItemStack item) {
        List<MC_ItemStack> inv = player.getInventory();
        inv.remove(index);
        inv.add(index, Util.getItemStack(item));
        player.setInventory(inv);
    }

    @Override
    public ItemStack[] getContents() {
        List<MC_ItemStack> inv = player.getInventory();
        ItemStack[] items = new ItemStack[inv.size()];
        for(int i = 0; i < inv.size(); i++)items[i] = Util.getItemStack(inv.get(i));
        return items;
    }

    @Override
    public void setContents(ItemStack[] items) throws IllegalArgumentException {
        List<MC_ItemStack> inv = player.getInventory();
        for(int i = 0; i < inv.size(); i++){
            inv.remove(i);
            MC_ItemStack is = Util.getItemStack(items[i]);
            if(is==null)is=MyPlugin.server.createItemStack(0,0,0);
            inv.add(i, is);
        }
        player.setInventory(inv);
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
    public HumanEntity getHolder() {
        return MyPlugin.getPlayer(player.getName());
    }

    @Override
    public boolean contains(int materialId) {
        for (ItemStack item : getContents()) {
            if (item != null && item.getTypeId() == materialId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Material material) {
        Validate.notNull(material, "Material cannot be null");
        return contains(material.getId());
    }

    @Override
    public boolean contains(ItemStack item) {
        if (item == null) {
            return false;
        }
        for (ItemStack i : getContents()) {
            if (item.equals(i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(int materialId, int amount) {
        if (amount <= 0) {
            return true;
        }
        for (ItemStack item : getContents()) {
            if (item != null && item.getTypeId() == materialId) {
                if ((amount -= item.getAmount()) <= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean contains(Material material, int amount) {
        Validate.notNull(material, "Material cannot be null");
        return contains(material.getId(), amount);
    }

    @Override
    public boolean contains(ItemStack item, int amount) {
        if (item == null) {
            return false;
        }
        if (amount <= 0) {
            return true;
        }
        for (ItemStack i : getContents()) {
            if (item.equals(i) && --amount <= 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAtLeast(ItemStack item, int amount) {
        if (item == null) {
            return false;
        }
        if (amount <= 0) {
            return true;
        }
        for (ItemStack i : getContents()) {
            if (item.isSimilar(i) && (amount -= i.getAmount()) <= 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public HashMap<Integer, ItemStack> all(int materialId) {
        HashMap<Integer, ItemStack> slots = new HashMap<Integer, ItemStack>();

        ItemStack[] inventory = getContents();
        for (int i = 0; i < inventory.length; i++) {
            ItemStack item = inventory[i];
            if (item != null && item.getTypeId() == materialId) {
                slots.put(i, item);
            }
        }
        return slots;
    }

    @Override
    public HashMap<Integer, ItemStack> all(Material material) {
        Validate.notNull(material, "Material cannot be null");
        return all(material.getId());
    }

    @Override
    public HashMap<Integer, ItemStack> all(ItemStack item) {
        HashMap<Integer, ItemStack> slots = new HashMap<Integer, ItemStack>();
        if (item != null) {
            ItemStack[] inventory = getContents();
            for (int i = 0; i < inventory.length; i++) {
                if (item.equals(inventory[i])) {
                    slots.put(i, inventory[i]);
                }
            }
        }
        return slots;
    }

    @Override
    public int first(int materialId) {
        ItemStack[] inventory = getContents();
        for (int i = 0; i < inventory.length; i++) {
            ItemStack item = inventory[i];
            if (item != null && item.getTypeId() == materialId) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int first(Material material) {
        Validate.notNull(material, "Material cannot be null");
        return first(material.getId());
    }

    @Override
    public int first(ItemStack item) {
        return first(item, true);
    }

    private int first(ItemStack item, boolean withAmount) {
        if (item == null) {
            return -1;
        }
        ItemStack[] inventory = getContents();
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) continue;

            if (withAmount ? item.equals(inventory[i]) : item.isSimilar(inventory[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int firstEmpty() {
        ItemStack[] inventory = getContents();
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public int firstPartial(int materialId) {
        ItemStack[] inventory = getContents();
        for (int i = 0; i < inventory.length; i++) {
            ItemStack item = inventory[i];
            if (item != null && item.getTypeId() == materialId && item.getAmount() < item.getMaxStackSize()) {
                return i;
            }
        }
        return -1;
    }

    public int firstPartial(Material material) {
        Validate.notNull(material, "Material cannot be null");
        return firstPartial(material.getId());
    }

    private int firstPartial(ItemStack item) {
        ItemStack[] inventory = getContents();
        ItemStack filteredItem = new ItemStack(item);
        if (item == null) {
            return -1;
        }
        for (int i = 0; i < inventory.length; i++) {
            ItemStack cItem = inventory[i];
            if (cItem != null && cItem.getAmount() < cItem.getMaxStackSize() && cItem.isSimilar(filteredItem)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack... items) {
        Validate.noNullElements(items, "Item cannot be null");
        HashMap<Integer, ItemStack> leftover = new HashMap<Integer, ItemStack>();

        /* TODO: some optimization
         *  - Create a 'firstPartial' with a 'fromIndex'
         *  - Record the lastPartial per Material
         *  - Cache firstEmpty result
         */

        for (int i = 0; i < items.length; i++) {
            ItemStack item = items[i];
            while (true) {
                // Do we already have a stack of it?
                int firstPartial = firstPartial(item);

                // Drat! no partial stack
                if (firstPartial == -1) {
                    // Find a free spot!
                    int firstFree = firstEmpty();

                    if (firstFree == -1) {
                        // No space at all!
                        leftover.put(i, item);
                        break;
                    } else {
                        // More than a single stack!
                        if (item.getAmount() > item.getMaxStackSize()) {
                            ItemStack stack = new ItemStack(item);
                            stack.setAmount(item.getMaxStackSize());
                            setItem(firstFree, stack);
                            item.setAmount(item.getAmount() - item.getMaxStackSize());
                        } else {
                            // Just store it
                            setItem(firstFree, item);
                            break;
                        }
                    }
                } else {
                    // So, apparently it might only partially fit, well lets do just that
                    ItemStack partialItem = getItem(firstPartial);

                    int amount = item.getAmount();
                    int partialAmount = partialItem.getAmount();
                    int maxAmount = partialItem.getMaxStackSize();

                    // Check if it fully fits
                    if (amount + partialAmount <= maxAmount) {
                        partialItem.setAmount(amount + partialAmount);
                        break;
                    }

                    // It fits partially
                    partialItem.setAmount(maxAmount);
                    item.setAmount(amount + partialAmount - maxAmount);
                }
            }
        }
        return leftover;
    }

    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack... items) {
        Validate.notNull(items, "Items cannot be null");
        HashMap<Integer, ItemStack> leftover = new HashMap<Integer, ItemStack>();

        // TODO: optimization

        for (int i = 0; i < items.length; i++) {
            ItemStack item = items[i];
            int toDelete = item.getAmount();

            while (true) {
                int first = first(item, false);

                // Drat! we don't have this type in the inventory
                if (first == -1) {
                    item.setAmount(toDelete);
                    leftover.put(i, item);
                    break;
                } else {
                    ItemStack itemStack = getItem(first);
                    int amount = itemStack.getAmount();

                    if (amount <= toDelete) {
                        toDelete -= amount;
                        // clear the slot, all used up
                        clear(first);
                    } else {
                        // split the stack and store
                        itemStack.setAmount(amount - toDelete);
                        setItem(first, itemStack);
                        toDelete = 0;
                    }
                }

                // Bail when done
                if (toDelete <= 0) {
                    break;
                }
            }
        }
        return leftover;
    }


    @Override
    public void remove(int materialId) {
        ItemStack[] items = getContents();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getTypeId() == materialId) {
                clear(i);
            }
        }
    }

    @Override
    public void remove(Material material) {
        Validate.notNull(material, "Material cannot be null");
        remove(material.getId());
    }

    @Override
    public void remove(ItemStack item) {
        ItemStack[] items = getContents();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].equals(item)) {
                clear(i);
            }
        }
    }

    @Override
    public void clear(int index) {
        setItem(index, null);
    }

    @Override
    public void clear() {
        for (int i = 0; i < getSize(); i++) {
            clear(i);
        }
    }

    @Override
    public ListIterator<ItemStack> iterator() {
        return new InventoryIterator(this);
    }

    @Override
    public ListIterator<ItemStack> iterator(int index) {
        if (index < 0) {
            index += getSize() + 1; // ie, with -1, previous() will return the last element
        }
        return new InventoryIterator(this, index);
    }

    @Override
    public InventoryType getType() {
        return InventoryType.PLAYER;
    }

    @Override
    public int hashCode() {
        return player.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof FakePlayerInventory && ((FakePlayerInventory) obj).player.equals(this.player);
    }
}
