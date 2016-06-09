package PluginBukkitBridge.inventory;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.Util;
import PluginBukkitBridge.block.FakeContainerBlockState;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.InventoryIterator;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by florian on 28.10.14.
 */
public abstract class FakeContainerInventory implements Inventory {
    FakeContainerBlockState blockState;
    int maxStackSize;

    public FakeContainerInventory(FakeContainerBlockState blockState, int maxStackSize) {
        this.blockState = blockState;
        this.maxStackSize = maxStackSize;
    }

    @Override
    public int getSize() {
        return blockState.container.getSize();
    }

    @Override
    public int getMaxStackSize() {
        return maxStackSize;
    }

    @Override
    public void setMaxStackSize(int i) {
        maxStackSize = i;
    }

    @Override
    public String getName() {
        MyPlugin.fixme();
		// better than null but still wrong
        return this.toString();
    }

    @Override
    public ItemStack getItem(int i) {
        return Util.getItemStack(blockState.container.getItemAtIdx(i));
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        blockState.container.setItemAtIdx(i, Util.getItemStack(itemStack));
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
    public ItemStack[] getContents() {
        ItemStack[] items = new ItemStack[getSize()];
        for(int i = 0; i < getSize(); i++)items[i] = getItem(i);
        return items;
    }

    @Override
    public void setContents(ItemStack[] itemStacks) throws IllegalArgumentException {
        for(int i = 0; i < getSize(); i++){
            setItem(i, itemStacks[i]);
        }
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
    public InventoryHolder getHolder() {
        MyPlugin.fixme();
        return null;
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
    public ItemStack[] getStorageContents() {
        ItemStack[] stacks = new ItemStack[blockState.container.getSize()];
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = Util.getItemStack(blockState.container.getItemAtIdx(i));
        }
        return stacks;
    }

    @Override
    public void setStorageContents(ItemStack[] itemStacks) throws IllegalArgumentException {
        for (int i = 0; i < itemStacks.length; i++) {
            blockState.container.setItemAtIdx(i, Util.getItemStack(itemStacks[i]));
        }
    }

    @Override
    public Location getLocation() {
        return blockState.getLocation();
    }
}
