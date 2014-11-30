package org.bukkit.craftbukkit.inventory;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.Util;
import lombok.SneakyThrows;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Map;

public class CraftShapedRecipe extends ShapedRecipe implements CraftRecipe {
    // TODO: Could eventually use this to add a matches() method or some such
    private Object recipe;

    public CraftShapedRecipe(ItemStack result) {
        super(result);
    }

    public CraftShapedRecipe(ItemStack result, Object recipe) {
        this(result);
        this.recipe = recipe;
    }

    public static CraftShapedRecipe fromBukkitRecipe(ShapedRecipe recipe) {
        if (recipe instanceof CraftShapedRecipe) {
            return (CraftShapedRecipe) recipe;
        }
        CraftShapedRecipe ret = new CraftShapedRecipe(recipe.getResult());
        String[] shape = recipe.getShape();
        ret.shape(shape);
        Map<Character, ItemStack> ingredientMap = recipe.getIngredientMap();
        for (char c : ingredientMap.keySet()) {
            ItemStack stack = ingredientMap.get(c);
            if (stack != null) {
                ret.setIngredient(c, stack.getType(), stack.getDurability());
            }
        }
        return ret;
    }

	@SneakyThrows
    public void addToCraftingManager() {
        Object[] data;
        String[] shape = this.getShape();
        Map<Character, ItemStack> ingred = this.getIngredientMap();
        int datalen = shape.length;
        datalen += ingred.size() * 2;
        int i = 0;
        data = new Object[datalen];
        for (; i < shape.length; i++) {
            data[i] = shape[i];
        }
        for (char c : ingred.keySet()) {
            ItemStack mdata = ingred.get(c);
            if (mdata == null) continue;
            data[i] = c;
            i++;
            int id = mdata.getTypeId();
            short dmg = mdata.getDurability();
            data[i] = Class.forName("WrapperObjects.ItemStackWrapper").getField("is").get(MyPlugin.server.createItemStack(id, 1, dmg));
            i++;
        }
		Object RecipeBook = Class.forName("joebkt.RecipeBook").getMethod("getRecipeBook").invoke(null);
		Class.forName("joebkt.RecipeBook").getMethod("addNewRecipe", Class.forName("joebkt.ItemStack"), Object[].class).invoke(RecipeBook, Class.forName("WrapperObjects.ItemStackWrapper").getField("is").get(Util.getItemStack(getResult())), data);
        //CraftingManager.getInstance().registerShapedRecipe(CraftItemStack.asNMSCopy(this.getResult()), data);
    }
}
