package org.bukkit.craftbukkit.inventory;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.Util;
import lombok.SneakyThrows;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.List;

public class CraftShapelessRecipe extends ShapelessRecipe implements CraftRecipe {
    // TODO: Could eventually use this to add a matches() method or some such
    private Object recipe;

    public CraftShapelessRecipe(ItemStack result) {
        super(result);
    }

    public CraftShapelessRecipe(ItemStack result, Object recipe) {
        this(result);
        this.recipe = recipe;
    }

    public static CraftShapelessRecipe fromBukkitRecipe(ShapelessRecipe recipe) {
        if (recipe instanceof CraftShapelessRecipe) {
            return (CraftShapelessRecipe) recipe;
        }
        CraftShapelessRecipe ret = new CraftShapelessRecipe(recipe.getResult());
        for (ItemStack ingred : recipe.getIngredientList()) {
            ret.addIngredient(ingred.getType(), ingred.getDurability());
        }
        return ret;
    }

	@SneakyThrows
    public void addToCraftingManager() {
        List<ItemStack> ingred = this.getIngredientList();
        Object[] data = new Object[ingred.size()];
        int i = 0;
        for (ItemStack mdata : ingred) {
            int id = mdata.getTypeId();
            short dmg = mdata.getDurability();
            data[i] = Class.forName("WrapperObjects.ItemStackWrapper").getField("is").get(MyPlugin.server.createItemStack(id, 1, dmg));
            i++;
        }
		Object RecipeBook = Class.forName("joebkt.RecipeBook").getMethod("getRecipeBook").invoke(null);
		Class.forName("joebkt.RecipeBook").getMethod("addShapelessRecipe", Class.forName("joebkt.ItemStack"), Object[].class).invoke(RecipeBook, Class.forName("WrapperObjects.ItemStackWrapper").getField("is").get(Util.getItemStack(getResult())), data);
		//CraftingManager.getInstance().registerShapelessRecipe(CraftItemStack.asNMSCopy(this.getResult()), data);
    }
}
