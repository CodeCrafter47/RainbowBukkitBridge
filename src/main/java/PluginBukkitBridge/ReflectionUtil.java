package PluginBukkitBridge;

import PluginReference.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;

/**
 * Created by florian on 12.10.14.
 */
public class ReflectionUtil {

    public static float getEntityHeight(MC_Entity entity) {
        try {
            Object mcEntity = getMember(Class.forName("WrapperObjects.Entities.EntityWrapper"), entity, "ent");
            return (float) getMember(Class.forName("joebkt.EntityGeneric"), mcEntity, "height");
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: getEntityHeight", MyPlugin.DebugMode?e:null);
        }
        return 0;
    }

    public static MC_ItemStack getItemStackOfEntityItem(MC_Entity entity){
        try {
            Object mcEntity = getMember(Class.forName("WrapperObjects.Entities.EntityWrapper"), entity, "ent");
            Method method = Class.forName("joebkt.EntityGeneric").getDeclaredMethod("getItemStack", null);
            method.setAccessible(true);
            Object mcItemStack = method.invoke(mcEntity, null);
            MC_ItemStack is = MyPlugin.server.createItemStack(1, 1, 1);
            is.getClass().getDeclaredField("is").set(is, mcItemStack);
            return is;
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: getItemStackOfEntityItem", MyPlugin.DebugMode?e:null);
        }
        return null;
    }

    public static UUID getEntityUUID(MC_Entity entity) {
        try {
            Object mcEntity = getMember(Class.forName("WrapperObjects.Entities.EntityWrapper"), entity, "ent");
            Method f_height = Class.forName("joebkt.EntityGeneric").getDeclaredMethod("getUUID", null);
            f_height.setAccessible(true);
            Object height = f_height.invoke(mcEntity, null);
            return (UUID) height;
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: getTarget", MyPlugin.DebugMode?e:null);
        }
        return null;
    }

    public static MC_Entity getTarget(MC_Entity entity) {
        try {
            Field f_mcEntity = entity.getClass().getDeclaredField("ent");
            f_mcEntity.setAccessible(true);
            Object mcEntity = f_mcEntity.get(entity);
            Field f_target = mcEntity.getClass().getDeclaredField("c");
            f_target.setAccessible(true);
            Object target = f_target.get(mcEntity);
            Class cHelper = Class.forName("WrapperObjects.Entities.EntityHelper");
            for (Method m : cHelper.getDeclaredMethods()) {
                if (m.getName().contains("CreateEntityWrapper")) {
                    m.setAccessible(true);
                    return (MC_Entity) m.invoke(null, target);
                }
            }
            return null;
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: getTarget", MyPlugin.DebugMode?e:null);
        }
        return null;
    }

    public static void setTarget(MC_Entity entity, MC_Entity target) {
        try {
            Field f_mcEntity = entity.getClass().getDeclaredField("ent");
            f_mcEntity.setAccessible(true);
            Object mcEntity = f_mcEntity.get(entity);
            Object mcTarget = f_mcEntity.get(target);
            Field f_target = mcEntity.getClass().getDeclaredField("c");
            f_target.setAccessible(true);
            f_target.set(mcEntity, mcTarget);
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: setTarget", MyPlugin.DebugMode?e:null);
        }
    }

    public static void sendPluginMessage(MC_Player player, String tag, byte[] data){
        try {
            Object byteBuf = Class.forName("io.netty.buffer.Unpooled").getDeclaredMethod("wrappedBuffer", byte[].class).invoke(null, data);
            Object byteData = Class.forName("joebkt.ByteData").getDeclaredConstructor(Class.forName("io.netty.buffer.ByteBuf")).newInstance(byteBuf);
            Object packet = Class.forName("joebkt.Packet_PluginMessage").getDeclaredConstructor(String.class, Class.forName("joebkt.ByteData")).newInstance(tag, byteData);
            sendPacket(player, packet);
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: sendPluginMessage", MyPlugin.DebugMode?e:null);
        }
    }

    public static void sendPlayerListItemChangeDisplayName(MC_Player receiver, MC_Player uuid, String name) {

        try {
            Class cPlayerListItem = Class.forName("joebkt.Packet_PlayerListItem");
            Object playerListItem = cPlayerListItem.getDeclaredConstructor(new Class[0]).newInstance();
            Class cEnumMultiplayStatusChange = Class.forName("joebkt.EnumMultiplayStatusChange");
            Object action = cEnumMultiplayStatusChange.getDeclaredField("UPDATE_DISPLAY_NAME").get(null);
            setMember(playerListItem, "a", action);
            Class cTextComponent = Class.forName("joebkt.TextComponent");
            Object text = cTextComponent.getDeclaredConstructor(String.class).newInstance(name);
            Class cItem = Class.forName("joebkt.kk_PacketListItemRelated");
            Object item = cItem.getDeclaredConstructors()[0].newInstance(playerListItem, getMember(Class.forName("joebkt.EntityHuman"), getMember(uuid, "plr"), "gameProf"), 0, null, text);
            setMember(playerListItem, "b", Arrays.asList(item));
            sendPacket(receiver, playerListItem);
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: sendPlayerListItemChangeDisplayName", MyPlugin.DebugMode?e:null);
        }
    }

    private static void sendPacket(MC_Player receiver, Object packet) throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class cPacketBase = Class.forName("joebkt.PacketBase");
        Object playerConnection = getMember(getMember(receiver, "plr"), "plrConnection");
        Object packetHandler = getMember(playerConnection, "a");
        // joe called this handleIncomingPacket but in fact it sends a packet :D
        Method m = packetHandler.getClass().getDeclaredMethod("handleIncomingPacket", new Class[]{cPacketBase});
        m.setAccessible(true);
        m.invoke(packetHandler, packet);
    }

    public static String getProperty(String key){
        Properties properties;
        try {
            properties = getServerConfig();
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: getServerProperties", MyPlugin.DebugMode ? e : null);
            return null;
        }
        if(!properties.containsKey(key))return null;
        return properties.getProperty(key);
    }

    public static long getWorldSeed(MC_World world){
        try {
            Object mcWorld = getMember(world, "world");
            Method getSeed = Class.forName("joebkt.World").getDeclaredMethod("getSeedNumber");
            getSeed.setAccessible(true);
            return (long) getSeed.invoke(mcWorld);
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: getWorldSeed", MyPlugin.DebugMode ? e : null);
            return 0;
        }
    }

    public static boolean hasStorm(MC_World world){
        try {
            Object mcWorld = getMember(world, "world");
            Object worldData = getMember(Class.forName("joebkt.World"), mcWorld, "worldData");
            Method getIsThundering = Class.forName("joebkt.WorldData").getDeclaredMethod("getIsThundering");
            getIsThundering.setAccessible(true);
            return (boolean) getIsThundering.invoke(worldData);
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: hasStorm", MyPlugin.DebugMode ? e : null);
            return false;
        }
    }

    public static void regenChunk(MC_World world, int x, int z){
        try {
            Object mcWorld = getMember(world, "world");
            Object chunkCache = getMember(Class.forName("joebkt.WorldServer"), mcWorld, "cachedChunks");
            Object chunkProviderServer = getMember(Class.forName("joebkt.ServerChunkCache"), chunkCache, "chunkProvider");
            Method createChunk = Class.forName("joebkt.IChunkProvider").getDeclaredMethod("getOrCreateChunk", int.class, int.class);
            createChunk.setAccessible(true);
            Object chunk = createChunk.invoke(chunkProviderServer, x, z);

            long index = (long) Class.forName("joebkt.ChunkCoordIntPair").getDeclaredMethod("a", int.class, int.class).invoke(null, x, z);
            Object chunkMap = getMember(chunkCache, "loadedChunkMap");
            Object oldChunk = chunkMap.getClass().getDeclaredMethod("get", long.class).invoke(chunkMap, index);
            // fixme remove old entities
            Method removeEntities = Class.forName("joebkt.Chunk").getDeclaredMethod("d");
            removeEntities.invoke(oldChunk);

            chunkMap.getClass().getDeclaredMethod("put", long.class, Object.class).invoke(chunkMap, index, chunk);
            List chunkList = (List) getMember(chunkCache, "loadedChunkList");
            if(chunkList.contains(oldChunk))chunkList.remove(oldChunk);
            chunkList.add(chunk);
            Object wChunkMap = getMember(Class.forName("joebkt.WorldServer"), mcWorld, "m_chunkMap");
            chunkMap = getMember(wChunkMap, "d_longHashMap");
            chunkMap.getClass().getDeclaredMethod("put", long.class, Object.class).invoke(chunkMap, index, chunk);
            chunkList = (List) getMember(wChunkMap, "f");
            if(chunkList.contains(oldChunk))chunkList.remove(oldChunk);
            chunkList.add(chunk);

            // add entities
            Method addEntitiesToWorld = Class.forName("joebkt.Chunk").getDeclaredMethod("addEntitiesToWorld");
            addEntitiesToWorld.invoke(chunk);

            // refresh surrounding chunks maybe?
            Method loadAdjacentChunks = Class.forName("joebkt.Chunk").getDeclaredMethod("loadAdjacentChunks",
                    Class.forName("joebkt.IChunkProvider"), Class.forName("joebkt.IChunkProvider"), int.class, int.class);
            loadAdjacentChunks.invoke(chunk, chunkProviderServer, chunkProviderServer, x, z);

            // refresh the chunk
            int px = x << 4;
            int pz = z << 4;

            // If there are more than 64 updates to a chunk at once, it will update all 'touched' sections within the chunk
            // And will include biome data if all sections have been 'touched'
            // This flags 65 blocks distributed across all the sections of the chunk, so that everything is sent, including biomes
            Method notifyBlockDataChangedAt = Class.forName("joebkt.World").getDeclaredMethod("notifyBlockDataChangedAt", Class.forName("joebkt.IntegerCoordinates"));
            Constructor cooordWrapper = Class.forName("joebkt.IntegerCoordinates").getConstructor(int.class, int.class, int.class);
            int height = 256 / 16;
            for (int idx = 0; idx < 64; idx++) {
                Object coords = cooordWrapper.newInstance(px + (idx / height), ((idx % height) * 16), pz);
                notifyBlockDataChangedAt.invoke(mcWorld, coords);
            }
            Object coords = cooordWrapper.newInstance(px + 15, (height * 16) - 1, pz + 15);
            notifyBlockDataChangedAt.invoke(mcWorld, coords);
            /*
            // copy block by block
            Method getBlock = Class.forName("joebkt.Chunk").getDeclaredMethod("getBlock2", int.class, int.class, int.class);
            Method toBlockState = Class.forName("joebkt.BlockObject").getDeclaredMethod("toBlockState");
            Constructor blockWrapper = Class.forName("WrapperObjects.BlockWrapper").getDeclaredConstructors()[0];
            for(int cx = 0; cx < 16; cx++){
                for(int y = 0; y < 256; y++){
                    for(int cz = 0; cz < 16; cz++){
                        Object block = getBlock.invoke(chunk, cx, y, cz);
                        Object blockState = toBlockState.invoke(block);
                        MC_Block wrappedBlock = (MC_Block) blockWrapper.newInstance(blockState);
                        world.setBlockAt(x*16 + cx, y, z*16 + cz, wrappedBlock, wrappedBlock.getSubtype());
                        System.out.println("set block at " + (x*16+cx) + "," + y + "," + (z*16+cz) + " to " + wrappedBlock.getId());
                    }
                }
            }
            */
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: regenChunk", MyPlugin.DebugMode ? e : null);
        }
    }

    public static int readFurnaceBurnTime(MC_Container furnace){
        try {
            Object mcfurnace = getMember(furnace, "m_inventory");
            return (int) getMember(mcfurnace, "burnTime");
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: readFurnaceBurnTime", MyPlugin.DebugMode ? e : null);
            return 0;
        }
    }

    public static int readFurnaceCookTime(MC_Container furnace){
        try {
            Object mcfurnace = getMember(furnace, "m_inventory");
            return (int) getMember(mcfurnace, "cookTime");
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: readFurnaceCookTime", MyPlugin.DebugMode ? e : null);
            return 0;
        }
    }

    public static void writeFurnaceBurnTime(MC_Container furnace, int burnTime){
        try {
            Object mcfurnace = getMember(furnace, "m_inventory");
            setMember(mcfurnace, "burnTime", burnTime);
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: writeFurnaceBurnTime", MyPlugin.DebugMode ? e : null);
        }
    }

    public static void writeFurnaceCookTime(MC_Container furnace, int cookTime){
        try {
            Object mcfurnace = getMember(furnace, "m_inventory");
            setMember(mcfurnace, "cookTime", cookTime);
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: writeFurnaceCookTime", MyPlugin.DebugMode ? e : null);
        }
    }

    public static String[] getBannedPlayers(){
        try {
            Object banList = getBanList();
            return (String[]) banList.getClass().getDeclaredMethod("a").invoke(banList);
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: getBannedPlayers", MyPlugin.DebugMode ? e : null);
            return new String[0];
        }
    }
/*
    public Collection<Recipe> getRecipes(){
        try {
            Object recipeBook = Class.forName("joebkt.RecipeBook").getDeclaredMethod("getRecipeBook").invoke(null);
            List recipes = (List) recipeBook.getClass().getDeclaredMethod("getCraftingResultEntries").invoke(recipeBook);
            Collection<Recipe> result = new ArrayList<>();
            for(Object o: recipes){
                if(Class.forName("joebkt.CraftingResultEntry").isAssignableFrom(o.getClass())){
                    // shaped recipe
                }
            }
        } catch (Exception e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: getRecipes", MyPlugin.DebugMode ? e : null);
            return new ArrayList<>();
        }
    }*/

    private static Object getBanList() throws Exception{
        return getMember(getMember(getMember(Class.forName("net.minecraft.server.MinecraftServer"), null, "k"), "playerList"), "m_bannedPlayers");
    }

    private static Properties getServerConfig() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (Properties) getMember(getMember(getMember(Class.forName("net.minecraft.server.MinecraftServer"), null, "k"), "serverProperties"), "b");
    }

    private static void setMember(Object o, String name, Object o2) throws IllegalAccessException, NoSuchFieldException {
        Field f = o.getClass().getDeclaredField(name);
        f.setAccessible(true);
        f.set(o, o2);
    }

    private static Object getMember(Object o, String name) throws IllegalAccessException, NoSuchFieldException {
        Field f = o.getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f.get(o);
    }

    private static Object getMember(Class c, Object o, String name) throws IllegalAccessException, NoSuchFieldException {
        Field f = c.getDeclaredField(name);
        f.setAccessible(true);
        return f.get(o);
    }
}
