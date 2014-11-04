package PluginBukkitBridge.scoreboard;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.ReflectionUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by florian on 03.11.14.
 */
public class FakeScoreboard implements Scoreboard {
	Object handle;

	public FakeScoreboard(Object handle) {
		this.handle = handle;
	}

	@Override public Objective registerNewObjective(String s, String s2) throws IllegalArgumentException {
		// we only register dummy things
		if(!s2.equals("dummy")){
			MyPlugin.fixme("unknown stat related thing: " + s2);
		}
		return ReflectionUtil.registerNewObjective(handle, s);
	}

	@Override public Objective getObjective(String s) throws IllegalArgumentException {
		return ReflectionUtil.getObjective(handle, s);
	}

	@Override public Set<Objective> getObjectivesByCriteria(String s) throws IllegalArgumentException {
		MyPlugin.fixme();
		return null;
	}

	@Override public Set<Objective> getObjectives() {
		MyPlugin.fixme();
		return null;
	}

	@Override public Objective getObjective(DisplaySlot displaySlot) throws IllegalArgumentException {
		MyPlugin.fixme();
		return null;
	}

	@Override public Set<Score> getScores(OfflinePlayer offlinePlayer) throws IllegalArgumentException {
		MyPlugin.fixme();
		return null;
	}

	@Override public Set<Score> getScores(String s) throws IllegalArgumentException {
		MyPlugin.fixme();
		return null;
	}

	@Override public void resetScores(OfflinePlayer offlinePlayer) throws IllegalArgumentException {
		MyPlugin.fixme();

	}

	@Override public void resetScores(String s) throws IllegalArgumentException {
		MyPlugin.fixme();

	}

	@Override public Team getPlayerTeam(OfflinePlayer offlinePlayer) throws IllegalArgumentException {
		MyPlugin.fixme();
		return null;
	}

	@Override public Team getTeam(String s) throws IllegalArgumentException {
		MyPlugin.fixme();
		return null;
	}

	@Override public Set<Team> getTeams() {
		return new HashSet<Team>(ReflectionUtil.getTeams(handle));
	}

	@Override public Team registerNewTeam(String s) throws IllegalArgumentException {
		return ReflectionUtil.registerNewTeam(handle, s);
	}

	@Override public Set<OfflinePlayer> getPlayers() {
		MyPlugin.fixme();
		return null;
	}

	@Override public Set<String> getEntries() {
		MyPlugin.fixme();
		return null;
	}

	@Override public void clearSlot(DisplaySlot displaySlot) throws IllegalArgumentException {
		MyPlugin.fixme();

	}
}
