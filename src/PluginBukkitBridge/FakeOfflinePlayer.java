package PluginBukkitBridge;

import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class FakeOfflinePlayer implements OfflinePlayer
{
	public String m_name = "";

	public static void FakeDebug(String msg)
	{
		System.out.println("FakeOfflinePlayer Proxy: " + msg);
	}
	
	@Override
	public boolean isOp()
	{
		FakeDebug("isOp");
		return false;
	}

	@Override
	public void setOp(boolean arg0)
	{
				FakeDebug("setOp");
		
	}

	@Override
	public Map<String, Object> serialize()
	{
				FakeDebug("serialize");
		return null;
	}

	@Override
	public Location getBedSpawnLocation()
	{
				FakeDebug("getBedSpawnLocation");
		return null;
	}

	@Override
	public long getFirstPlayed()
	{
				FakeDebug("getFirstPlayed");
		return 0;
	}

	@Override
	public long getLastPlayed()
	{
				FakeDebug("getLastPlayed");
		return 0;
	}

	@Override
	public String getName()
	{
		return m_name;
	}

	@Override
	public Player getPlayer()
	{
				FakeDebug("getPlayer");
		return null;
	}

	@Override
	public UUID getUniqueId()
	{
				FakeDebug("getUniqueId");
		return null;
	}

	@Override
	public boolean hasPlayedBefore()
	{
				FakeDebug("hasPlayedBefore");
		return false;
	}

	@Override
	public boolean isBanned()
	{
				FakeDebug("isBanned");
		return false;
	}

	@Override
	public boolean isOnline()
	{
		return false;
	}

	@Override
	public boolean isWhitelisted()
	{
				FakeDebug("isWhitelisted");
		return false;
	}

	@Override
	public void setBanned(boolean arg0)
	{
				FakeDebug("setBanned");
		
	}

	@Override
	public void setWhitelisted(boolean arg0)
	{
				FakeDebug("setWhitelisted");
		
	}

}
