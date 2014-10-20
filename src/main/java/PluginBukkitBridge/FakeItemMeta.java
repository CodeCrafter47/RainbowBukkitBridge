package PluginBukkitBridge;

import PluginReference.MC_Enchantment;
import PluginReference.MC_ItemStack;
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
    public MC_ItemStack is;

    public FakeItemMeta(MC_ItemStack is) {
        this.is = is;
    }

    @Override
    public boolean hasDisplayName() {
        return is.hasCustomName();
    }

    @Override
    public String getDisplayName() {
        return is.getFriendlyName();
    }

    @Override
    public void setDisplayName(String s) {
        if(s == null)is.removeCustomName();
        is.setCustomName(s);
    }

    @Override
    public boolean hasLore() {
        return !(is.getLore() == null || is.getLore().isEmpty());
    }

    @Override
    public List<String> getLore() {
        return new ArrayList<>(is.getLore());
    }

    @Override
    public void setLore(List<String> strings) {
        if(strings==null)return;
        is.setLore(strings);
    }

    @Override
    public boolean hasEnchants() {
        return !is.getEnchantments().isEmpty();
    }

    @Override
    public boolean hasEnchant(Enchantment enchantment) {
        return 0 != is.getEnchantmentLevel(Util.wrapEnchantmentType(enchantment.getId()));
    }

    @Override
    public int getEnchantLevel(Enchantment enchantment) {
        return is.getEnchantmentLevel(Util.wrapEnchantmentType(enchantment.getId()));
    }

    @Override
    public Map<Enchantment, Integer> getEnchants() {
        Map<Enchantment, Integer> map = new HashMap<>();
        for(MC_Enchantment e: is.getEnchantments()){
            map.put(Util.wrapEnchantmentType(e.type), e.level);
        }
        return map;
    }

    @Override
    public boolean addEnchant(Enchantment enchantment, int level, boolean ignoreRestrictions) {
        MyPlugin.fixme();
        if (ignoreRestrictions || level >= enchantment.getStartLevel() && level <= enchantment.getMaxLevel()) {
            int old = getEnchantLevel(enchantment);
            is.setEnchantmentLevel(Util.wrapEnchantmentType(enchantment.getId()), level);
            return old != level;
        }
        return false;
    }

    @Override
    public boolean removeEnchant(Enchantment enchantment) {
        boolean removed = false;
        List<MC_Enchantment> list = new ArrayList<>(is.getEnchantments());
        for(int i = 0; i< list.size(); i++){
            if(list.get(i).type == Util.wrapEnchantmentType(enchantment.getId())){
                removed = true;
                list.remove(i);
                i--;
            }
        }
        is.setEnchantments(list);
        return removed;
    }

    @Override
    public boolean hasConflictingEnchant(Enchantment enchantment) {
        return checkConflictingEnchants(getEnchants(), enchantment);
    }

    @Override
    public Spigot spigot() {
        return new Spigot(){
            @Override
            public void setUnbreakable(boolean unbreakable) {
                MyPlugin.fixme();
            }

            @Override
            public boolean isUnbreakable() {
                MyPlugin.fixme();
                return false;
            }
        };
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
        return new FakeItemMeta(is.getDuplicate());
    }

    @Override
    public Map<String, Object> serialize() {
        return null;
    }
}
