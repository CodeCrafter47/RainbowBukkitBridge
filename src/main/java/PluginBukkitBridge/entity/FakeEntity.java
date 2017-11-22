package PluginBukkitBridge.entity;

import PluginBukkitBridge.FakeHelper;
import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.Util;
import PluginBukkitBridge.WorldManager;
import PluginReference.MC_Entity;
import PluginReference.MC_MotionData;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class FakeEntity implements Entity {
    public MC_Entity m_ent;

	public void refreshReference(MC_Entity entity){
		m_ent = entity;
	}

	public void setDamageCause(EntityDamageEvent.DamageCause damageCause) {
		this.damageCause = damageCause;
	}

	public EntityDamageEvent.DamageCause damageCause = null;

    public FakeEntity(MC_Entity argEnt) {
        m_ent = argEnt;
    }

	@Override
	public void setCustomName(String name) {
		m_ent.setCustomName(name);
	}

	@Override
	public String getCustomName() {
		return m_ent.getCustomName();
	}

	@Override
	public void setCustomNameVisible(boolean arg0) {
		// we can ignore this as the custom name is always visible
	}

	@Override
	public boolean isCustomNameVisible() {
		MyPlugin.fixme();
		return false;
	}

    @Override
    public void setGlowing(boolean b) {
        MyPlugin.fixme();
    }

    @Override
    public boolean isGlowing() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void setInvulnerable(boolean b) {
        MyPlugin.fixme();
    }

    @Override
    public boolean isInvulnerable() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isSilent() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void setSilent(boolean b) {
        MyPlugin.fixme();
    }

    @Override
    public boolean hasGravity() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void setGravity(boolean b) {
        MyPlugin.fixme();
    }

    @Override
    public int getPortalCooldown() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setPortalCooldown(int i) {
        MyPlugin.fixme();
    }

    @Override
    public List<MetadataValue> getMetadata(String arg0) {
        return MyPlugin.entityMetadataStore.getMetadata(this, arg0);
    }

    @Override
    public boolean hasMetadata(String arg0) {
        return MyPlugin.entityMetadataStore.hasMetadata(this, arg0);
    }

    @Override
    public void removeMetadata(String arg0, Plugin arg1) {
        MyPlugin.entityMetadataStore.removeMetadata(this, arg0, arg1);
    }

    @Override
    public void setMetadata(String arg0, MetadataValue arg1) {
        MyPlugin.entityMetadataStore.setMetadata(this, arg0, arg1);
    }

    @Override
    public boolean eject() {
        if (getPassenger() == null) return false;
        m_ent.setRider(null);
        return true;
    }

    @Override
    public int getEntityId() {
        return m_ent.getEntityId();
    }

    @Override
    public void sendMessage(String s) {
        MyPlugin.fixme();
    }

    @Override
    public void sendMessage(String[] strings) {
        MyPlugin.fixme();
    }

    @Override
    public Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public String getName() {
        return m_ent.getName();
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        if(damageCause == null){
			MyPlugin.fixme();
			return null;
		}
        return new EntityDamageEvent(this, damageCause, 999);
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
    public List<Entity> getNearbyEntities(double x, double y, double z) {
        List<Entity> list = new ArrayList<>();
        for(Entity ent: getWorld().getEntities()){
            Location l = ent.getLocation();
            if(l.getX() >= x && l.getX() < x && l.getY() >= y && l.getY() < y && l.getZ() >= z && l.getZ() < z)list.add(ent);
        }
        return list;
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
        return m_ent.getUUID();
    }

    @Override
    public Entity getVehicle() {
        return Util.wrapEntity(m_ent.getVehicle());
    }

    @Override
    public Spigot spigot() {
        return new Spigot(){
            @Override
            public boolean isInvulnerable() {
                MyPlugin.fixme();
                return false;
            }
        };
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
        m_ent.removeEntity();
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
        MyPlugin.fixme();
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

    @Override
    public boolean isPermissionSet(String s) {
        MyPlugin.fixme();
        return true;
    }

    @Override
    public boolean isPermissionSet(Permission permission) {
        MyPlugin.fixme();
        return true;
    }

    @Override
    public boolean hasPermission(String s) {
        MyPlugin.fixme();
        return true;
    }

    @Override
    public boolean hasPermission(Permission permission) {
        MyPlugin.fixme();
        return true;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int i) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void removeAttachment(PermissionAttachment permissionAttachment) {
        MyPlugin.fixme();
    }

    @Override
    public void recalculatePermissions() {
        MyPlugin.fixme();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean isOp() {
        MyPlugin.fixme();
        return true;
    }

    @Override
    public void setOp(boolean b) {
        MyPlugin.fixme();
    }

    @Override
    public boolean addPassenger(Entity arg0) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean addScoreboardTag(String arg0) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public double getHeight() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public List<Entity> getPassengers() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public Set<String> getScoreboardTags() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public double getWidth() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public boolean removePassenger(Entity arg0) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean removeScoreboardTag(String arg0) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        MyPlugin.fixme();
        return null;
    }
}
