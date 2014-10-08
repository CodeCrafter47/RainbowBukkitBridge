package PluginBukkitBridge;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by florian on 08.10.14.
 */
public class FakeItemMeta implements ItemMeta {
    private String displayName;
    List<String> lore;
    Map<Enchantment, Integer> enchantments;

    public FakeItemMeta() {
        displayName = null;
        lore = null;
        enchantments = new HashMap<>();
    }

    public FakeItemMeta(FakeItemMeta fakeItemMeta) {
        displayName = fakeItemMeta.displayName;
        lore = fakeItemMeta.lore;
        enchantments = fakeItemMeta.enchantments;
    }

    @Override
    public boolean hasDisplayName() {
        return displayName != null;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String s) {
        displayName = s;
    }

    @Override
    public boolean hasLore() {
        return lore != null;
    }

    @Override
    public List<String> getLore() {
        return new ArrayList<>(lore);
    }

    @Override
    public void setLore(List<String> strings) {
        lore = new ArrayList<>(strings);
    }

    @Override
    public boolean hasEnchants() {
        return !enchantments.isEmpty();
    }

    @Override
    public boolean hasEnchant(Enchantment enchantment) {
        return enchantments.containsKey(enchantment);
    }

    @Override
    public int getEnchantLevel(Enchantment enchantment) {
        if(!hasEnchant(enchantment))return 0;
        return enchantments.get(enchantment);
    }

    @Override
    public Map<Enchantment, Integer> getEnchants() {
        return new HashMap<>(enchantments);
    }

    @Override
    public boolean addEnchant(Enchantment enchantment, int level, boolean ignoreRestrictions) {
        if (ignoreRestrictions || level >= enchantment.getStartLevel() && level <= enchantment.getMaxLevel()) {
            Integer old = enchantments.put(enchantment, level);
            return old == null || old != level;
        }
        return false;
    }

    @Override
    public boolean removeEnchant(Enchantment enchantment) {
        return hasEnchants() && enchantments.remove(enchantment) != null;
    }

    @Override
    public boolean hasConflictingEnchant(Enchantment enchantment) {
        return checkConflictingEnchants(enchantments, enchantment);
    }

    static boolean checkConflictingEnchants(Map<Enchantment, Integer> enchantments, Enchantment ench) {
        if (enchantments == null || enchantments.isEmpty()) {
            return false;
        }

        for (Enchantment enchant : enchantments.keySet()) {
            if (enchant.conflictsWith(ench)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public ItemMeta clone() {
        return new FakeItemMeta(this);
    }

    @Override
    public Map<String, Object> serialize() {
        return null;
    }
}
