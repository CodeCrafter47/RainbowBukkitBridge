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
		FakeDebug("asMetaFor");
		return null;
	}

	@Override
	public ItemMeta asMetaFor(ItemMeta arg0, Material arg1) throws IllegalArgumentException
	{
		FakeDebug("asMetaFor");
		return null;
	}

	@Override
	public boolean equals(ItemMeta arg0, ItemMeta arg1) throws IllegalArgumentException
	{
		FakeDebug("equals");
		return false;
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
		FakeDebug("getItemMeta");
		return null;
	}

	@Override
	public boolean isApplicable(ItemMeta arg0, ItemStack arg1) throws IllegalArgumentException
	{
		FakeDebug("isApplicable");
		return false;
	}

	@Override
	public boolean isApplicable(ItemMeta arg0, Material arg1) throws IllegalArgumentException
	{
		FakeDebug("isApplicable");
		return false;
	}

}
