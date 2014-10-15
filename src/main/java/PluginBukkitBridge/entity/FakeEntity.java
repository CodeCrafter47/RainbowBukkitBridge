package PluginBukkitBridge.entity;

import PluginBukkitBridge.*;
import PluginReference.MC_Entity;
import PluginReference.MC_MotionData;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeEntity implements Entity {
    public MC_Entity m_ent;

    public FakeEntity(MC_Entity argEnt) {
        m_ent = argEnt;
    }

    public static void FakeDebug(String msg) {
        System.out.println("FakeEntity Proxy: " + msg);
    }

    public EntityDamageEvent lastDamageCause = null;

    @Override
    public List<MetadataValue> getMetadata(String arg0) {
        MyPlugin.fixme("stub method");
        return new ArrayList<>();
    }

    @Override
    public boolean hasMetadata(String arg0) {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void removeMetadata(String arg0, Plugin arg1) {
        MyPlugin.fixme("stub method");

    }

    @Override
    public void setMetadata(String arg0, MetadataValue arg1) {
        MyPlugin.fixme("stub method");

    }

    @Override
    public boolean eject() {
        if (getPassenger() == null) return false;
        m_ent.setRider(null);
        return true;
    }

    @Override
    public int getEntityId() {
        try {
            return ReflectionUtil.getEntityID(m_ent);
        } catch (Exception e) {
            MyPlugin.fixme("execution failed");
        }
        return 0;
    }

    @Override
    public Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return lastDamageCause;
    }

    @Override
    public Location getLocation() {
        return Util.getLocation(m_ent.getLocation());
    }

    @Override
    public int getMaxFireTicks() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public List<Entity> getNearbyEntities(double arg0, double arg1, double arg2) {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public Entity getPassenger() {
        return Util.wrapEntity(m_ent.getRider());
    }

    @Override
    public int getTicksLived() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public EntityType getType() {
        return FakeHelper.GetEntityType(m_ent.getType());
    }

    @Override
    public void setFallDistance(float distance) {
        MC_MotionData md = m_ent.getMotionData();
        md.fallDistance = distance;
        m_ent.setMotionData(md);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public UUID getUniqueId() {
        try {
            return ReflectionUtil.getEntityUUID(m_ent);
        } catch (Exception e) {
            MyPlugin.fixme("execution failed");
        }
        return null;
    }

    @Override
    public Entity getVehicle() {
        return Util.wrapEntity(m_ent.getVehicle());
    }

    @Override
    public void setVelocity(Vector velocity) {
        m_ent.setMotionData(Util.getMotionData(velocity, m_ent.getMotionData()));
    }

    @Override
    public boolean isOnGround() {
        return m_ent.getMotionData().onGround;
    }

    @Override
    public void setFireTicks(int ticks) {
        m_ent.setFireTicks(ticks);
    }

    @Override
    public boolean isDead() {
        return m_ent.isDead();
    }

    @Override
    public boolean isEmpty() {
        return m_ent.getRider() == null;
    }

    @Override
    public boolean isInsideVehicle() {
        return getVehicle() != null;
    }

    @Override
    public Vector getVelocity() {
        return Util.getDirection(m_ent.getMotionData());
    }

    @Override
    public boolean leaveVehicle() {
        if (getVehicle() == null) return false;
        m_ent.setVehicle(null);
        return true;
    }

    @Override
    public void playEffect(EntityEffect arg0) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public void remove() {
        m_ent.kill();
    }

    @Override
    public float getFallDistance() {
        return (float) m_ent.getMotionData().fallDistance;
    }

    @Override
    public int getFireTicks() {
        return m_ent.getFireTicks();
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent arg0) {
        lastDamageCause = arg0;

    }

    @Override
    public boolean setPassenger(Entity arg0) {
        m_ent.setRider(((FakeEntity) arg0).m_ent);
        return true;
    }

    @Override
    public void setTicksLived(int arg0) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public Location getLocation(Location loc) {
        loc.setX(m_ent.getLocation().x);
        loc.setY(m_ent.getLocation().y);
        loc.setZ(m_ent.getLocation().z);
        loc.setPitch(m_ent.getLocation().pitch);
        loc.setYaw(m_ent.getLocation().yaw);
        loc.setWorld(WorldManager.getWorld(m_ent.getWorld().getName()));
        return getLocation();
    }

    @Override
    public World getWorld() {
        return WorldManager.getWorld(m_ent.getWorld().getName());
    }

    @Override
    public boolean teleport(Entity arg0) {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public boolean teleport(Location location) {
        return teleport(location, TeleportCause.PLUGIN);
    }

    @Override
    public boolean teleport(Location arg0, TeleportCause arg1) {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public boolean teleport(Entity arg0, TeleportCause arg1) {
        MyPlugin.fixme("stub method");
        return false;
    }

}
