package PluginBukkitBridge.scoreboard;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.ReflectionUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Set;

public class FakeTeam implements Team {

	Object handle;
	FakeScoreboard scoreboard;

	public FakeTeam(Object handle, FakeScoreboard scoreboard) {
		this.handle = handle;
		this.scoreboard = scoreboard;
	}

	@Override public String getName() throws IllegalStateException {
		MyPlugin.fixme();
		return null;
	}

	@Override public String getDisplayName() throws IllegalStateException {
		MyPlugin.fixme();
		return null;
	}

	@Override public void setDisplayName(String s) throws IllegalStateException, IllegalArgumentException {
		ReflectionUtil.setTeamDisplayName(handle, s);
	}

	@Override public String getPrefix() throws IllegalStateException {
		MyPlugin.fixme();
		return null;
	}

	@Override public void setPrefix(String s) throws IllegalStateException, IllegalArgumentException {
		ReflectionUtil.setTeamPrefix(handle, s);
	}

	@Override public String getSuffix() throws IllegalStateException {
		MyPlugin.fixme();
		return null;
	}

	@Override public void setSuffix(String s) throws IllegalStateException, IllegalArgumentException {
		ReflectionUtil.setTeamSuffix(handle, s);
	}

	@Override public boolean allowFriendlyFire() throws IllegalStateException {
		MyPlugin.fixme();
		return false;
	}

	@Override public void setAllowFriendlyFire(boolean b) throws IllegalStateException {
		MyPlugin.fixme();
	}

	@Override public boolean canSeeFriendlyInvisibles() throws IllegalStateException {
		MyPlugin.fixme();
		return false;
	}

	@Override public void setCanSeeFriendlyInvisibles(boolean b) throws IllegalStateException {
		MyPlugin.fixme();
	}

    @Override
    public NameTagVisibility getNameTagVisibility() throws IllegalArgumentException {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setNameTagVisibility(NameTagVisibility nameTagVisibility) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override public Set<OfflinePlayer> getPlayers() throws IllegalStateException {
		MyPlugin.fixme();
		return null;
	}

	@Override public Set<String> getEntries() throws IllegalStateException {
		MyPlugin.fixme();
		return null;
	}

	@Override public int getSize() throws IllegalStateException {
		MyPlugin.fixme();
		return 0;
	}

	@Override public Scoreboard getScoreboard() {
		MyPlugin.fixme();
		return null;
	}

	@Override public void addPlayer(OfflinePlayer offlinePlayer) throws IllegalStateException, IllegalArgumentException {
		addEntry(offlinePlayer.getName());
	}

	@Override public void addEntry(String s) throws IllegalStateException, IllegalArgumentException {
		ReflectionUtil.addPlayerToTeam(scoreboard.handle, handle, s);
	}

	@Override public boolean removePlayer(OfflinePlayer offlinePlayer) throws IllegalStateException, IllegalArgumentException {
		MyPlugin.fixme();
		return false;
	}

	@Override public boolean removeEntry(String s) throws IllegalStateException, IllegalArgumentException {
		MyPlugin.fixme();
		return false;
	}

	@Override public void unregister() throws IllegalStateException {
		MyPlugin.fixme();
	}

	@Override public boolean hasPlayer(OfflinePlayer offlinePlayer) throws IllegalArgumentException, IllegalStateException {
		MyPlugin.fixme();
		return false;
	}

	@Override public boolean hasEntry(String s) throws IllegalArgumentException, IllegalStateException {
		MyPlugin.fixme();
		return false;
	}
}
