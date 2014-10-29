package PluginBukkitBridge;

import PluginReference.MC_EnchantmentType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by florian on 29.10.14.
 */
public class FakeEnchantment extends Enchantment{

    MC_EnchantmentType type;
    String name;
    int startLevel;
    int maxLevel;
    EnchantmentTarget target;
    List<Integer> conflictingEnchants;

    public FakeEnchantment(int id, MC_EnchantmentType type, String name, int startLevel, int maxLevel, EnchantmentTarget target, List<Integer> conflictingEnchants) {
        super(id);
        this.type = type;
        this.name = name;
        this.startLevel = startLevel;
        this.maxLevel = maxLevel;
        this.target = target;
        this.conflictingEnchants = conflictingEnchants;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public int getStartLevel() {
        return startLevel;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return target;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return conflictingEnchants.contains(enchantment.getId());
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return target.includes(itemStack);
    }
}
