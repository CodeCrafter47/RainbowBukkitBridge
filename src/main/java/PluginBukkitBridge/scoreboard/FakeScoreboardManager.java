package PluginBukkitBridge.scoreboard;

import PluginBukkitBridge.ReflectionUtil;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

/**
 * Created by florian on 03.11.14.
 */
public class FakeScoreboardManager implements ScoreboardManager {
	@Override public Scoreboard getMainScoreboard() {
		return ReflectionUtil.getMainScoreboard();
	}

	@Override public Scoreboard getNewScoreboard() {
		return ReflectionUtil.getNewScoreboard();
	}
}
