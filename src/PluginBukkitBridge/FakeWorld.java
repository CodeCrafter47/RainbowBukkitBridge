package PluginBukkitBridge;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.entity.*;
import org.bukkit.generator.*;
import org.bukkit.inventory.*;
import org.bukkit.metadata.*;
import org.bukkit.plugin.*;
import org.bukkit.util.*;

import PluginReference.*;

public class FakeWorld implements World
{
	public MC_World m_world = null;
	
	public static void FakeDebug(String msg)
	{
		System.out.println("FakeWorld Proxy: " + msg);
	}

	public FakeWorld(MC_World world)
	{
		m_world = world;
	}
	
	@Override
	public Set<String> getListeningPluginChannels()
	{
		FakeDebug("getListeningPluginChannels");
		return null;
	}

	@Override
	public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2)
	{
		FakeDebug("sendPluginMessage");
		
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
	public boolean canGenerateStructures()
	{
		FakeDebug("canGenerateStructures");
		return false;
	}

	@Override
	public boolean createExplosion(Location arg0, float arg1)
	{
		FakeDebug("createExplosion");
		return false;
	}

	@Override
	public boolean createExplosion(Location arg0, float arg1, boolean arg2)
	{
		FakeDebug("createExplosion");
		return false;
	}

	@Override
	public boolean createExplosion(double arg0, double arg1, double arg2, float arg3)
	{
		FakeDebug("createExplosion");
		return false;
	}

	@Override
	public boolean createExplosion(double arg0, double arg1, double arg2, float arg3, boolean arg4)
	{
		FakeDebug("createExplosion");
		return false;
	}

	@Override
	public boolean createExplosion(double arg0, double arg1, double arg2, float arg3, boolean arg4, boolean arg5)
	{
		FakeDebug("createExplosion");
		return false;
	}

	@Override
	public Item dropItem(Location arg0, ItemStack arg1)
	{
		FakeDebug("dropItem");
		return null;
	}

	@Override
	public Item dropItemNaturally(Location arg0, ItemStack arg1)
	{
		FakeDebug("dropItemNaturally");
		return null;
	}

	@Override
	public boolean generateTree(Location arg0, TreeType arg1)
	{
		FakeDebug("generateTree");
		return false;
	}

	@Override
	public boolean generateTree(Location arg0, TreeType arg1, BlockChangeDelegate arg2)
	{
		FakeDebug("generateTree");
		return false;
	}

	@Override
	public boolean getAllowAnimals()
	{
		FakeDebug("getAllowAnimals");
		return false;
	}

	@Override
	public boolean getAllowMonsters()
	{
		FakeDebug("getAllowMonsters");
		return false;
	}

	@Override
	public int getAmbientSpawnLimit()
	{
		FakeDebug("getAmbientSpawnLimit");
		return 0;
	}

	@Override
	public int getAnimalSpawnLimit()
	{
		FakeDebug("getAnimalSpawnLimit");
		return 0;
	}

	@Override
	public Biome getBiome(int arg0, int arg1)
	{
		FakeDebug("getBiome");
		return null;
	}

	@Override
	public Block getBlockAt(Location loc)
	{
		return getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}

	@Override
	public Block getBlockAt(int x, int y, int z)
	{
		MC_Block blk = m_world.getBlockAt(x,y,z);
		return new FakeBlock(blk, m_world, x, y, z);
	}

	@Override
	public int getBlockTypeIdAt(Location loc)
	{
		return getBlockTypeIdAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}

	@Override
	public int getBlockTypeIdAt(int x, int y, int z)
	{
		MC_Block blk = m_world.getBlockAt(x,y,z);
		return blk.getId();
	}

	@Override
	public Chunk getChunkAt(Location arg0)
	{
		FakeDebug("getChunkAt");
		return null;
	}

	@Override
	public Chunk getChunkAt(Block arg0)
	{
		FakeDebug("getChunkAt");
		return null;
	}

	@Override
	public Chunk getChunkAt(int arg0, int arg1)
	{
		FakeDebug("getChunkAt");
		return null;
	}

	@Override
	public Difficulty getDifficulty()
	{
		FakeDebug("getDifficulty");
		return null;
	}

	@Override
	public ChunkSnapshot getEmptyChunkSnapshot(int arg0, int arg1, boolean arg2, boolean arg3)
	{
		FakeDebug("getEmptyChunkSnapshot");
		return null;
	}

	@Override
	public List<Entity> getEntities()
	{
		ArrayList<Entity> arr = new ArrayList<Entity>();
		for(MC_Entity ent : m_world.getEntities()) arr.add(new FakeEntity(ent));
		return arr;
	}

	@Override
	public <T extends Entity> Collection<T> getEntitiesByClass(Class<T>... arg0)
	{
		FakeDebug("getEntitiesByClass");
		return null;
	}

	@Override
	public <T extends Entity> Collection<T> getEntitiesByClass(Class<T> arg0)
	{
		FakeDebug("getEntitiesByClass");
		return null;
	}

	@Override
	public Collection<Entity> getEntitiesByClasses(Class<?>... arg0)
	{
		FakeDebug("getEntitiesByClasses");
		return null;
	}

	@Override
	public Environment getEnvironment()
	{
		FakeDebug("getEnvironment");
		return null;
	}

	@Override
	public long getFullTime()
	{
		FakeDebug("getFullTime");
		return 0;
	}

	@Override
	public String getGameRuleValue(String arg0)
	{
		FakeDebug("getGameRuleValue");
		return null;
	}

	@Override
	public String[] getGameRules()
	{
		FakeDebug("getGameRules");
		return null;
	}

	@Override
	public ChunkGenerator getGenerator()
	{
		FakeDebug("getGenerator");
		return null;
	}

	@Override
	public Block getHighestBlockAt(Location arg0)
	{
		FakeDebug("getHighestBlockAt");
		return null;
	}

	@Override
	public Block getHighestBlockAt(int arg0, int arg1)
	{
		FakeDebug("getHighestBlockAt");
		return null;
	}

	@Override
	public int getHighestBlockYAt(Location arg0)
	{
		FakeDebug("getHighestBlockYAt");
		return 0;
	}

	@Override
	public int getHighestBlockYAt(int arg0, int arg1)
	{
		FakeDebug("getHighestBlockYAt");
		return 0;
	}

	@Override
	public double getHumidity(int arg0, int arg1)
	{
		FakeDebug("getHumidity");
		return 0;
	}

	@Override
	public boolean getKeepSpawnInMemory()
	{
		FakeDebug("getKeepSpawnInMemory");
		return false;
	}

	@Override
	public List<LivingEntity> getLivingEntities()
	{
		FakeDebug("getLivingEntities");
		return null;
	}

	@Override
	public Chunk[] getLoadedChunks()
	{
		FakeDebug("getLoadedChunks");
		return null;
	}

	@Override
	public int getMaxHeight()
	{
		FakeDebug("getMaxHeight");
		return 0;
	}

	@Override
	public int getMonsterSpawnLimit()
	{
		FakeDebug("getMonsterSpawnLimit");
		return 0;
	}

	@Override
	public String getName()
	{
		return m_world.getName();
	}

	@Override
	public boolean getPVP()
	{
		FakeDebug("getPVP");
		return false;
	}

	@Override
	public List<Player> getPlayers()
	{
		ArrayList<Player> arr = new ArrayList<Player>();
		for(MC_Player plr : MyPlugin.server.getPlayers()) 
		{
			arr.add(new FakePlayer(plr));
		}
		return arr;
	}

	@Override
	public List<BlockPopulator> getPopulators()
	{
		FakeDebug("getPopulators");
		return null;
	}

	@Override
	public int getSeaLevel()
	{
		FakeDebug("getSeaLevel");
		return 0;
	}

	@Override
	public long getSeed()
	{
		FakeDebug("getSeed");
		return 0;
	}

	@Override
	public Location getSpawnLocation()
	{
		MC_Location loc = m_world.getSpawnLocation();
		return new Location(this, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()); 
	}

	@Override
	public double getTemperature(int arg0, int arg1)
	{
		FakeDebug("getTemperature");
		return 0;
	}

	@Override
	public int getThunderDuration()
	{
		FakeDebug("getThunderDuration");
		return 0;
	}

	@Override
	public long getTicksPerAnimalSpawns()
	{
		FakeDebug("getTicksPerAnimalSpawns");
		return 0;
	}

	@Override
	public long getTicksPerMonsterSpawns()
	{
		FakeDebug("getTicksPerMonsterSpawns");
		return 0;
	}

	@Override
	public long getTime()
	{
		FakeDebug("getTime");
		return 0;
	}

	@Override
	public UUID getUID()
	{
		FakeDebug("getUID");
		return null;
	}

	@Override
	public int getWaterAnimalSpawnLimit()
	{
		FakeDebug("getWaterAnimalSpawnLimit");
		return 0;
	}

	@Override
	public int getWeatherDuration()
	{
		FakeDebug("getWeatherDuration");
		return 0;
	}

	@Override
	public File getWorldFolder()
	{
		FakeDebug("getWorldFolder");
		return null;
	}

	@Override
	public WorldType getWorldType()
	{
		FakeDebug("getWorldType");
		return null;
	}

	@Override
	public boolean hasStorm()
	{
		FakeDebug("hasStorm");
		return false;
	}

	@Override
	public boolean isAutoSave()
	{
		FakeDebug("isAutoSave");
		return false;
	}

	@Override
	public boolean isChunkInUse(int arg0, int arg1)
	{
		FakeDebug("isChunkInUse");
		return false;
	}

	@Override
	public boolean isChunkLoaded(Chunk arg0)
	{
		FakeDebug("isChunkLoaded");
		return false;
	}

	@Override
	public boolean isChunkLoaded(int arg0, int arg1)
	{
		FakeDebug("isChunkLoaded");
		return false;
	}

	@Override
	public boolean isGameRule(String arg0)
	{
		FakeDebug("isGameRule");
		return false;
	}

	@Override
	public boolean isThundering()
	{
		FakeDebug("isThundering");
		return false;
	}

	@Override
	public void loadChunk(Chunk arg0)
	{
		FakeDebug("loadChunk");
		
	}

	@Override
	public void loadChunk(int arg0, int arg1)
	{
		FakeDebug("loadChunk");
		
	}

	@Override
	public boolean loadChunk(int arg0, int arg1, boolean arg2)
	{
		FakeDebug("loadChunk");
		return false;
	}

	@Override
	public void playEffect(Location arg0, Effect arg1, int arg2)
	{
		FakeDebug("playEffect");
		
	}

	@Override
	public <T> void playEffect(Location arg0, Effect arg1, T arg2)
	{
		FakeDebug("playEffect");
		
	}

	@Override
	public void playEffect(Location arg0, Effect arg1, int arg2, int arg3)
	{
		FakeDebug("playEffect");
		
	}

	@Override
	public <T> void playEffect(Location arg0, Effect arg1, T arg2, int arg3)
	{
		FakeDebug("playEffect");
		
	}

	@Override
	public void playSound(Location arg0, Sound arg1, float arg2, float arg3)
	{
		FakeDebug("playSound");
		
	}

	@Override
	public boolean refreshChunk(int arg0, int arg1)
	{
		FakeDebug("refreshChunk");
		return false;
	}

	@Override
	public boolean regenerateChunk(int arg0, int arg1)
	{
		FakeDebug("regenerateChunk");
		return false;
	}

	@Override
	public void save()
	{
		FakeDebug("save");
		
	}

	@Override
	public void setAmbientSpawnLimit(int arg0)
	{
		FakeDebug("setAmbientSpawnLimit");
		
	}

	@Override
	public void setAnimalSpawnLimit(int arg0)
	{
		FakeDebug("setAnimalSpawnLimit");
		
	}

	@Override
	public void setAutoSave(boolean arg0)
	{
		FakeDebug("setAutoSave");
		
	}

	@Override
	public void setBiome(int arg0, int arg1, Biome arg2)
	{
		FakeDebug("setBiome");
		
	}

	@Override
	public void setDifficulty(Difficulty arg0)
	{
		FakeDebug("setDifficulty");
		
	}

	@Override
	public void setFullTime(long arg0)
	{
		FakeDebug("setFullTime");
		
	}

	@Override
	public boolean setGameRuleValue(String arg0, String arg1)
	{
		FakeDebug("setGameRuleValue");
		return false;
	}

	@Override
	public void setKeepSpawnInMemory(boolean arg0)
	{
		FakeDebug("setKeepSpawnInMemory");
		
	}

	@Override
	public void setMonsterSpawnLimit(int arg0)
	{
		FakeDebug("setMonsterSpawnLimit");
		
	}

	@Override
	public void setPVP(boolean arg0)
	{
		FakeDebug("setPVP");
		
	}

	@Override
	public void setSpawnFlags(boolean arg0, boolean arg1)
	{
		FakeDebug("setSpawnFlags");
		
	}

	@Override
	public boolean setSpawnLocation(int arg0, int arg1, int arg2)
	{
		FakeDebug("setSpawnLocation");
		return false;
	}

	@Override
	public void setStorm(boolean arg0)
	{
		FakeDebug("setStorm");
		
	}

	@Override
	public void setThunderDuration(int arg0)
	{
		FakeDebug("setThunderDuration");
		
	}

	@Override
	public void setThundering(boolean arg0)
	{
		FakeDebug("setThundering");
		
	}

	@Override
	public void setTicksPerAnimalSpawns(int arg0)
	{
		FakeDebug("setTicksPerAnimalSpawns");
		
	}

	@Override
	public void setTicksPerMonsterSpawns(int arg0)
	{
		FakeDebug("setTicksPerMonsterSpawns");
		
	}

	@Override
	public void setTime(long arg0)
	{
		FakeDebug("setTime");
		
	}

	@Override
	public void setWaterAnimalSpawnLimit(int arg0)
	{
		FakeDebug("setWaterAnimalSpawnLimit");
		
	}

	@Override
	public void setWeatherDuration(int arg0)
	{
		FakeDebug("setWeatherDuration");
		
	}

	@Override
	public <T extends Entity> T spawn(Location arg0, Class<T> arg1) throws IllegalArgumentException
	{
		FakeDebug("spawn");
		return null;
	}

	@Override
	public Arrow spawnArrow(Location arg0, Vector arg1, float arg2, float arg3)
	{
		FakeDebug("spawnArrow");
		return null;
	}

	@Override
	public LivingEntity spawnCreature(Location arg0, EntityType arg1)
	{
		FakeDebug("spawnCreature");
		return null;
	}

	@Override
	public LivingEntity spawnCreature(Location arg0, CreatureType arg1)
	{
		FakeDebug("spawnCreature");
		return null;
	}

	@Override
	public Entity spawnEntity(Location arg0, EntityType arg1)
	{
		FakeDebug("spawnEntity");
		return null;
	}

	@Override
	public FallingBlock spawnFallingBlock(Location arg0, Material arg1, byte arg2) throws IllegalArgumentException
	{
		FakeDebug("spawnFallingBlock");
		return null;
	}

	@Override
	public FallingBlock spawnFallingBlock(Location arg0, int arg1, byte arg2) throws IllegalArgumentException
	{
		FakeDebug("spawnFallingBlock");
		return null;
	}

	@Override
	public LightningStrike strikeLightning(Location arg0)
	{
		FakeDebug("strikeLightning");
		return null;
	}

	@Override
	public LightningStrike strikeLightningEffect(Location arg0)
	{
		FakeDebug("strikeLightningEffect");
		return null;
	}

	@Override
	public boolean unloadChunk(Chunk arg0)
	{
		FakeDebug("unloadChunk");
		return false;
	}

	@Override
	public boolean unloadChunk(int arg0, int arg1)
	{
		FakeDebug("unloadChunk");
		return false;
	}

	@Override
	public boolean unloadChunk(int arg0, int arg1, boolean arg2)
	{
		FakeDebug("unloadChunk");
		return false;
	}

	@Override
	public boolean unloadChunk(int arg0, int arg1, boolean arg2, boolean arg3)
	{
		FakeDebug("unloadChunk");
		return false;
	}

	@Override
	public boolean unloadChunkRequest(int arg0, int arg1)
	{
		FakeDebug("unloadChunkRequest");
		return false;
	}

	@Override
	public boolean unloadChunkRequest(int arg0, int arg1, boolean arg2)
	{
		FakeDebug("unloadChunkRequest");
		return false;
	}

}
