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
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;

import java.net.InetSocketAddress;
import java.util.*;


public class FakePlayer extends FakeHumanEntity implements Player {
    private UUID uuid;

    private String playerListName = null;

    private int noDamageTicks = 0;

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
        return playerListName == null ? getName() : playerListName;
    }

    @Override
    public void setPlayerListName(String name) {
        playerListName = name;
        for (Player p : Bukkit.getOnlinePlayers()) {
            ReflectionUtil.sendPlayerListItemChangeDisplayName(((FakePlayer) p).player, player, name);
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
        playSound(loc, "note." + instrumentName, 3.0f, note);
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
        MyPlugin.fixme();
    }

    @Override
    public <T> void playEffect(Location location, Effect effect, T t) {
        MyPlugin.fixme();
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
        for (String msg : messages) {
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
    public boolean equals(Object o) {
        return o instanceof Player && ((Player) o).getUniqueId().equals(getUniqueId());
    }

    @Override
    public void abandonConversation(Conversation arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void abandonConversation(Conversation arg0, ConversationAbandonedEvent arg1) {
        MyPlugin.fixme();
    }

    @Override
    public void acceptConversationInput(String arg0) {
        MyPlugin.fixme();
    }

    @Override
    public boolean beginConversation(Conversation arg0) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isConversing() {
        MyPlugin.fixme();
        return false;
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
    public boolean hasPlayedBefore() {
        return !ReflectionUtil.isFirstJoin(player);
    }

    @Override
    public boolean isBanned() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isWhitelisted() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void setBanned(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setWhitelisted(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public Map<String, Object> serialize() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return MyPlugin.messenger.getIncomingChannels();
    }

    @Override
    public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2) {
        // fixme shall we check whether a plugin has registered for a channel?
        // it seems kind of useless
        ReflectionUtil.sendPluginMessage(player, arg1, arg2);
    }

    @Override
    public void awardAchievement(Achievement arg0) {
        MyPlugin.fixme();
    }

    @Override
    public boolean canSee(Player arg0) {
        MyPlugin.fixme();
        return true;
    }

    @Override
    public void chat(String arg0) {
        MyPlugin.fixme();

    }

    @Override
    public void decrementStatistic(Statistic arg0) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public void decrementStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public void decrementStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public void decrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public void decrementStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public void decrementStatistic(Statistic arg0, EntityType arg1, int arg2) {
        MyPlugin.fixme();
    }

    @Override
    public Location getBedSpawnLocation() {
        return ReflectionUtil.getBedSpawnLocation(player);
    }

    @Override
    public float getExhaustion() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public WeatherType getPlayerWeather() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public float getSaturation() {
        return ReflectionUtil.getFoodSaturation(player);
    }

    @Override
    public int getStatistic(Statistic arg0) throws IllegalArgumentException {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int getStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int getStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public boolean hasAchievement(Achievement arg0) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void hidePlayer(Player arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void incrementStatistic(Statistic arg0) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public void incrementStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public void incrementStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public void incrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public void incrementStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public void incrementStatistic(Statistic arg0, EntityType arg1, int arg2) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public boolean isHealthScaled() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isSleepingIgnored() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void kickPlayer(String arg0) {
        MyPlugin.server.executeCommand("kick " + player.getName() + " " + arg0);
    }

    @Override
    public void loadData() {
        MyPlugin.fixme();
    }

    @Override
    public void showParticle(Location location, Particle particle, float v, float v2, float v3, float v4, int i) {
        MyPlugin.fixme();
    }

    @Override
    public void showParticle(Location location, Particle particle, MaterialData materialData, float v, float v2, float v3, float v4, int i) {
        MyPlugin.fixme();
    }

    @Override
    public void removeAchievement(Achievement arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void resetPlayerTime() {
        MyPlugin.fixme();
    }

    @Override
    public void resetPlayerWeather() {
        MyPlugin.fixme();
    }

    @Override
    public void saveData() {
        ReflectionUtil.savePlayerData(player);
    }

    @Override
    public void sendBlockChange(Location arg0, Material arg1, byte arg2) {
        MyPlugin.fixme();
    }

    @Override
    public void sendBlockChange(Location arg0, int arg1, byte arg2) {
        MyPlugin.fixme();
    }

    @Override
    public boolean sendChunkChange(Location arg0, int arg1, int arg2, int arg3, byte[] arg4) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void sendMap(MapView arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void sendSignChange(Location arg0, String[] arg1) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public void setAllowFlight(boolean arg0) {
        player.setAllowFlight(arg0);
    }

    @Override
    public void setBedSpawnLocation(Location arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setBedSpawnLocation(Location arg0, boolean arg1) {
        MyPlugin.fixme();
    }

    @Override
    public void setExhaustion(float arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setExp(float arg0) {
        player.setExp(arg0);
    }

    @Override
    public void setFlySpeed(float arg0) throws IllegalArgumentException {
        player.setFlySpeed(arg0);
    }

    @Override
    public void setFlying(boolean arg0) {
        player.setFlying(arg0);
    }

    @Override
    public void setLevel(int arg0) {
        player.setLevel(arg0);
    }

    @Override
    public void setPlayerTime(long arg0, boolean arg1) {
        MyPlugin.fixme();
    }

    @Override
    public void setPlayerWeather(WeatherType arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setSaturation(float arg0) {
        ReflectionUtil.setFoodSaturation(player, arg0);
    }

    @Override
    public void setSleepingIgnored(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setSneaking(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setSprinting(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public void setStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
        MyPlugin.fixme();
    }

    @Override
    public void setStatistic(Statistic arg0, EntityType arg1, int arg2) {
        MyPlugin.fixme();
    }

    @Override
    public void setTotalExperience(int arg0) {
        player.setTotalExperience(arg0);
    }

    @Override
    public void setWalkSpeed(float arg0) throws IllegalArgumentException {
        player.setWalkSpeed(arg0);
    }

    @Override
    public void showPlayer(Player arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void updateInventory() {
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
        if (event.isCancelled()) return false;
        Location to = event.getTo();
        if (to == null) {
            to = location;
        }
        player.teleport(Util.getLocation(to));
        return true;
    }

    @Override
    public boolean teleport(Entity destination) {
        return teleport(destination, PlayerTeleportEvent.TeleportCause.PLUGIN);
    }

    @Override
    public Player.Spigot spigot() {
        return new Player.Spigot() {
            @Override
            public InetSocketAddress getRawAddress() {
                MyPlugin.fixme();
                return null;
            }

            @Override
            public void playEffect(Location location, Effect effect, int id, int data, float offsetX, float offsetY, float offsetZ, float speed, int particleCount, int radius) {
                MyPlugin.fixme();
            }

            @Override
            public boolean getCollidesWithEntities() {
                MyPlugin.fixme();
                return false;
            }

            @Override
            public void setCollidesWithEntities(boolean collides) {
                MyPlugin.fixme();
            }

            @Override
            public void respawn() {
                MyPlugin.fixme();
            }

            @Override
            public String getLocale() {
                MyPlugin.fixme();
                return "en";
            }

            @Override
            public Set<Player> getHiddenPlayers() {
                MyPlugin.fixme();
                return new HashSet<>();
            }

            @Override
            public boolean isInvulnerable() {
                MyPlugin.fixme();
                return false;
            }
        };
    }

    @Override
    public List<MetadataValue> getMetadata(String arg0) {
        return MyPlugin.playerMetadataStore.getMetadata(this, arg0);
    }

    @Override
    public boolean hasMetadata(String arg0) {
        return MyPlugin.playerMetadataStore.hasMetadata(this, arg0);
    }

    @Override
    public void removeMetadata(String arg0, Plugin arg1) {
        MyPlugin.playerMetadataStore.removeMetadata(this, arg0, arg1);
    }

    @Override
    public void setMetadata(String arg0, MetadataValue arg1) {
        MyPlugin.playerMetadataStore.setMetadata(this, arg0, arg1);
    }

    public void doTick(){
        if(noDamageTicks > 0)noDamageTicks--;
    }

    @Override
    public void setNoDamageTicks(int arg0) {
        noDamageTicks = arg0;
    }

    @Override
    public int getNoDamageTicks() {
        return noDamageTicks;
    }
}
