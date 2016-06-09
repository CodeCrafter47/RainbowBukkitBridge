package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_EntityAgeable;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.MerchantRecipe;

import java.util.Collections;
import java.util.List;

/**
 * Created by florian on 12.10.14.
 */
public class FakeVillager extends FakeAgeable implements Villager {
    public FakeVillager(MC_EntityAgeable ageable) {
        super(ageable);
    }

    @Override
    public Profession getProfession() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setProfession(Profession profession) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public List<MerchantRecipe> getRecipes() {
        MyPlugin.fixme();
        return Collections.emptyList();
    }

    @Override
    public void setRecipes(List<MerchantRecipe> list) {
        MyPlugin.fixme();
    }

    @Override
    public MerchantRecipe getRecipe(int i) throws IndexOutOfBoundsException {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setRecipe(int i, MerchantRecipe merchantRecipe) throws IndexOutOfBoundsException {
        MyPlugin.fixme();
    }

    @Override
    public int getRecipeCount() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public Inventory getInventory() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean isTrading() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public HumanEntity getTrader() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public int getRiches() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setRiches(int i) {
        MyPlugin.fixme();
    }
}
