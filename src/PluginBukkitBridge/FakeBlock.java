package PluginBukkitBridge;

import java.util.Collection;
import java.util.List;

import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.inventory.*;
import org.bukkit.metadata.*;
import org.bukkit.plugin.*;

import PluginReference.*;

public class FakeBlock implements Block
{
	public MC_Block m_block = null;
	public MC_World m_world = null;
	public MC_ItemStack is = null;
	public int x, y, z;
	int id = -1;
	byte subType = -1;

	private int internalGetId()
	{
		if(id != -1) return id;
		return m_block.getId();
	}
	private byte internalGetSubtype()
	{
		if(subType != -1) return subType ;
		return (byte)m_block.getSubtype();
	}
	
	public static void FakeDebug(String msg)
	{
		System.out.println("FakeBlock Proxy: " + msg);
	}

	public FakeBlock(MC_Block blk, MC_World world, int tx, int ty, int tz)
	{
		m_block = blk;
		m_world = world;
		x = tx;
		y = ty;
		z = tz;
		if((blk != null) && (blk != this)) 
		{
			id = blk.getId();
			subType = (byte)blk.getSubtype();
		}
	}
	public FakeBlock(MC_Block blk, MC_World world, int tx, int ty, int tz, int argID, int argSubtype)
	{
		m_block = blk;
		m_world = world;
		x = tx;
		y = ty;
		z = tz;
		id = argID;
		subType = (byte)argSubtype;
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
	public boolean breakNaturally()
	{
				FakeDebug("breakNaturally");
		return false;
	}

	@Override
	public boolean breakNaturally(ItemStack arg0)
	{
				FakeDebug("breakNaturally");
		return false;
	}

	@Override
	public Biome getBiome()
	{
				FakeDebug("getBiome");
		return null;
	}

	@Override
	public int getBlockPower()
	{
				FakeDebug("getBlockPower");
		return 0;
	}

	@Override
	public int getBlockPower(BlockFace arg0)
	{
				FakeDebug("getBlockPower");
		return 0;
	}

	@Override
	public Chunk getChunk()
	{
				FakeDebug("getChunk");
		return null;
	}

	@Override
	public byte getData()
	{
		return internalGetSubtype();
	}

	@Override
	public Collection<ItemStack> getDrops()
	{
		FakeDebug("getDrops");
		return null;
	}

	@Override
	public Collection<ItemStack> getDrops(ItemStack arg0)
	{
				FakeDebug("getDrops");
		return null;
	}

	@Override
	public BlockFace getFace(Block arg0)
	{
		FakeDebug("getFace");
		return null;
	}

	@Override
	public double getHumidity()
	{
				FakeDebug("getHumidity");
		return 0;
	}

	@Override
	public byte getLightFromBlocks()
	{
				FakeDebug("getLightFromBlocks");
		return 0;
	}

	@Override
	public byte getLightFromSky()
	{
				FakeDebug("getLightFromSky");
		return 0;
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
		World world = Bukkit.getWorld(m_world.getName());
		Location loc = new Location(world, x, y, z);
		return loc;
	}

	@Override
	public Location getLocation(Location arg0)
	{
		FakeDebug("getLocation: " + FakeHelper.LocStringShort(arg0));
		return null;
	}

	@Override
	public PistonMoveReaction getPistonMoveReaction()
	{
		FakeDebug("getPistonMoveReaction");
		return null;
	}

	@Override
	public Block getRelative(BlockFace face)
	{
		int nx = x;
		int ny = y;
		int nz = z;
		
		if(face == BlockFace.NORTH) nz--; 
		if(face == BlockFace.SOUTH) nz++;
		if(face == BlockFace.WEST) nx--;
		if(face == BlockFace.EAST) nx++;
		if(face == BlockFace.UP) ny++; 
		if(face == BlockFace.DOWN) ny--;
		
		if(face == BlockFace.NORTH_EAST)
		{
			nz--; 
			nx++;
		}
		if(face == BlockFace.NORTH_WEST)
		{
			nz--; 
			nx--;
		}
		if(face == BlockFace.SOUTH_WEST)
		{
			nz++; 
			nx--;
		}
		if(face == BlockFace.SOUTH_EAST)
		{
			nz++; 
			nx++;
		}
		
		return new FakeBlock(m_world.getBlockAt(nx,  ny, nz), m_world, nx, ny, nz);
	}

	@Override
	public Block getRelative(BlockFace arg0, int arg1)
	{
				FakeDebug("getRelative");
		return null;
	}

	@Override
	public Block getRelative(int arg0, int arg1, int arg2)
	{
				FakeDebug("getRelative");
		return null;
	}

	@Override
	public BlockState getState()
	{
		FakeDebug("getState");
		return null;
	}

	@Override
	public double getTemperature()
	{
				FakeDebug("getTemperature");
		return 0;
	}

	@Override
	public Material getType()
	{
		if(m_block == null) return Material.AIR;
		return Material.getMaterial(internalGetId());
	}

	@Override
	public int getTypeId()
	{
		return internalGetId();
	}

	@Override
	public World getWorld()
	{
		return new FakeWorld(m_world);
	}

	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}

	@Override
	public int getZ()
	{
		return z;
}

	@Override
	public boolean isBlockFaceIndirectlyPowered(BlockFace arg0)
	{
		FakeDebug("isBlockFaceIndirectlyPowered");
		return false;
	}

	@Override
	public boolean isBlockFacePowered(BlockFace arg0)
	{
				FakeDebug("isBlockFacePowered");
		return false;
	}

	@Override
	public boolean isBlockIndirectlyPowered()
	{
				FakeDebug("isBlockIndirectlyPowered");
		return false;
	}

	@Override
	public boolean isBlockPowered()
	{
				FakeDebug("isBlockPowered");
		return false;
	}

	@Override
	public boolean isEmpty()
	{
				FakeDebug("isEmpty");
		return false;
	}

	@Override
	public boolean isLiquid()
	{
				FakeDebug("isLiquid");
		return false;
	}

	@Override
	public void setBiome(Biome arg0)
	{
				FakeDebug("setBiome");
		
	}

	@Override
	public void setData(byte arg0)
	{
		m_world.setBlockAt(x,  y, z, m_block, arg0);
	}

	@Override
	public void setData(byte arg0, boolean arg1)
	{
		m_world.setBlockAt(x,  y, z, m_block, arg0);
	}

	@Override
	public void setType(Material mat)
	{
		setTypeId(mat.getId(), true);
	}

	@Override
	public boolean setTypeId(int id)
	{
		setTypeId(id, true);
		return true;
	}

	@Override
	public boolean setTypeId(int blkID, boolean arg1)
	{
		String blockName = BlockHelper.getBlockName(blkID);
		MC_Block blk = m_world.getBlockFromName(blockName.toLowerCase());
		if(blk == null)
		{
			FakeDebug("setTypeId: ID=" + blkID + ", flag=" + arg1);
			return false;
		}

		m_world.setBlockAt(x,  y, z, blk, 0);
		
		return true;
	}

	@Override
	public boolean setTypeIdAndData(int blkID, byte blkData, boolean arg2)
	{
		String blockName = BlockHelper.getBlockName(blkID);
		MC_Block blk = m_world.getBlockFromName(blockName.toLowerCase());
		if(blk == null)
		{
			FakeDebug("setTypeIdAndData: ID=" + blkID + ", Data=" + blkData + ", flag=" + arg2);
			return false;
		}

		m_world.setBlockAt(x,  y, z, blk, blkData);
		
		return true;
	}

}
