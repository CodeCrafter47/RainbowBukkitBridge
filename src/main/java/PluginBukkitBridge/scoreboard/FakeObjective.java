package PluginBukkitBridge.scoreboard;

import PluginBukkitBridge.MyPlugin;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Created by florian on 03.11.14.
 */
public class FakeObjective implements Objective {
	Object handle;

	public FakeObjective(Object handle) {
		this.handle = handle;
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
		MyPlugin.fixme();

	}

	@Override public String getCriteria() throws IllegalStateException {
		MyPlugin.fixme();
		return null;
	}

	@Override public boolean isModifiable() throws IllegalStateException {
		MyPlugin.fixme();
		return false;
	}

	@Override public Scoreboard getScoreboard() {
		MyPlugin.fixme();
		return null;
	}

	@Override public void unregister() throws IllegalStateException {
		MyPlugin.fixme();

	}

	@Override public void setDisplaySlot(DisplaySlot displaySlot) throws IllegalStateException {
		MyPlugin.fixme();

	}

	@Override public DisplaySlot getDisplaySlot() throws IllegalStateException {
		MyPlugin.fixme();
		return null;
	}

	@Override public Score getScore(OfflinePlayer offlinePlayer) throws IllegalArgumentException, IllegalStateException {
		MyPlugin.fixme();
		return null;
	}

	@Override public Score getScore(String s) throws IllegalArgumentException, IllegalStateException {
		MyPlugin.fixme();
		return null;
	}
}
