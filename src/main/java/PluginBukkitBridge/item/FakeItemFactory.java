package PluginBukkitBridge.item;

import PluginBukkitBridge.Util;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jnbt.CompoundTag;
import org.jnbt.Tag;

import java.util.HashMap;

public class FakeItemFactory implements ItemFactory {
    static final Color DEFAULT_LEATHER_COLOR = Color.fromRGB(0xA06540);

    @Override
    public ItemMeta asMetaFor(ItemMeta arg0, ItemStack arg1) throws IllegalArgumentException {
        return asMetaFor(arg0, arg1.getType());
    }

    @Override
    public ItemMeta asMetaFor(ItemMeta meta, Material material) throws IllegalArgumentException {
        if (!(meta instanceof FakeItemMeta)) {
            throw new IllegalArgumentException("Meta of " + (meta != null ? meta.getClass().toString() : "null") + " not created by " + FakeItemFactory.class.getName());
        }
        return Util.getItemMeta(material, ((FakeItemMeta) meta).tag);
    }
        /*
        if (arg0 != null) {
            ((FakeItemMeta) arg0).is = MyPlugin.server.createItemStack(arg1.getId(), 1, 1);
            return arg0;
        }
        return new FakeItemMeta(MyPlugin.server.createItemStack(arg1.getId(), 1, 1));
        */

    @Override
    public boolean equals(ItemMeta meta1, ItemMeta meta2) throws IllegalArgumentException {
        if (meta1 == meta2) {
            return true;
        }
        if (meta1 != null && !(meta1 instanceof FakeItemMeta)) {
            throw new IllegalArgumentException("First meta of " + meta1.getClass().getName() + " does not belong to " + FakeItemFactory.class.getName());
        }
        if (meta2 != null && !(meta2 instanceof FakeItemMeta)) {
            throw new IllegalArgumentException("Second meta " + meta2.getClass().getName() + " does not belong to " + FakeItemFactory.class.getName());
        }
        if (meta1 == null) {
            return ((FakeItemMeta) meta2).isEmpty();
        }
        if (meta2 == null) {
            return ((FakeItemMeta) meta1).isEmpty();
        }

        return ((FakeItemMeta)meta1).equalsCommon(((FakeItemMeta)meta2)) && ((FakeItemMeta)meta1).notUncommon(((FakeItemMeta)meta2)) && ((FakeItemMeta)meta2).notUncommon(((FakeItemMeta)meta1));
    }

    @Override
    public Color getDefaultLeatherColor() {
        return DEFAULT_LEATHER_COLOR;
    }

    @Override
    public ItemMeta getItemMeta(Material arg0) {
        return Util.getItemMeta(arg0, new CompoundTag("tag", new HashMap<String, Tag>()));
    }

    @Override
    public boolean isApplicable(ItemMeta meta, ItemStack itemstack) throws IllegalArgumentException {
        if (itemstack == null) {
            return false;
        }
        return isApplicable(meta, itemstack.getType());
    }

    @Override
    public boolean isApplicable(ItemMeta meta, Material type) throws IllegalArgumentException {
        if (type == null || meta == null) {
            return false;
        }
        if (!(meta instanceof FakeItemMeta)) {
            throw new IllegalArgumentException("Meta of " + meta.getClass().toString() + " not created by " + FakeItemFactory.class.getName());
        }

        return ((FakeItemMeta) meta).applicableTo(type);
    }

}
