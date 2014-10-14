package PluginBukkitBridge;

import PluginBukkitBridge.block.FakeBlock;
import PluginReference.MC_Entity;
import PluginReference.MC_Player;
import PluginReference.MC_World;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.*;

public class FakeWorld implements World
{
	public MC_World world = null;
	
	public static void FakeDebug(String msg)
	{
		System.out.println("FakeWorld Proxy: " + msg);
	}

    public FakeWorld(MC_World world) {
        this.world = world;
    }

    @Override
    public Block getBlockAt(int x, int y, int z) {
        return new FakeBlock(x,y,z,world);
    }

    @Override
    public Block getBlockAt(Location location) {
        return getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());

    }

    @Override
    public int getBlockTypeIdAt(int x, int y, int z) {
        return getBlockAt(x,y,z).getTypeId();
    }

    @Override
    public int getBlockTypeIdAt(Location location) {
        return getBlockTypeIdAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public boolean isChunkLoaded(Chunk chunk) {
        // fixme
        // rainbow doesn't care
        return true;
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        // fixme
        // rainbow doesn't care
        return true;
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> list = new ArrayList<>();
        for(MC_Player player: MyPlugin.server.getPlayers()){
            if(player.getWorld().equals(world))list.add(PlayerManager.getPlayer(player));
        }
        return list;
    }

    @Override
    public Location getSpawnLocation() {
        return Util.getLocation(world.getSpawnLocation());
    }

    @Override
    public int getMaxHeight() {
        return MyPlugin.server.getMaxBuildHeight();
    }

    @Override
    public void playSound(Location location, Sound sound, float volume, float pitch) {
        for(Player player: getPlayers()){
            player.playSound(location,sound,volume,pitch);
        }
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof FakeWorld))return false;
        return world.getName().equals(((FakeWorld)o).world.getName());
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
	public Chunk getChunkAt(Location arg0)
	{
		return getChunkAt(arg0.getBlockX()/16, arg0.getBlockZ()/16);
	}

	@Override
	public Chunk getChunkAt(Block arg0)
	{
		return getChunkAt(arg0.getLocation());
	}

	@Override
	public Chunk getChunkAt(int arg0, int arg1)
	{
		return new FakeChunk(arg0, arg1, this);
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
		for(MC_Entity ent : world.getEntities()) arr.add(Util.wrapEntity(ent));
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
        if(getName().contains("_nether"))return Environment.NETHER;
        if(getName().contains("_the_end"))return Environment.THE_END;
        return Environment.NORMAL;
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
        List<LivingEntity> l = new ArrayList<>();
		for(Entity e: getEntities()){
            if(e instanceof LivingEntity){
                l.add((LivingEntity) e);
            }
        }
		return l;
	}

	@Override
	public Chunk[] getLoadedChunks()
	{
		FakeDebug("getLoadedChunks");
		return null;
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
		return world.getName();
	}

	@Override
	public boolean getPVP()
	{
		FakeDebug("getPVP");
		return false;
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
	public boolean isGameRule(String arg0)
	{
		FakeDebug("isGameRule");
		return false;
	}

    @Override
    public void showParticle(Location location, Particle particle, float v, float v2, float v3, float v4, int i) {
        FakeDebug("showParticle");
    }

    @Override
    public void showParticle(Location location, Particle particle, MaterialData materialData, float v, float v2, float v3, float v4, int i) {
        FakeDebug("showParticle");
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
