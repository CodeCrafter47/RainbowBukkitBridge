package PluginBukkitBridge;

import PluginBukkitBridge.block.FakeBlock;
import PluginBukkitBridge.entity.FakeFallingBlock;
import PluginReference.*;
import com.google.common.base.Charsets;
import org.apache.commons.lang.Validate;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.metadata.BlockMetadataStore;
import org.bukkit.entity.*;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.*;

public class FakeWorld implements World {
    public MC_World world = null;
    public BlockMetadataStore blockMetadataStore;

    public FakeWorld(MC_World world) {
        this.world = world;
        blockMetadataStore = new BlockMetadataStore(this);
    }

    @Override
    public Block getBlockAt(int x, int y, int z) {
        return new FakeBlock(x, y, z, world);
    }

    @Override
    public Block getBlockAt(Location location) {
        return getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());

    }

    @Override
    public int getBlockTypeIdAt(int x, int y, int z) {
        return getBlockAt(x, y, z).getTypeId();
    }

    @Override
    public int getBlockTypeIdAt(Location location) {
        return getBlockTypeIdAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public boolean isChunkLoaded(Chunk chunk) {
        return isChunkLoaded(chunk.getX(), chunk.getZ());
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        return world.isChunkLoaded(x, z);
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> list = new ArrayList<>();
        for (MC_Player player : MyPlugin.server.getPlayers()) {
            if (player.getWorld().getName().equals(world.getName())) list.add(PlayerManager.getPlayer(player));
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
        for (Player player : getPlayers()) {
            player.playSound(location, sound, volume, pitch);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FakeWorld)) return false;
        return world.getName().equals(((FakeWorld) o).world.getName());
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return Bukkit.getServer().getListeningPluginChannels();
    }

    @Override
    public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2) {
        for (Player player : getPlayers()) {
            player.sendPluginMessage(arg0, arg1, arg2);
        }
    }

    @Override
    public List<MetadataValue> getMetadata(String arg0) {
        return MyPlugin.worldMetadataStorage.getMetadata(this, arg0);
    }

    @Override
    public boolean hasMetadata(String arg0) {
        return MyPlugin.worldMetadataStorage.hasMetadata(this, arg0);
    }

    @Override
    public void removeMetadata(String arg0, Plugin arg1) {
        MyPlugin.worldMetadataStorage.removeMetadata(this, arg0, arg1);
    }

    @Override
    public void setMetadata(String arg0, MetadataValue arg1) {
        MyPlugin.worldMetadataStorage.setMetadata(this, arg0, arg1);
    }

    @Override
    public boolean canGenerateStructures() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean createExplosion(Location arg0, float arg1) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean createExplosion(Location arg0, float arg1, boolean arg2) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean createExplosion(double arg0, double arg1, double arg2, float arg3) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean createExplosion(double arg0, double arg1, double arg2, float arg3, boolean arg4) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean createExplosion(double arg0, double arg1, double arg2, float arg3, boolean arg4, boolean arg5) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public Item dropItem(Location arg0, ItemStack arg1) {
        return (Item) Util.wrapEntity(world.dropItem(Util.getItemStack(arg1), Util.getLocation(arg0), null));
    }

    @Override
    public Item dropItemNaturally(Location arg0, ItemStack arg1) {
        return (Item) Util.wrapEntity(world.dropItem(Util.getItemStack(arg1), Util.getLocation(arg0), null));
    }

    @Override
    public boolean generateTree(Location arg0, TreeType arg1) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean generateTree(Location arg0, TreeType arg1, BlockChangeDelegate arg2) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean getAllowAnimals() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean getAllowMonsters() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public int getAmbientSpawnLimit() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int getAnimalSpawnLimit() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public Biome getBiome(int arg0, int arg1) {
        return Util.wrapBiome(world.getBiomeTypeAt(arg0, arg1));
    }

    @Override
    public Chunk getChunkAt(Location arg0) {
        return getChunkAt(Location.locToBlock(((double)arg0.getBlockX()) / 16D), Location.locToBlock(((double)arg0.getBlockZ()) / 16D));
    }

    @Override
    public Chunk getChunkAt(Block arg0) {
        return getChunkAt(arg0.getLocation());
    }

    @Override
    public Chunk getChunkAt(int arg0, int arg1) {
        return new FakeChunk(arg0, arg1, this);
    }

    @Override
    public Difficulty getDifficulty() {
		try{
			return Difficulty.valueOf(ReflectionUtil.getProperty("difficulty"));
		}catch(Exception ignored){

		}
		try{
			return Difficulty.getByValue(Integer.valueOf(ReflectionUtil.getProperty("difficulty")));
		}catch(Exception ignored){

		}
        MyPlugin.fixme();
        return Difficulty.NORMAL;
    }

    @Override
    public ChunkSnapshot getEmptyChunkSnapshot(int arg0, int arg1, boolean arg2, boolean arg3) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public List<Entity> getEntities() {
        ArrayList<Entity> arr = new ArrayList<Entity>();
        for (MC_Entity ent : world.getEntities()) arr.add(Util.wrapEntity(ent));
        return arr;
    }

    @Override
    public <T extends Entity> Collection<T> getEntitiesByClass(Class<T>... classes) {
        return (Collection<T>) getEntitiesByClasses(classes);
    }

    @Override
    public <T extends Entity> Collection<T> getEntitiesByClass(Class<T> clazz) {
        Collection<T> list = new ArrayList<T>();

        for (Entity bukkitEntity : getEntities()) {
            Class<?> bukkitClass = bukkitEntity.getClass();

            if (clazz.isAssignableFrom(bukkitClass)) {
                list.add((T) bukkitEntity);
            }
        }

        return list;
    }

    @Override
    public Collection<Entity> getEntitiesByClasses(Class<?>... classes) {
        Collection<Entity> list = new ArrayList<Entity>();

        for (Entity bukkitEntity : getEntities()) {
            Class<?> bukkitClass = bukkitEntity.getClass();

            for (Class<?> clazz : classes) {
                if (clazz.isAssignableFrom(bukkitClass)) {
                    list.add(bukkitEntity);
                    break;
                }
            }

        }

        return list;
    }

    @Override
    public Environment getEnvironment() {
        if (getName().contains("_nether")) return Environment.NETHER;
        if (getName().contains("_the_end")) return Environment.THE_END;
        return Environment.NORMAL;
    }

    @Override
    public long getFullTime() {
        return world.getGameTime();
    }

    @Override
    public String getGameRuleValue(String arg0) {
        return String.valueOf(world.getGameRuleBool(Util.getGameRule(arg0)));
    }

    @Override
    public String[] getGameRules() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public ChunkGenerator getGenerator() {
        return new FakeChunkGenerator();
    }

    @Override
    public Block getHighestBlockAt(Location arg0) {
        return getHighestBlockAt(arg0.getBlockX(), arg0.getBlockZ());
    }

    @Override
    public Block getHighestBlockAt(int x, int z) {
        return getBlockAt(x, getHighestBlockYAt(x, z), z);
    }

    @Override
    public int getHighestBlockYAt(Location arg0) {
        return getHighestBlockYAt(arg0.getBlockX(), arg0.getBlockZ());
    }

    @Override
    public int getHighestBlockYAt(int x, int z) {
        for (int i = 255; i >= 0; i--) {
            if (world.getBlockAt(x, i, z) != null && world.getBlockAt(x, i, z).getId() != 0) return i;
        }
        return 0;
    }

    @Override
    public double getHumidity(int arg0, int arg1) {
        // fixme what the hell is that?
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public boolean getKeepSpawnInMemory() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public List<LivingEntity> getLivingEntities() {
        List<LivingEntity> l = new ArrayList<>();
        for (Entity e : getEntities()) {
            if (e instanceof LivingEntity) {
                l.add((LivingEntity) e);
            }
        }
        return l;
    }

    @Override
    public Chunk[] getLoadedChunks() {
		List<MC_Chunk> loadedChunks = world.getLoadedChunks();
		Chunk[] chunks = new Chunk[loadedChunks.size()];
		for (int i = 0; i < loadedChunks.size(); i++) {
			MC_Chunk mc_chunk = loadedChunks.get(i);
			chunks[i] = new FakeChunk(mc_chunk.getCX(), mc_chunk.getCZ(), this);
		}
		return chunks;
    }

    @Override
    public int getMonsterSpawnLimit() {
        MyPlugin.fixme();
        return -1;
    }

    @Override
    public String getName() {
        return world.getName();
    }

    @Override
    public boolean getPVP() {
        return Boolean.valueOf(ReflectionUtil.getProperty("pvp"));
    }

    @Override
    public List<BlockPopulator> getPopulators() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public int getSeaLevel() {
        MyPlugin.fixme();
        return 64;
    }

    @Override
    public long getSeed() {
        return ReflectionUtil.getWorldSeed(world);
    }

    @Override
    public double getTemperature(int arg0, int arg1) {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int getThunderDuration() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public long getTicksPerAnimalSpawns() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public long getTicksPerMonsterSpawns() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public long getTime() {
        return world.getDayTime();
    }

    @Override
    public UUID getUID() {
        return UUID.nameUUIDFromBytes(("FakeWorld:" + getName()).getBytes(Charsets.UTF_8));
    }

    @Override
    public int getWaterAnimalSpawnLimit() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int getWeatherDuration() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public File getWorldFolder() {
        return new File(Bukkit.getServer().getWorldContainer(), getName());
    }

    @Override
    public WorldType getWorldType() {
		// What else should it be...
        return WorldType.NORMAL;
    }

    @Override
    public boolean hasStorm() {
        return ReflectionUtil.hasStorm(world);
    }

    @Override
    public boolean isAutoSave() {
        MyPlugin.fixme();
        return true;
    }

    @Override
    public boolean isChunkInUse(int arg0, int arg1) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isGameRule(String arg0) {
        return MC_GameRuleType.valueOf(arg0) != null;
    }

    @Override
    public Spigot spigot() {
        return new Spigot(){
            @Override
            public void playEffect(Location location, Effect effect) {
                MyPlugin.fixme();
            }

            @Override
            public void playEffect(Location location, Effect effect, int id, int data, float offsetX, float offsetY, float offsetZ, float speed, int particleCount, int radius) {
                MyPlugin.fixme();
            }

            @Override
            public LightningStrike strikeLightning(Location loc, boolean isSilent) {
                MyPlugin.fixme();
                return null;
            }

            @Override
            public LightningStrike strikeLightningEffect(Location loc, boolean isSilent) {
                MyPlugin.fixme();
                return null;
            }
        };
    }

    @Override
    public WorldBorder getWorldBorder() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean isThundering() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void loadChunk(Chunk arg0) {
        loadChunk(arg0.getX(), arg0.getZ());
    }

    @Override
    public void loadChunk(int arg0, int arg1) {
        loadChunk(arg0, arg1, false);
    }

    @Override
    public boolean loadChunk(int arg0, int arg1, boolean ignored) {
		world.loadChunk(arg0, arg1);
        return true;
    }

    @Override
    public void playEffect(Location arg0, Effect arg1, int arg2) {
        for (Player player: getPlayers())player.playEffect(arg0, arg1, arg2);
    }

    @Override
    public <T> void playEffect(Location arg0, Effect arg1, T arg2) {
        for (Player player: getPlayers())player.playEffect(arg0, arg1, arg2);
    }

    @Override
    public void playEffect(Location arg0, Effect arg1, int arg2, int arg3) {
        for (Player player: getPlayers())if(player.getLocation().distance(arg0) <= arg3)player.playEffect(arg0, arg1, arg2);
    }

    @Override
    public <T> void playEffect(Location arg0, Effect arg1, T arg2, int arg3) {
        for (Player player: getPlayers())if(player.getLocation().distance(arg0) <= arg3)player.playEffect(arg0, arg1, arg2);
    }

    @Override
    public boolean refreshChunk(int arg0, int arg1) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean regenerateChunk(int arg0, int arg1) {
        ReflectionUtil.regenChunk(world, arg0, arg1);
        return true;
    }

    @Override
    public void save() {
        MyPlugin.fixme();
    }

    @Override
    public void setAmbientSpawnLimit(int arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setAnimalSpawnLimit(int arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setAutoSave(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setBiome(int arg0, int arg1, Biome arg2) {
        world.setBiomeTypeAt(arg0, arg1, Util.wrapBiome(arg2));
    }

    @Override
    public void setDifficulty(Difficulty arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setFullTime(long arg0) {
        MyPlugin.fixme();
    }

    @Override
    public boolean setGameRuleValue(String arg0, String arg1) {
        if(!isGameRule(arg0))return false;
        world.setGameRule(Util.getGameRule(arg0), Boolean.valueOf(arg1));
        return true;
    }

    @Override
    public void setKeepSpawnInMemory(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setMonsterSpawnLimit(int arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setPVP(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setSpawnFlags(boolean arg0, boolean arg1) {
        MyPlugin.fixme();
    }

    @Override
    public boolean setSpawnLocation(int arg0, int arg1, int arg2) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void setStorm(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setThunderDuration(int arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setThundering(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setTicksPerAnimalSpawns(int arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setTicksPerMonsterSpawns(int arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setTime(long arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setWaterAnimalSpawnLimit(int arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setWeatherDuration(int arg0) {
        MyPlugin.fixme();
    }

    @Override
    public <T extends Entity> T spawn(Location location, Class<T> clazz) throws IllegalArgumentException {
        return spawn(location, clazz, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }

    @Override
    public Arrow spawnArrow(Location loc, Vector velocity, float speed, float spread) {
        Validate.notNull(loc, "Can not spawn arrow with a null location");
        Validate.notNull(velocity, "Can not spawn arrow with a null velocity");

        Arrow a = (Arrow) world.spawnEntity(MC_EntityType.ARROW, Util.getLocation(loc), null);
        a.setVelocity(velocity);
        return a;
    }

    @Override
    public LivingEntity spawnCreature(Location arg0, EntityType arg1) {
        return (LivingEntity) world.spawnEntity(Util.getEntityType(arg1), Util.getLocation(arg0), null);
    }

    @Override
    public LivingEntity spawnCreature(Location loc, CreatureType creatureType) {
        return spawnCreature(loc, creatureType.toEntityType());
    }

    @Override
    public Entity spawnEntity(Location loc, EntityType entityType) {
        return spawn(loc, entityType.getEntityClass());
    }

    @SuppressWarnings("unchecked")
    public <T extends Entity> T spawn(Location location, Class<T> clazz, CreatureSpawnEvent.SpawnReason reason) throws IllegalArgumentException {
        if (location == null || clazz == null) {
            throw new IllegalArgumentException("Location or entity class cannot be null");
        }

        MC_Entity entity = null;

        // order is important for some of these
        if (Boat.class.isAssignableFrom(clazz)) {
            entity = world.spawnEntity(MC_EntityType.BOAT, Util.getLocation(location), null);
        } else if (FallingBlock.class.isAssignableFrom(clazz)) {
            entity = world.spawnEntity(MC_EntityType.FALLING_SAND, Util.getLocation(location), null);
        } else if (Projectile.class.isAssignableFrom(clazz)) {
            if (Snowball.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.SNOWBALL, Util.getLocation(location), null);
            } else if (Egg.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.THROWN_EGG, Util.getLocation(location), null);
            } else if (Arrow.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.ARROW, Util.getLocation(location), null);
            } else if (ThrownExpBottle.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.THROWN_EXP_BOTTLE, Util.getLocation(location), null);
            } else if (EnderPearl.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.THROWN_ENDERPEARL, Util.getLocation(location), null);
            } else if (ThrownPotion.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.THROWN_POTION, Util.getLocation(location), null);
            } else if (Fireball.class.isAssignableFrom(clazz)) {
                if (SmallFireball.class.isAssignableFrom(clazz)) {
                    entity = world.spawnEntity(MC_EntityType.SMALL_FIREBALL, Util.getLocation(location), null);
                } else if (WitherSkull.class.isAssignableFrom(clazz)) {
                    // fixme
                } else {
                    entity = world.spawnEntity(MC_EntityType.FIREBALL, Util.getLocation(location), null);
                }
            }
        } else if (Minecart.class.isAssignableFrom(clazz)) {
            if (PoweredMinecart.class.isAssignableFrom(clazz)) {
                // fixme
            } else if (StorageMinecart.class.isAssignableFrom(clazz)) {
                // fixme
            } else if (ExplosiveMinecart.class.isAssignableFrom(clazz)) {
                // fixme
            } else if (HopperMinecart.class.isAssignableFrom(clazz)) {
                // fixme
            } else if (SpawnerMinecart.class.isAssignableFrom(clazz)) {
                // fixme
            } else { // Default to rideable minecart for pre-rideable compatibility
                entity = world.spawnEntity(MC_EntityType.MINECART, Util.getLocation(location), null);
            }
        } else if (EnderSignal.class.isAssignableFrom(clazz)) {
            entity = world.spawnEntity(MC_EntityType.EYE_OF_ENDER_SIGNAL, Util.getLocation(location), null);
        } else if (EnderCrystal.class.isAssignableFrom(clazz)) {
            entity = world.spawnEntity(MC_EntityType.ENDER_CRYSTAL, Util.getLocation(location), null);
        } else if (LivingEntity.class.isAssignableFrom(clazz)) {
            if (Chicken.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.CHICKEN, Util.getLocation(location), null);
            } else if (Cow.class.isAssignableFrom(clazz)) {
                if (MushroomCow.class.isAssignableFrom(clazz)) {
                    entity = world.spawnEntity(MC_EntityType.MUSHROOM_COW, Util.getLocation(location), null);
                } else {
                    entity = world.spawnEntity(MC_EntityType.COW, Util.getLocation(location), null);
                }
            } else if (Golem.class.isAssignableFrom(clazz)) {
                if (Snowman.class.isAssignableFrom(clazz)) {
                    entity = world.spawnEntity(MC_EntityType.SNOWMAN, Util.getLocation(location), null);
                } else if (IronGolem.class.isAssignableFrom(clazz)) {
                    entity = world.spawnEntity(MC_EntityType.VILLAGER_GOLEM, Util.getLocation(location), null);
                }
            } else if (Creeper.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.CREEPER, Util.getLocation(location), null);
            } else if (Ghast.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.GHAST, Util.getLocation(location), null);
            } else if (Pig.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.PIG, Util.getLocation(location), null);
            } else if (Player.class.isAssignableFrom(clazz)) {
                // need a net server handler for this one
            } else if (Sheep.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.SHEEP, Util.getLocation(location), null);
            } else if (Horse.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.HORSE, Util.getLocation(location), null);
            } else if (Skeleton.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.SKELETON, Util.getLocation(location), null);
            } else if (Slime.class.isAssignableFrom(clazz)) {
                if (MagmaCube.class.isAssignableFrom(clazz)) {
                    entity = world.spawnEntity(MC_EntityType.LAVA_SLIME, Util.getLocation(location), null);
                } else {
                    entity = world.spawnEntity(MC_EntityType.SLIME, Util.getLocation(location), null);
                }
            } else if (Spider.class.isAssignableFrom(clazz)) {
                if (CaveSpider.class.isAssignableFrom(clazz)) {
                    entity = world.spawnEntity(MC_EntityType.CAVE_SPIDER, Util.getLocation(location), null);
                } else {
                    entity = world.spawnEntity(MC_EntityType.SPIDER, Util.getLocation(location), null);
                }
            } else if (Squid.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.SQUID, Util.getLocation(location), null);
            } else if (Tameable.class.isAssignableFrom(clazz)) {
                if (Wolf.class.isAssignableFrom(clazz)) {
                    entity = world.spawnEntity(MC_EntityType.WOLF, Util.getLocation(location), null);
                } else if (Ocelot.class.isAssignableFrom(clazz)) {
                    entity = world.spawnEntity(MC_EntityType.OCELOT, Util.getLocation(location), null);
                }
            } else if (PigZombie.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.PIG_ZOMBIE, Util.getLocation(location), null);
            } else if (Zombie.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.ZOMBIE, Util.getLocation(location), null);
            } else if (Giant.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.GIANT, Util.getLocation(location), null);
            } else if (Silverfish.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.SILVERFISH, Util.getLocation(location), null);
            } else if (Enderman.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.ENDERMAN, Util.getLocation(location), null);
            } else if (Blaze.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.BLAZE, Util.getLocation(location), null);
            } else if (Villager.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.VILLAGER, Util.getLocation(location), null);
            } else if (Witch.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.WITCH, Util.getLocation(location), null);
            } else if (Wither.class.isAssignableFrom(clazz)) {
                entity = world.spawnEntity(MC_EntityType.WITHERBOSS, Util.getLocation(location), null);
            } else if (ComplexLivingEntity.class.isAssignableFrom(clazz)) {
                if (EnderDragon.class.isAssignableFrom(clazz)) {
                    entity = world.spawnEntity(MC_EntityType.ENDERDRAGON, Util.getLocation(location), null);
                }
            } else if (Ambient.class.isAssignableFrom(clazz)) {
                if (Bat.class.isAssignableFrom(clazz)) {
                    entity = world.spawnEntity(MC_EntityType.BAT, Util.getLocation(location), null);
                }
            }
        } else if (Hanging.class.isAssignableFrom(clazz)) {
            /*
            Block block = getBlockAt(location);
            BlockFace face = BlockFace.SELF;
            if (block.getRelative(BlockFace.EAST).getTypeId() == 0) {
                face = BlockFace.EAST;
            } else if (block.getRelative(BlockFace.NORTH).getTypeId() == 0) {
                face = BlockFace.NORTH;
            } else if (block.getRelative(BlockFace.WEST).getTypeId() == 0) {
                face = BlockFace.WEST;
            } else if (block.getRelative(BlockFace.SOUTH).getTypeId() == 0) {
                face = BlockFace.SOUTH;
            }
            int dir;
            switch (face) {
                case SOUTH:
                default:
                    dir = 0;
                    break;
                case WEST:
                    dir = 1;
                    break;
                case NORTH:
                    dir = 2;
                    break;
                case EAST:
                    dir = 3;
                    break;
            }

            if (Painting.class.isAssignableFrom(clazz)) {
                entity = new EntityPainting(world, (int) x, (int) y, (int) z, dir);
            } else if (ItemFrame.class.isAssignableFrom(clazz)) {
                entity = new EntityItemFrame(world, (int) x, (int) y, (int) z, dir);
            } else if (LeashHitch.class.isAssignableFrom(clazz)) {
                entity = new EntityLeash(world, (int) x, (int) y, (int) z);
                entity.attachedToPlayer = true;
            }

            if (entity != null && !((EntityHanging) entity).survives()) {
                throw new IllegalArgumentException("Cannot spawn hanging entity for " + clazz.getName() + " at " + location);
            }
            */
            // fixme
        } else if (TNTPrimed.class.isAssignableFrom(clazz)) {
            entity = world.spawnEntity(MC_EntityType.PRIMED_TNT, Util.getLocation(location), null);
        } else if (ExperienceOrb.class.isAssignableFrom(clazz)) {
            entity = world.spawnEntity(MC_EntityType.XP_ORB, Util.getLocation(location), null);
        } else if (Weather.class.isAssignableFrom(clazz)) {
            // not sure what this can do
            if (LightningStrike.class.isAssignableFrom(clazz)) {
                // fixme
                // what is this, I don't even
            }
        } else if (Firework.class.isAssignableFrom(clazz)) {
            entity = world.spawnEntity(MC_EntityType.FIREWORK, Util.getLocation(location), null);
        }

        if(entity != null)return (T) Util.wrapEntity(entity);

        throw new IllegalArgumentException("Cannot spawn an entity for " + clazz.getName());
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, Material material, byte data) throws IllegalArgumentException {
        Validate.notNull(location, "Location cannot be null");
        Validate.notNull(material, "Material cannot be null");
        Validate.isTrue(material.isBlock(), "Material must be a block");

        double x = location.getBlockX() + 0.5;
        double y = location.getBlockY() + 0.5;
        double z = location.getBlockZ() + 0.5;

        FakeFallingBlock entity = (FakeFallingBlock) Util.wrapEntity(world.spawnEntity(MC_EntityType.FALLING_SAND, Util.getLocation(location), null));

        // fixme apply material + data

        return entity;
    }

    @Override
    public FallingBlock spawnFallingBlock(Location arg0, int arg1, byte arg2) throws IllegalArgumentException {
        return spawnFallingBlock(arg0, Material.getMaterial(arg1), arg2);
    }

    @Override
    public LightningStrike strikeLightning(Location arg0) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public LightningStrike strikeLightningEffect(Location arg0) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean unloadChunk(Chunk arg0) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean unloadChunk(int arg0, int arg1) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean unloadChunk(int arg0, int arg1, boolean arg2) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean unloadChunk(int arg0, int arg1, boolean arg2, boolean arg3) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean unloadChunkRequest(int arg0, int arg1) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean unloadChunkRequest(int arg0, int arg1, boolean arg2) {
        MyPlugin.fixme();
        return false;
    }

}
