package PluginBukkitBridge;

import PluginReference.MC_GameMode;
import PluginReference.MC_Player;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.craftbukkit.CraftSound;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.map.MapView;
import org.bukkit.material.MaterialData;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import java.net.InetSocketAddress;
import java.util.*;


public class FakePlayer extends FakeEntity implements Player
{
    private MC_Player player;
    private UUID uuid;

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
        return player.getCustomName();
    }

    @Override
    public void setPlayerListName(String name) {
        player.setCustomName(name);
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
    public EntityType getType() {
        return EntityType.valueOf(player.getType().name());
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
    public PlayerInventory getInventory() {
        return new FakePlayerInventory(player);
    }

    @Override
    public ItemStack getItemInHand() {
        return Util.getItemStack(player.getItemInHand());
    }

    @Override
    public void setItemInHand(ItemStack item) {
        player.setItemInHand(Util.getItemStack(item));
    }


    @Override
    public boolean isSleeping() {
        return player.isSleeping();
    }


    @Override
    public GameMode getGameMode() {
        return GameMode.valueOf(player.getGameMode().name());
    }

    @Override
    public void setGameMode(GameMode mode) {
        player.setGameMode(MC_GameMode.valueOf(mode.name()));
    }


    @Override
    public void setCustomName(String name) {
        player.setCustomName(name);
    }

    @Override
    public String getCustomName() {
        return player.getCustomName();
    }


    @Override
    public double getHealth() {
        return player.getHealth();
    }

    @Override
    public int _INVALID_getHealth() {
        return (int) player.getHealth();
    }

    @Override
    public void setHealth(double health) {
        player.setHealth((float) health);
    }

    @Override
    public void _INVALID_setHealth(int health) {
        player.setHealth(health);
    }


    @Override
    public boolean isOp() {
        return player.isOp();
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
	public void closeInventory()
	{
		FakeDebug("closeInventory");
	}

	@Override
	public Inventory getEnderChest()
	{
		FakeDebug("getEnderChest");
		return null;
	}

	@Override
	public int getExpToLevel()
	{
		FakeDebug("getExpToLevel");
		return 0;
	}

	@Override
	public ItemStack getItemOnCursor()
	{
		FakeDebug("getItemOnCursor");
		return null;
	}

	@Override
	public InventoryView getOpenInventory()
	{
		FakeDebug("getOpenInventory");
		return null;
	}

	@Override
	public int getSleepTicks()
	{
		FakeDebug("getSleepTicks");
		return 0;
	}

	@Override
	public boolean isBlocking()
	{
		FakeDebug("isBlocking");
		return false;
	}

	@Override
	public InventoryView openEnchanting(Location arg0, boolean arg1)
	{
		FakeDebug("openEnchanting");
		return null;
	}

	@Override
	public InventoryView openInventory(Inventory arg0)
	{
		FakeDebug("openInventory");
		return null;
	}

	@Override
	public void openInventory(InventoryView arg0)
	{
		FakeDebug("openInventory");
	}

	@Override
	public InventoryView openWorkbench(Location arg0, boolean arg1)
	{
		FakeDebug("openWorkbench");
		return null;
	}

	@Override
	public void setItemOnCursor(ItemStack arg0)
	{
		FakeDebug("setItemOnCursor");
	}

	@Override
	public boolean setWindowProperty(Property arg0, int arg1)
	{
		FakeDebug("setWindowProperty");
		return false;
	}

	@Override
	public int _INVALID_getLastDamage()
	{
		FakeDebug("_INVALID_getLastDamage");
		return 0;
	}

	@Override
	public void _INVALID_setLastDamage(int arg0)
	{
		FakeDebug("_INVALID_setLastDamage");
	}

	@Override
	public boolean addPotionEffect(PotionEffect arg0)
	{
		FakeDebug("addPotionEffect");
		return false;
	}

	@Override
	public boolean addPotionEffect(PotionEffect arg0, boolean arg1)
	{
		FakeDebug("addPotionEffect");
		return false;
	}

	@Override
	public boolean addPotionEffects(Collection<PotionEffect> arg0)
	{
		FakeDebug("addPotionEffects");
		return false;
	}

	@Override
	public Collection<PotionEffect> getActivePotionEffects()
	{
		FakeDebug("getActivePotionEffects");
		return null;
	}

	@Override
	public boolean getCanPickupItems()
	{
		FakeDebug("getCanPickupItems");
		return false;
	}

	@Override
	public EntityEquipment getEquipment()
	{
		FakeDebug("getEquipment");
		return null;
	}

	@Override
	public double getEyeHeight()
	{
		FakeDebug("getEyeHeight");
		return 0;
	}

	@Override
	public double getEyeHeight(boolean arg0)
	{
		FakeDebug("getEyeHeight");
		return 0;
	}

	@Override
	public Location getEyeLocation()
	{
		FakeDebug("getEyeLocation");
		return null;
	}

	@Override
	public Player getKiller()
	{
		FakeDebug("getKiller");
		return null;
	}

	@Override
	public double getLastDamage()
	{
		FakeDebug("getLastDamage");
		return 0;
	}

	@Override
	public List<Block> getLastTwoTargetBlocks(HashSet<Byte> arg0, int arg1)
	{
		FakeDebug("getLastTwoTargetBlocks");
		return null;
	}

	@Override
	public Entity getLeashHolder() throws IllegalStateException
	{
		FakeDebug("getLeashHolder");
		return null;
	}

	@Override
	public List<Block> getLineOfSight(HashSet<Byte> arg0, int arg1)
	{
		FakeDebug("getLineOfSight");
		return null;
	}

	@Override
	public int getMaximumAir()
	{
		FakeDebug("getMaximumAir");
		return 0;
	}

	@Override
	public int getMaximumNoDamageTicks()
	{
		FakeDebug("getMaximumNoDamageTicks");
		return 0;
	}

	@Override
	public int getNoDamageTicks()
	{
		FakeDebug("getNoDamageTicks");
		return 0;
	}

	@Override
	public int getRemainingAir()
	{
		FakeDebug("getRemainingAir");
		return 0;
	}

	@Override
	public boolean getRemoveWhenFarAway()
	{
		FakeDebug("getRemoveWhenFarAway");
		return false;
	}

	@Override
	public Block getTargetBlock(HashSet<Byte> arg0, int arg1)
	{
		FakeDebug("getTargetBlock");
		return null;
	}

	@Override
	public boolean hasLineOfSight(Entity arg0)
	{
		FakeDebug("hasLineOfSight");
		return false;
	}

	@Override
	public boolean hasPotionEffect(PotionEffectType arg0)
	{
		FakeDebug("hasPotionEffect");
		return false;
	}

	@Override
	public boolean isCustomNameVisible()
	{
		FakeDebug("isCustomNameVisible");
		return false;
	}

	@Override
	public boolean isLeashed()
	{
		FakeDebug("isLeashed");
		return false;
	}

	@Override
	public void removePotionEffect(PotionEffectType arg0)
	{
		FakeDebug("removePotionEffect");
	}

	@Override
	public void setCanPickupItems(boolean arg0)
	{
		FakeDebug("setCanPickupItems");
	}

	@Override
	public void setCustomNameVisible(boolean arg0)
	{
		FakeDebug("setCustomNameVisible");
	}

	@Override
	public void setLastDamage(double arg0)
	{
		FakeDebug("setLastDamage");
	}

	@Override
	public boolean setLeashHolder(Entity arg0)
	{
		FakeDebug("setLeashHolder");
		return false;
	}

	@Override
	public void setMaximumAir(int arg0)
	{
		FakeDebug("setMaximumAir");
	}

	@Override
	public void setMaximumNoDamageTicks(int arg0)
	{
		FakeDebug("setMaximumNoDamageTicks");
	}

	@Override
	public void setNoDamageTicks(int arg0)
	{
		FakeDebug("setNoDamageTicks");
	}

	@Override
	public void setRemainingAir(int arg0)
	{
		FakeDebug("setRemainingAir");
	}

	@Override
	public void setRemoveWhenFarAway(boolean arg0)
	{
		FakeDebug("setRemoveWhenFarAway");
	}

	@Override
	public Arrow shootArrow()
	{
		FakeDebug("shootArrow");
		return null;
	}

	@Override
	public Egg throwEgg()
	{
		FakeDebug("throwEgg");
		return null;
	}

	@Override
	public Snowball throwSnowball()
	{
		FakeDebug("throwSnowball");
		return null;
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
	public EntityDamageEvent getLastDamageCause()
	{
		FakeDebug("getLastDamageCause");
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
	public int getTicksLived()
	{
		FakeDebug("getTicksLived");
		return 0;
	}

	@Override
	public Entity getVehicle()
	{
		FakeDebug("getVehicle");
		return null;
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
	public void setLastDamageCause(EntityDamageEvent arg0)
	{
		FakeDebug("setLastDamageCause");
	}

	@Override
	public boolean setPassenger(Entity arg0)
	{
		FakeDebug("setPassenger");
		return false;
	}

	@Override
	public void setTicksLived(int arg0)
	{
		FakeDebug("setTicksLived");
	}

	@Override
	public void _INVALID_damage(int arg0)
	{
		FakeDebug("_INVALID_damage");
	}

	@Override
	public void _INVALID_damage(int arg0, Entity arg1)
	{
		FakeDebug("_INVALID_damage");
	}

	@Override
	public int _INVALID_getMaxHealth()
	{
		FakeDebug("_INVALID_getMaxHealth");
		return 0;
	}

	@Override
	public void _INVALID_setMaxHealth(int arg0)
	{
		FakeDebug("_INVALID_setMaxHealth");
	}

	@Override
	public void damage(double arg0)
	{
		FakeDebug("damage");
	}

	@Override
	public void damage(double arg0, Entity arg1)
	{
		FakeDebug("damage");
	}

	@Override
	public double getMaxHealth()
	{
		FakeDebug("getMaxHealth");
		return 0;
	}

	@Override
	public void resetMaxHealth()
	{
		FakeDebug("resetMaxHealth");
	}

	@Override
	public void setMaxHealth(double arg0)
	{
		FakeDebug("setMaxHealth");
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> arg0)
	{
		FakeDebug("launchProjectile");
		return null;
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> arg0, Vector arg1)
	{
		FakeDebug("launchProjectile");
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0)
	{
		FakeDebug("addAttachment");
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, int arg1)
	{
		FakeDebug("addAttachment");
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2)
	{
		FakeDebug("addAttachment");
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2, int arg3)
	{
		FakeDebug("addAttachment");
		return null;
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions()
	{
		FakeDebug("getEffectivePermissions");
		return null;
	}

	@Override
	public boolean hasPermission(String perm)
	{
		return PermissionHelper.hasPermission(player,  perm);
	}

	@Override
	public boolean hasPermission(Permission arg0)
	{
		FakeDebug("hasPermission");
		return false;
	}

	@Override
	public boolean isPermissionSet(String arg0)
	{
		FakeDebug("isPermissionSet");
		return false;
	}

	@Override
	public boolean isPermissionSet(Permission arg0)
	{
		FakeDebug("isPermissionSet");
		return false;
	}

	@Override
	public void recalculatePermissions()
	{
		FakeDebug("recalculatePermissions");
	}

	@Override
	public void removeAttachment(PermissionAttachment arg0)
	{
		FakeDebug("removeAttachment");
	}

	@Override
	public void setOp(boolean arg0)
	{
		FakeDebug("setOp");
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
	public void playEffect(Location arg0, Effect arg1, int arg2)
	{
				FakeDebug("playEffect");
		
	}

	@Override
	public <T> void playEffect(Location arg0, Effect arg1, T arg2)
	{
				FakeDebug("playEffect");
		
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
