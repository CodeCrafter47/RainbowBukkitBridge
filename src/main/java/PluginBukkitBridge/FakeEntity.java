package PluginBukkitBridge;

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

import java.util.List;
import java.util.UUID;

public class FakeEntity implements Entity
{
	public MC_Entity m_ent;
	
	public FakeEntity(MC_Entity argEnt)
	{
		m_ent = argEnt;
	}
	public static void FakeDebug(String msg)
	{
		System.out.println("FakeEntity Proxy: " + msg);
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
	public boolean eject()
	{
		FakeDebug("eject");
		return false;
	}

	@Override
	public int getEntityId()
	{
		FakeDebug("getEntityId");
		return 0;
	}

    @Override
    public Server getServer() {
        return Bukkit.getServer();
    }

	@Override
	public EntityDamageEvent getLastDamageCause()
	{
		FakeDebug("getLastDamageCause");
		return null;
	}

    @Override
    public Location getLocation() {
        return Util.getLocation(m_ent.getLocation());
    }

	@Override
	public int getMaxFireTicks()
	{
		FakeDebug("getMaxFireTicks");
		return 0;
	}

	@Override
	public List<Entity> getNearbyEntities(double arg0, double arg1, double arg2)
	{
		FakeDebug("getNearbyEntities");
		return null;
	}

	@Override
	public Entity getPassenger()
	{
		FakeDebug("getPassenger");
		return null;
	}

	@Override
	public int getTicksLived()
	{
		FakeDebug("getTicksLived");
		return 0;
	}

	@Override
	public EntityType getType()
	{
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
	public UUID getUniqueId()
	{
		FakeDebug("getUniqueId");
		return null;
	}

	@Override
	public Entity getVehicle()
	{
		FakeDebug("getVehicle");
		return null;
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
	public boolean isDead()
	{
		return m_ent.isDead();
	}

	@Override
	public boolean isEmpty()
	{
		FakeDebug("isEmpty");
		return false;
	}

	@Override
	public boolean isInsideVehicle()
	{
		FakeDebug("isInsideVehicle");
		return false;
	}

    @Override
    public Vector getVelocity() {
        return Util.getDirection(m_ent.getMotionData());
    }

	@Override
	public boolean leaveVehicle()
	{
		FakeDebug("leaveVehicle");
		return false;
	}

	@Override
	public void playEffect(EntityEffect arg0)
	{
		FakeDebug("playEffect");
	}

	@Override
	public void remove()
	{
		FakeDebug("remove");
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
	public void setLastDamageCause(EntityDamageEvent arg0)
	{
		FakeDebug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		
	}

	@Override
	public boolean setPassenger(Entity arg0)
	{
		FakeDebug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		return false;
	}

	@Override
	public void setTicksLived(int arg0)
	{
		FakeDebug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		
	}

    @Override
    public Location getLocation(Location loc) {
        loc.setX(m_ent.getLocation().x);
        loc.setY(m_ent.getLocation().y);
        loc.setZ(m_ent.getLocation().z);
        loc.setPitch(m_ent.getLocation().pitch);
        loc.setYaw(m_ent.getLocation().yaw);
        loc.setWorld(new FakeWorld(m_ent.getWorld()));
        return getLocation();
    }

    @Override
    public World getWorld() {
        return new FakeWorld(m_ent.getWorld());
    }

    @Override
	public boolean teleport(Entity arg0)
	{
		FakeDebug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		return false;
	}

    @Override
    public boolean teleport(Location location) {
        return teleport(location, TeleportCause.PLUGIN);
    }

    @Override
	public boolean teleport(Location arg0, TeleportCause arg1)
	{
		FakeDebug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		return false;
	}

    @Override
	public boolean teleport(Entity arg0, TeleportCause arg1)
	{
		FakeDebug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		return false;
	}

}
