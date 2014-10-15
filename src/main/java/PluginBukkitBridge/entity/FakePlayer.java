package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.ReflectionUtil;
import PluginBukkitBridge.Util;
import PluginReference.MC_Player;
import org.bukkit.*;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.craftbukkit.CraftSound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.map.MapView;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


public class FakePlayer extends FakeHumanEntity implements Player
{
    public MC_Player player;
    private UUID uuid;

    private String playerListName = null;

    public FakePlayer(MC_Player player) {
        super(player);
        this.player = player;
        uuid = player.getUUID();
    }

    @Override
    public String getDisplayName() {
        return player.getCustomName();
    }

    @Override
    public void setDisplayName(String name) {
        player.setCustomName(name);
    }

    @Override
    public String getPlayerListName() {
        return playerListName == null?getName():playerListName;
    }

    @Override
    public void setPlayerListName(String name) {
        playerListName = name;
        for(Player p: Bukkit.getOnlinePlayers()){
            ReflectionUtil.sendPlayerListItemChangeDisplayName(((FakePlayer)p).player, player, name);
        }
    }

    @Override
    public void setCompassTarget(Location loc) {
        player.setCompassTarget(Util.getLocation(loc));
    }

    @Override
    public Location getCompassTarget() {
        return Util.getLocation(player.getCompassTarget());
    }

    @Override
    public InetSocketAddress getAddress() {
        return new InetSocketAddress(player.getIPAddress(), 0);
    }
    @Override
    public void sendRawMessage(String message) {
        // fixme
        player.sendMessage(message);
    }

    @Override
    public boolean performCommand(String command) {
        return MyPlugin.commandMap.dispatch(this, command);
    }

    @Override
    public boolean isSneaking() {
        return player.isSneaking();
    }

    @Override
    public boolean isSprinting() {
        return player.isSprinting();
    }

    @Override
    public void playNote(Location loc, byte instrument, byte note) {
        String instrumentName = null;
        switch (instrument) {
            case 0:
                instrumentName = "harp";
                break;
            case 1:
                instrumentName = "bd";
                break;
            case 2:
                instrumentName = "snare";
                break;
            case 3:
                instrumentName = "hat";
                break;
            case 4:
                instrumentName = "bassattack";
                break;
        }
        playSound(loc, "note."+instrumentName, 3.0f, note);
    }

    @Override
    public void playNote(Location loc, Instrument instrument, Note note) {
        String instrumentName = null;
        switch (instrument.ordinal()) {
            case 0:
                instrumentName = "harp";
                break;
            case 1:
                instrumentName = "bd";
                break;
            case 2:
                instrumentName = "snare";
                break;
            case 3:
                instrumentName = "hat";
                break;
            case 4:
                instrumentName = "bassattack";
                break;
        }
        playSound(loc, "note." + instrumentName, 3.0f, note.getId());
    }

    @Override
    public void playSound(Location location, Sound sound, float volume, float pitch) {
        playSound(location, CraftSound.getSound(sound), volume, pitch);
    }

    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {
        // fixme location
        player.playSound(sound, volume, pitch);
    }

    @Override
    public void playEffect(Location location, Effect effect, int i) {

    }

    @Override
    public <T> void playEffect(Location location, Effect effect, T t) {

    }

    @Override
    public long getPlayerTime() {
        // fixme
        return getWorld().getTime();
    }

    @Override
    public long getPlayerTimeOffset() {
        // fixme
        return 0;
    }

    @Override
    public boolean isPlayerTimeRelative() {
        // fixme
        return false;
    }

    @Override
    public void giveExp(int amount) {
        setExp(getExp() + amount);
    }

    @Override
    public void giveExpLevels(int amount) {
        setLevel(getLevel() + amount);
    }

    @Override
    public float getExp() {
        return player.getExp();
    }
    @Override
    public int getLevel() {
        return player.getLevel();
    }

    @Override
    public int getTotalExperience() {
        return player.getTotalExperience();
    }

    @Override
    public int getFoodLevel() {
        return player.getFoodLevel();
    }

    @Override
    public void setFoodLevel(int value) {
        player.setFoodLevel(value);
    }

    @Override
    public boolean getAllowFlight() {
        return player.isAllowedFlight();
    }

    @Override
    public UUID getUniqueId() {
        return uuid;
    }


    @Override
    public Player getPlayer() {
        return this;
    }

    @Override
    public boolean isFlying() {
        return player.isFlying();
    }

    @Override
    public float getFlySpeed() {
        return player.getFlySpeed();
    }

    @Override
    public float getWalkSpeed() {
        return player.getWalkSpeed();
    }

    @Override
    public void setTexturePack(String url) {
        MyPlugin.fixme();
    }

    @Override
    public void setResourcePack(String url) {
        MyPlugin.fixme();
    }

    @Override
    public Scoreboard getScoreboard() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
        MyPlugin.fixme();
    }

    @Override
    public void setHealthScaled(boolean scale) {
        MyPlugin.fixme();
    }

    @Override
    public void setHealthScale(double scale) throws IllegalArgumentException {
        setHealth(scale);
    }

    @Override
    public double getHealthScale() {
        return getHealth();
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    @Override
    public void sendMessage(String[] messages) {
        for(String msg: messages){
            sendMessage(msg);
        }
    }

    @Override
    public boolean isOnline() {
        return isValid();
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Player && ((Player) o).getUniqueId().equals(getUniqueId());
    }
	public static void FakeDebug(String msg)
	{
		System.out.println("FakePlayer Proxy: " + msg);
	}

	@Override
	public void abandonConversation(Conversation arg0)
	{
		FakeDebug("abandonConversation");
	}

	@Override
	public void abandonConversation(Conversation arg0, ConversationAbandonedEvent arg1)
	{
		FakeDebug("abandonConversation");
	}

	@Override
	public void acceptConversationInput(String arg0)
	{
		FakeDebug("acceptConversationInput");
	}

	@Override
	public boolean beginConversation(Conversation arg0)
	{
		FakeDebug("beginConversation");
		return false;
	}

	@Override
	public boolean isConversing()
	{
		FakeDebug("isConversing");
		return false;
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
	public boolean hasPlayedBefore()
	{
		FakeDebug("hasPlayedBefore");
        // return true as work around for AuthMe
		return true;
	}

	@Override
	public boolean isBanned()
	{
		FakeDebug("isBanned");
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

	@Override
	public Map<String, Object> serialize()
	{
				FakeDebug("serialize");
		return null;
	}

	@Override
	public Set<String> getListeningPluginChannels()
	{
		FakeDebug("getListeningPluginChannels");
		return null;
	}

	@Override
	public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2)
	{
		FakeDebug("sendPluginMessage");
	}

	@Override
	public void awardAchievement(Achievement arg0)
	{
				FakeDebug("awardAchievement");
		
	}

	@Override
	public boolean canSee(Player arg0)
	{
				FakeDebug("canSee");
		return false;
	}

	@Override
	public void chat(String arg0)
	{
				FakeDebug("chat");
		
	}

	@Override
	public void decrementStatistic(Statistic arg0) throws IllegalArgumentException
	{
				FakeDebug("decrementStatistic");
		
	}

	@Override
	public void decrementStatistic(Statistic arg0, int arg1) throws IllegalArgumentException
	{
				FakeDebug("decrementStatistic");
		
	}

	@Override
	public void decrementStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException
	{
				FakeDebug("decrementStatistic");
		
	}

	@Override
	public void decrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException
	{
				FakeDebug("decrementStatistic");
		
	}

	@Override
	public void decrementStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException
	{
				FakeDebug("decrementStatistic");
		
	}

	@Override
	public void decrementStatistic(Statistic arg0, EntityType arg1, int arg2)
	{
				FakeDebug("decrementStatistic");
		
	}

	@Override
	public Location getBedSpawnLocation()
	{
				FakeDebug("getBedSpawnLocation");
		return null;
	}

	@Override
	public float getExhaustion()
	{
		FakeDebug("getExhaustion");
		return 0;
	}

	@Override
	public WeatherType getPlayerWeather()
	{
				FakeDebug("getPlayerWeather");
		return null;
	}

	@Override
	public float getSaturation()
	{
				FakeDebug("getSaturation");
		return 0;
	}

	@Override
	public int getStatistic(Statistic arg0) throws IllegalArgumentException
	{
				FakeDebug("getStatistic");
		return 0;
	}

	@Override
	public int getStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException
	{
				FakeDebug("getStatistic");
		return 0;
	}

	@Override
	public int getStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException
	{
				FakeDebug("getStatistic");
		return 0;
	}

	@Override
	public boolean hasAchievement(Achievement arg0)
	{
				FakeDebug("hasAchievement");
		return false;
	}

	@Override
	public void hidePlayer(Player arg0)
	{
				FakeDebug("hidePlayer");
		
	}

	@Override
	public void incrementStatistic(Statistic arg0) throws IllegalArgumentException
	{
				FakeDebug("incrementStatistic");
		
	}

	@Override
	public void incrementStatistic(Statistic arg0, int arg1) throws IllegalArgumentException
	{
				FakeDebug("incrementStatistic");
		
	}

	@Override
	public void incrementStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException
	{
				FakeDebug("incrementStatistic");
		
	}

	@Override
	public void incrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException
	{
				FakeDebug("incrementStatistic");
		
	}

	@Override
	public void incrementStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException
	{
				FakeDebug("incrementStatistic");
		
	}

	@Override
	public void incrementStatistic(Statistic arg0, EntityType arg1, int arg2) throws IllegalArgumentException
	{
				FakeDebug("incrementStatistic");
		
	}

	@Override
	public boolean isHealthScaled()
	{
				FakeDebug("isHealthScaled");
		return false;
	}

	@Override
	public boolean isSleepingIgnored()
	{
				FakeDebug("isSleepingIgnored");
		return false;
	}

	@Override
	public void kickPlayer(String arg0)
	{
		MyPlugin.server.executeCommand("kick " + player.getName() + " " + arg0);
	}

	@Override
	public void loadData()
	{
				FakeDebug("loadData");
		
	}

    @Override
    public void showParticle(Location location, Particle particle, float v, float v2, float v3, float v4, int i) {

    }

    @Override
    public void showParticle(Location location, Particle particle, MaterialData materialData, float v, float v2, float v3, float v4, int i) {

    }

    @Override
	public void removeAchievement(Achievement arg0)
	{
		FakeDebug("removeAchievement");
	}

	@Override
	public void resetPlayerTime()
	{
		FakeDebug("resetPlayerTime");
	}

	@Override
	public void resetPlayerWeather()
	{
		FakeDebug("resetPlayerWeather");
	}

	@Override
	public void saveData()
	{
		FakeDebug("saveData");
	}

	@Override
	public void sendBlockChange(Location arg0, Material arg1, byte arg2)
	{
		FakeDebug("sendBlockChange");
	}

	@Override
	public void sendBlockChange(Location arg0, int arg1, byte arg2)
	{
		FakeDebug("sendBlockChange");
	}

	@Override
	public boolean sendChunkChange(Location arg0, int arg1, int arg2, int arg3, byte[] arg4)
	{
		FakeDebug("sendChunkChange");
		return false;
	}

	@Override
	public void sendMap(MapView arg0)
	{
		FakeDebug("sendMap");
	}

	@Override
	public void sendSignChange(Location arg0, String[] arg1) throws IllegalArgumentException
	{
		FakeDebug("sendSignChange");
	}

	@Override
	public void setAllowFlight(boolean arg0)
	{
		player.setAllowFlight(arg0);
	}

	@Override
	public void setBedSpawnLocation(Location arg0)
	{
		FakeDebug("setBedSpawnLocation");
	}

	@Override
	public void setBedSpawnLocation(Location arg0, boolean arg1)
	{
		FakeDebug("setBedSpawnLocation");
	}

	@Override
	public void setExhaustion(float arg0)
	{
		FakeDebug("setExhaustion");
	}

	@Override
	public void setExp(float arg0)
	{
		player.setExp(arg0);
	}

	@Override
	public void setFlySpeed(float arg0) throws IllegalArgumentException
	{
		player.setFlySpeed(arg0);
	}

	@Override
	public void setFlying(boolean arg0)
	{
		player.setFlying(arg0);
	}

	@Override
	public void setLevel(int arg0)
	{
		player.setLevel(arg0);
	}

	@Override
	public void setPlayerTime(long arg0, boolean arg1)
	{
		FakeDebug("setPlayerTime");
	}

	@Override
	public void setPlayerWeather(WeatherType arg0)
	{
		FakeDebug("setPlayerWeather");
	}

	@Override
	public void setSaturation(float arg0)
	{
		FakeDebug("setSaturation");
	}

	@Override
	public void setSleepingIgnored(boolean arg0)
	{
		FakeDebug("setSleepingIgnored");
	}

	@Override
	public void setSneaking(boolean arg0)
	{
		FakeDebug("setSneaking");
	}

	@Override
	public void setSprinting(boolean arg0)
	{
		FakeDebug("setSprinting");
	}

	@Override
	public void setStatistic(Statistic arg0, int arg1) throws IllegalArgumentException
	{
		FakeDebug("setStatistic");
	}

	@Override
	public void setStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException
	{
		FakeDebug("setStatistic");
	}

	@Override
	public void setStatistic(Statistic arg0, EntityType arg1, int arg2)
	{
		FakeDebug("setStatistic");
	}

	@Override
	public void setTotalExperience(int arg0)
	{
		player.setTotalExperience(arg0);
	}

	@Override
	public void setWalkSpeed(float arg0) throws IllegalArgumentException
	{
		player.setWalkSpeed(arg0);
	}

	@Override
	public void showPlayer(Player arg0)
	{
		FakeDebug("showPlayer");
	}

	@Override
	public void updateInventory()
	{
		player.updateInventory();
	}

    @Override
    public boolean teleport(Entity destination, PlayerTeleportEvent.TeleportCause cause) {
        return teleport(destination.getLocation());
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause) {
        PlayerTeleportEvent event = new PlayerTeleportEvent(this, getLocation(), location, cause);
        MyPlugin.pluginManager.callEvent(event);
        if(event.isCancelled())return false;
        Location to = event.getTo();
        if(to == null){
            to = location;
        }
        player.teleport(Util.getLocation(to));
        return true;
    }

    @Override
    public boolean teleport(Entity destination) {
        return teleport(destination, PlayerTeleportEvent.TeleportCause.PLUGIN);
    }

}
