package PluginBukkitBridge;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

/**
 * Created by florian on 10.10.14.
 */
public class FakedFakeItem implements Item {

    ItemStack is;

    public FakedFakeItem(ItemStack is) {
        this.is = is;
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
        MyPlugin.fixme();
        return null;
    }

    @Override
    public Location getLocation(Location location) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setVelocity(Vector vector) {
        MyPlugin.fixme();
    }

    @Override
    public Vector getVelocity() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean isOnGround() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public World getWorld() {
        MyPlugin.fixme();
        return null;
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
        return false;
    }

    @Override
    public Server getServer() {
        return Bukkit.getServer();
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
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean isInsideVehicle() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean leaveVehicle() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public Entity getVehicle() {
        MyPlugin.fixme();
        return null;
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
}
