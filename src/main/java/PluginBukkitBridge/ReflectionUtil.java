package PluginBukkitBridge;

import PluginReference.MC_Entity;
import PluginReference.MC_Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Level;

/**
 * Created by florian on 12.10.14.
 */
public class ReflectionUtil {

    public static float getEntityHeight(MC_Entity entity) {
        try {
            Field f_mcEntity = entity.getClass().getDeclaredField("ent");
            f_mcEntity.setAccessible(true);
            Object mcEntity = f_mcEntity.get(entity);
            Field f_height = mcEntity.getClass().getDeclaredField("height");
            f_height.setAccessible(true);
            Object height = f_height.get(mcEntity);
            return (float) height;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: getEntityHeight", e);
        }
        return 0;
    }

    public static int getEntityID(MC_Entity entity) {
        try {
            Field f_mcEntity = entity.getClass().getDeclaredField("ent");
            f_mcEntity.setAccessible(true);
            Object mcEntity = f_mcEntity.get(entity);
            Field f_height = mcEntity.getClass().getDeclaredField("entityID");
            f_height.setAccessible(true);
            Object height = f_height.get(mcEntity);
            return (int) height;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: getEntityID", e);
        }
        return 0;
    }

    public static UUID getEntityUUID(MC_Entity entity) {
        try {
            Field f_mcEntity = entity.getClass().getDeclaredField("ent");
            f_mcEntity.setAccessible(true);
            Object mcEntity = f_mcEntity.get(entity);
            Method f_height = mcEntity.getClass().getDeclaredMethod("getUUID", null);
            f_height.setAccessible(true);
            Object height = f_height.invoke(mcEntity, null);
            return (UUID) height;
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | NullPointerException e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: getTarget", e);
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
            Class cHelper = Class.forName("WrapperObjects.EntityHelper");
            for (Method m : cHelper.getDeclaredMethods()) {
                if (m.getName().contains("CreateEntityWrapper")) {
                    m.setAccessible(true);
                    return (MC_Entity) m.invoke(null, target);
                }
            }
            return null;
        } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException | InvocationTargetException | NullPointerException e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: getTarget", e);
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
        } catch (NoSuchFieldException | IllegalAccessException | NullPointerException e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: setTarget", e);
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
            Class cItem = Class.forName("joebkt.kk");
            Object item = cItem.getDeclaredConstructors()[0].newInstance(playerListItem, getMember(Class.forName("joebkt.EntityHuman"), getMember(uuid, "plr"), "gameProf"), 0, null, text);
            setMember(playerListItem, "b", Arrays.asList(new Object[]{item}));
            Class cPacketBase = Class.forName("joebkt.PacketBase");
            Object playerConnection = getMember(getMember(receiver, "plr"), "plrConnection");
            Object packetHandler = getMember(playerConnection, "a");
            Method m = packetHandler.getClass().getDeclaredMethod("a", new Class[]{cPacketBase});
            m.setAccessible(true);
            m.invoke(packetHandler, playerListItem);
        } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
            MyPlugin.logger.log(Level.WARNING, "Reflection failed: sendPlayerListItemChangeDisplayName", e);
        }
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