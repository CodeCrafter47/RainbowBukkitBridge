package PluginBukkitBridge;

import org.bukkit.*;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by florian on 10.10.14.
 */
public class FakedFakeItem implements Item {

    ItemStack is;
    Location location;

    public FakedFakeItem(ItemStack is, Location location) {
        this.is = is;
		if(is == null){
			MyPlugin.fixme("itemstack is null, pretending dirt");
			this.is = new ItemStack(Material.DIRT);
		}
        this.location = location;
    }

    @Override
    public ItemStack getItemStack() {
        return is.clone();
    }

    @Override
    public void setItemStack(ItemStack itemStack) {
        is = itemStack;
    }

    @Override
    public int getPickupDelay() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setPickupDelay(int i) {
        MyPlugin.fixme();
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Location getLocation(Location location) {
        if(location == null)return this.location;
		location.setWorld(this.location.getWorld());
		location.setDirection(this.location.getDirection());
		location.setX(this.location.getX());
		location.setY(this.location.getY());
		location.setZ(this.location.getZ());
		location.setPitch(this.location.getPitch());
		location.setYaw(this.location.getYaw());
        return location;
    }

    @Override
    public void setVelocity(Vector vector) {
        MyPlugin.fixme();
    }

    @Override
    public Vector getVelocity() {
        return new Vector();
    }

    @Override
    public boolean isOnGround() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public World getWorld() {
        return location.getWorld();
    }

    @Override
    public boolean teleport(Location location) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause teleportCause) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean teleport(Entity entity) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean teleport(Entity entity, PlayerTeleportEvent.TeleportCause teleportCause) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public List<Entity> getNearbyEntities(double v, double v2, double v3) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public int getEntityId() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int getFireTicks() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int getMaxFireTicks() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setFireTicks(int i) {
        MyPlugin.fixme();
    }

    @Override
    public void remove() {
        MyPlugin.fixme();
    }

    @Override
    public boolean isDead() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isValid() {
        MyPlugin.fixme();
        return true;
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
        MyPlugin.fixme();
        return null;
    }

    @Override
    public Entity getPassenger() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean setPassenger(Entity entity) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isEmpty() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean eject() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public float getFallDistance() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setFallDistance(float v) {
        MyPlugin.fixme();
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent entityDamageEvent) {
        MyPlugin.fixme();
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public UUID getUniqueId() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public int getTicksLived() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setTicksLived(int i) {
        MyPlugin.fixme();
    }

    @Override
    public void playEffect(EntityEffect entityEffect) {
        MyPlugin.fixme();
    }

    @Override
    public EntityType getType() {
        return EntityType.DROPPED_ITEM;
    }

    @Override
    public boolean isInsideVehicle() {
        return false;
    }

    @Override
    public boolean leaveVehicle() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public Entity getVehicle() {
        return null;
    }

	@Override public void setCustomName(String s) {
		MyPlugin.fixme();
	}

	@Override public String getCustomName() {
		MyPlugin.fixme();
		return null;
	}

	@Override public void setCustomNameVisible(boolean b) {
		MyPlugin.fixme();
	}

	@Override public boolean isCustomNameVisible() {
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
    public void setMetadata(String s, MetadataValue metadataValue) {
        MyPlugin.fixme();
    }

    @Override
    public List<MetadataValue> getMetadata(String s) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean hasMetadata(String s) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void removeMetadata(String s, Plugin plugin) {
        MyPlugin.fixme();
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
