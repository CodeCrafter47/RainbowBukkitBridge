package PluginBukkitBridge;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FakeItemFactory implements ItemFactory
{
	public static void FakeDebug(String msg)
	{
		System.out.println("FakeItemFactory Proxy: " + msg);
	}

	@Override
	public ItemMeta asMetaFor(ItemMeta arg0, ItemStack arg1) throws IllegalArgumentException
	{
		return asMetaFor(arg0, arg1.getType());
	}

	@Override
	public ItemMeta asMetaFor(ItemMeta arg0, Material arg1) throws IllegalArgumentException
	{
        if(arg0 != null){
            ((FakeItemMeta)arg0).is = MyPlugin.server.createItemStack(arg1.getId(), 1, 1);
            return arg0;
        }
		return new FakeItemMeta(MyPlugin.server.createItemStack(arg1.getId(), 1, 1));
	}

	@Override
	public boolean equals(ItemMeta arg0, ItemMeta arg1) throws IllegalArgumentException
	{
		FakeDebug("equals");
		return true;
	}

	@Override
	public Color getDefaultLeatherColor()
	{
		FakeDebug("getDefaultLeatherColor");
		return null;
	}

	@Override
	public ItemMeta getItemMeta(Material arg0)
	{
		return new FakeItemMeta(MyPlugin.server.createItemStack(arg0.getId(), 1, 1));
	}

	@Override
	public boolean isApplicable(ItemMeta arg0, ItemStack arg1) throws IllegalArgumentException
	{
		return arg1 != null;
	}

	@Override
	public boolean isApplicable(ItemMeta arg0, Material arg1) throws IllegalArgumentException
	{
		return arg1 != Material.AIR;
	}

}
