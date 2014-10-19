package PluginBukkitBridge;

import PluginReference.MC_Player;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class FakeOfflinePlayer implements OfflinePlayer {
    public MC_Player player;

    public FakeOfflinePlayer(MC_Player player) {
        this.player = player;
    }

    @Override
    public boolean isOp() {
        return player.isOp();
    }

    @Override
    public void setOp(boolean op) {
        if(op == isOp())return;
        MyPlugin.server.executeCommand((op?"op ":"deop ") + getName());
    }

    @Override
    public Map<String, Object> serialize() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public Location getBedSpawnLocation() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public long getFirstPlayed() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public long getLastPlayed() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public Player getPlayer() {
        return PlayerManager.getPlayer(getUniqueId());
    }

    @Override
    public UUID getUniqueId() {
        return player.getUUID();
    }

    @Override
    public boolean hasPlayedBefore() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isBanned() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isOnline() {
        return getPlayer() != null;
    }

    @Override
    public boolean isWhitelisted() {
        MyPlugin.fixme();
        return true;
    }

    @Override
    public void setBanned(boolean banned) {
        MyPlugin.server.executeCommand((banned?"ban ":"pardon") + getName());
    }

    @Override
    public void setWhitelisted(boolean arg0) {
        MyPlugin.fixme();
    }

}
