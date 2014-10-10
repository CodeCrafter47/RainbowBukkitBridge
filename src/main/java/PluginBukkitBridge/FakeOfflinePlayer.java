package PluginBukkitBridge;

import PluginReference.MC_Player;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class FakeOfflinePlayer implements OfflinePlayer
{
	public MC_Player player;

	public static void FakeDebug(String msg)
	{
		System.out.println("FakeOfflinePlayer Proxy: " + msg);
	}

    public FakeOfflinePlayer(MC_Player player) {
        this.player = player;
    }

    @Override
	public boolean isOp()
	{
		return player.isOp();
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
		return player.getName();
	}

	@Override
	public Player getPlayer()
	{
		return MyPlugin.getPlayer(getName());
	}

	@Override
	public UUID getUniqueId()
	{
		return player.getUUID();
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
