package PluginBukkitBridge;

import PluginReference.MC_Entity;
import PluginReference.MC_ItemStack;
import PluginReference.MC_Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
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
