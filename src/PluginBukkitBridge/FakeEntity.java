package PluginBukkitBridge;

import java.util.List;
import java.util.UUID;

import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import PluginReference.*;

public class FakeEntity implements Entity
{
	public MC_Entity m_ent = null;
	
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
	public float getFallDistance()
	{
		FakeDebug("getFallDistance");
		return 0;
	}

	@Override
	public int getFireTicks()
	{
		FakeDebug("getFireTicks");
		return 0;
	}

	@Override
	public EntityDamageEvent getLastDamageCause()
	{
		FakeDebug("getLastDamageCause");
		return null;
	}

	@Override
	public Location getLocation()
	{
		FakeDebug("getLocation");
		return null;
	}

	@Override
	public Location getLocation(Location arg0)
	{
		FakeDebug("getLocation");
		return null;
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
	public Server getServer()
	{
		FakeDebug("getServer");
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
	public Vector getVelocity()
	{
		FakeDebug("getVelocity");
		return null;
	}

	@Override
	public World getWorld()
	{
		FakeDebug("getWorld");
		return null;
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
	public boolean isOnGround()
	{
		FakeDebug("isOnGround");
		return false;
	}

	@Override
	public boolean isValid()
	{
		FakeDebug("isValid");
		return false;
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
	public void setFallDistance(float arg0)
	{
		FakeDebug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		
	}

	@Override
	public void setFireTicks(int arg0)
	{
		FakeDebug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		
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
	public void setVelocity(Vector arg0)
	{
		FakeDebug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		
	}

	@Override
	public boolean teleport(Location arg0)
	{
		FakeDebug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		return false;
	}

	@Override
	public boolean teleport(Entity arg0)
	{
		FakeDebug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		return false;
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
