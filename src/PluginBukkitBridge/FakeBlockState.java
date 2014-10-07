package PluginBukkitBridge;

import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class FakeBlockState implements BlockState
{
	FakeBlock blk = null;
	public FakeBlockState(FakeBlock arg)
	{
		blk = arg;
	}
	public static void FakeDebug(String msg)
	{
		System.out.println("FakeBlockState Proxy: " + msg);
	}
	

	@Override
	public List<MetadataValue> getMetadata(String arg0)
	{
		FakeDebug("getMetadata");
		return null;
	}

	@Override
	public boolean hasMetadata(String arg0)
	{
		FakeDebug("hasMetadata");
		return false;
	}

	@Override
	public void removeMetadata(String arg0, Plugin arg1)
	{
		FakeDebug("removeMetadata");
	}

	@Override
	public void setMetadata(String arg0, MetadataValue arg1)
	{
		FakeDebug("setMetadata");
	}

	@Override
	public Block getBlock()
	{
		return blk;
	}

	@Override
	public Chunk getChunk()
	{
		FakeDebug("getChunk");
		return null;
	}

	@Override
	public MaterialData getData()
	{
		FakeDebug("getData");
		return null;
	}

	@Override
	public byte getLightLevel()
	{
		FakeDebug("getLightLevel");
		return 0;
	}

	@Override
	public Location getLocation()
	{
		return blk.getLocation();
	}

	@Override
	public Location getLocation(Location arg0)
	{
		return blk.getLocation(arg0);
	}

	@Override
	public byte getRawData()
	{
		FakeDebug("getRawData");
		return 0;
	}

	@Override
	public Material getType()
	{
		return blk.getType();
	}

	@Override
	public int getTypeId()
	{
		return blk.getTypeId();
	}

	@Override
	public World getWorld()
	{
		return blk.getWorld();
	}

	@Override
	public int getX()
	{
		return blk.getX();
	}

	@Override
	public int getY()
	{
		return blk.getY();
	}

	@Override
	public int getZ()
	{
		return blk.getZ();
	}

	@Override
	public void setData(MaterialData arg0)
	{
		FakeDebug("setData");
		
	}

	@Override
	public void setRawData(byte arg0)
	{
		FakeDebug("setRawData");
		
	}

	@Override
	public void setType(Material arg0)
	{
		blk.setType(arg0);
	}

	@Override
	public boolean setTypeId(int arg0)
	{
		return blk.setTypeId(arg0);
	}

	@Override
	public boolean update()
	{
		FakeDebug("update1");
		return false;
	}

	@Override
	public boolean update(boolean arg0)
	{
		FakeDebug("update2=" + arg0);
		return false;
	}

	@Override
	public boolean update(boolean arg0, boolean arg1)
	{
		FakeDebug("update3: arg0=" + arg0 + ", arg1=" + arg1);
		return false;
	}

}
