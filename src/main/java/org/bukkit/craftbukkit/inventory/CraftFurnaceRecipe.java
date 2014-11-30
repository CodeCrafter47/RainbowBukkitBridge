package org.bukkit.craftbukkit.inventory;

import PluginBukkitBridge.Util;
import lombok.SneakyThrows;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class CraftFurnaceRecipe extends FurnaceRecipe implements CraftRecipe {
    public CraftFurnaceRecipe(ItemStack result, ItemStack source) {
        super(result, source.getType(), source.getDurability());
    }

    public static CraftFurnaceRecipe fromBukkitRecipe(FurnaceRecipe recipe) {
        if (recipe instanceof CraftFurnaceRecipe) {
            return (CraftFurnaceRecipe) recipe;
        }
        return new CraftFurnaceRecipe(recipe.getResult(), recipe.getInput());
    }

    @Override
	@SneakyThrows
    public void addToCraftingManager() {
        ItemStack result = this.getResult();
        ItemStack input = this.getInput();
		Object RecipeBook = Class.forName("joebkt.FurnaceResultManager").getMethod("a").invoke(null);
		Class.forName("joebkt.FurnaceResultManager").getMethod("setConvertsTo3", Class.forName("joebkt.ItemStack"), Class.forName("joebkt.ItemStack"), float.class)
				.invoke(RecipeBook, Class.forName("WrapperObjects.ItemStackWrapper").getField("is").get(Util.getItemStack(getInput())),
						Class.forName("WrapperObjects.ItemStackWrapper").getField("is").get(Util.getItemStack(getResult())), 0.0F);
		//RecipesFurnace.getInstance().registerRecipe(CraftItemStack.asNMSCopy(input), CraftItemStack.asNMSCopy(result));
    }
}
