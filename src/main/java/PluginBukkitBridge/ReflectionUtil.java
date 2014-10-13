package PluginBukkitBridge;

import PluginReference.MC_Entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.logging.Level;

/**
 * Created by florian on 12.10.14.
 */
public class ReflectionUtil {

    public static float getEntityHeight(MC_Entity entity)
    {
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

    public static int getEntityID(MC_Entity entity)
    {
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

    public static UUID getEntityUUID(MC_Entity entity)
    {
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

    public static MC_Entity getTarget(MC_Entity entity)
    {
        try {
            Field f_mcEntity = entity.getClass().getDeclaredField("ent");
            f_mcEntity.setAccessible(true);
            Object mcEntity = f_mcEntity.get(entity);
            Field f_target = mcEntity.getClass().getDeclaredField("c");
            f_target.setAccessible(true);
            Object target = f_target.get(mcEntity);
            Class cHelper = Class.forName("WrapperObjects.EntityHelper");
            for(Method m: cHelper.getDeclaredMethods()){
                if(m.getName().contains("CreateEntityWrapper")){
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

    public static void setTarget(MC_Entity entity, MC_Entity target)
    {
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
}
