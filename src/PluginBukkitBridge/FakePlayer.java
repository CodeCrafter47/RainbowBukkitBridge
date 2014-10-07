package PluginBukkitBridge;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import PluginReference.MC_ItemStack;
import PluginReference.MC_Location;
import PluginReference.MC_Player;


public class FakePlayer implements Player
{
	public String m_loginName = null;
	public InetSocketAddress m_inetSocketAddress = null;
	public MC_Player m_player = null;
	
	public FakePlayer()
	{
		
	}
	public FakePlayer(MC_Player argPlayer)
	{
		m_player = argPlayer;
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
	public GameMode getGameMode()
	{
		FakeDebug("getGameMode");
		return null;
	}

	@Override
	public PlayerInventory getInventory()
	{
		FakeDebug("getInventory");
		return null;
	}

	@Override
	public ItemStack getItemInHand()
	{
		MC_ItemStack is =  m_player.getItemInHand();
		return new ItemStack(is.getId(), is.getCount());
	}

	@Override
	public ItemStack getItemOnCursor()
	{
		FakeDebug("getItemOnCursor");
		return null;
	}

	@Override
	public String getName()
	{
		if(m_loginName != null) return m_loginName;
		return m_player.getName();
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
	public boolean isSleeping()
	{
		FakeDebug("isSleeping");
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
	public void setGameMode(GameMode arg0)
	{
		FakeDebug("setGameMode");	
	}

	@Override
	public void setItemInHand(ItemStack arg0)
	{
		FakeDebug("setItemInHand");
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
	public String getCustomName()
	{
		FakeDebug("getCustomName");
		return null;
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
	public void setCustomName(String arg0)
	{
		FakeDebug("setCustomName");
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
	public float getFallDistance()
	{
		FakeDebug("getFallDistance");
		return 0;
	}

	@Override
	public int getFireTicks()
	{
		FakeDebug("getFireTicks");
		return 0;
	}

	@Override
	public EntityDamageEvent getLastDamageCause()
	{
		FakeDebug("getLastDamageCause");
		return null;
	}

	@Override
	public Location getLocation()
	{
		MC_Location mloc = m_player.getLocation();
		
		World world = Bukkit.getWorld(FakeHelper.GetDimensionName(mloc.dimension));
		Location loc = new Location(world, mloc.x, mloc.y, mloc.z);
		
		//FakeDebug(" --- getLocation returning: " + FakeHelper.LocStringShort(loc));
		return loc;
	}

	@Override
	public Location getLocation(Location arg0)
	{
		FakeDebug("getLocation");
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
	public Server getServer()
	{
		//FakeDebug("getServer");
		return MyPlugin.fakeServer;
		//return null;
	}

	@Override
	public int getTicksLived()
	{
		FakeDebug("getTicksLived");
		return 0;
	}

	@Override
	public EntityType getType()
	{
		FakeDebug("getType");
		return null;
	}

	@Override
	public UUID getUniqueId()
	{
		//FakeDebug("getUniqueId");
		if(m_player != null) return m_player.getUUID();
		return null;
	}

	@Override
	public Entity getVehicle()
	{
		FakeDebug("getVehicle");
		return null;
	}

	@Override
	public Vector getVelocity()
	{
		FakeDebug("getVelocity");
		return null;
	}

	@Override
	public World getWorld()
	{
		return new FakeWorld(m_player.getWorld());
	}

	@Override
	public boolean isDead()
	{
		FakeDebug("isDead");
		return false;
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
	public boolean isValid()
	{
		FakeDebug("isValid");
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
	public void setFallDistance(float arg0)
	{
		FakeDebug("setFallDistance");
	}

	@Override
	public void setFireTicks(int arg0)
	{
		FakeDebug("setFireTicks");
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
	public void setVelocity(Vector arg0)
	{
		FakeDebug("setVelocity");
	}

	@Override
	public boolean teleport(Location arg0)
	{
		FakeDebug("teleport");
		return false;
	}

	@Override
	public boolean teleport(Entity arg0)
	{
		FakeDebug("teleport");
		return false;
	}

	@Override
	public boolean teleport(Location arg0, TeleportCause arg1)
	{
		FakeDebug("teleport");
		return false;
	}

	@Override
	public boolean teleport(Entity arg0, TeleportCause arg1)
	{
		FakeDebug("teleport");
		return false;
	}

	@Override
	public List<MetadataValue> getMetadata(String arg0)
	{
		FakeDebug("getMetadata");
		return null;
	}

	@Override
	public boolean hasMetadata(String arg0)
	{
		FakeDebug("hasMetadata");
		return false;
	}

	@Override
	public void removeMetadata(String arg0, Plugin arg1)
	{
		FakeDebug("removeMetadata");
	}

	@Override
	public void setMetadata(String arg0, MetadataValue arg1)
	{
		FakeDebug("setMetadata");
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
	public int _INVALID_getHealth()
	{
		FakeDebug("_INVALID_getHealth");
		return 0;
	}

	@Override
	public int _INVALID_getMaxHealth()
	{
		FakeDebug("_INVALID_getMaxHealth");
		return 0;
	}

	@Override
	public void _INVALID_setHealth(int arg0)
	{
		FakeDebug("_INVALID_setHealth");
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
	public double getHealth()
	{
		FakeDebug("getHealth");
		return 0;
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
	public void setHealth(double arg0)
	{
		FakeDebug("setHealth");
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
		return PermissionHelper.hasPermission(m_player,  perm);
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
	public boolean isOp()
	{
		if(m_player != null) return m_player.isOp();
		return true;
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
	public void sendMessage(String arg0)
	{
		m_player.sendMessage(arg0);
	}

	@Override
	public void sendMessage(String[] arg0)
	{
		FakeDebug("sendMessage2");
		for(String piece : arg0) sendMessage(piece);
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
	public Player getPlayer()
	{
		FakeDebug("getPlayer");
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
		FakeDebug("isOnline");
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
	public InetSocketAddress getAddress()
	{
		return m_inetSocketAddress;
	}

	@Override
	public boolean getAllowFlight()
	{
		return m_player.isAllowedFlight();
	}

	@Override
	public Location getBedSpawnLocation()
	{
				FakeDebug("getBedSpawnLocation");
		return null;
	}

	@Override
	public Location getCompassTarget()
	{
		MC_Location mloc = m_player.getCompassTarget();
		
		World world = Bukkit.getWorld(FakeHelper.GetDimensionName(mloc.dimension));
		Location loc = new Location(world, mloc.x, mloc.y, mloc.z);
		
		return loc;
	}

	@Override
	public String getDisplayName()
	{
		return m_player.getName();
	}

	@Override
	public float getExhaustion()
	{
		FakeDebug("getExhaustion");
		return 0;
	}

	@Override
	public float getExp()
	{
		return m_player.getExp();
	}

	@Override
	public float getFlySpeed()
	{
		return m_player.getFlySpeed();
	}

	@Override
	public int getFoodLevel()	
	{
		return m_player.getFoodLevel();
	}

	@Override
	public double getHealthScale()
	{
		FakeDebug("getHealthScale");
		return 0;
	}

	@Override
	public int getLevel()
	{
		return m_player.getLevel();
	}

	@Override
	public String getPlayerListName()
	{
		return m_player.getName();
	}

	@Override
	public long getPlayerTime()
	{
		FakeDebug("getPlayerTime");
		return 0;
	}

	@Override
	public long getPlayerTimeOffset()
	{
				FakeDebug("getPlayerTimeOffset");
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
	public Scoreboard getScoreboard()
	{
				FakeDebug("getScoreboard");
		return null;
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
	public int getTotalExperience()
	{
				FakeDebug("getTotalExperience");
		return 0;
	}

	@Override
	public float getWalkSpeed()
	{
		return m_player.getWalkSpeed();
	}

	@Override
	public void giveExp(int arg0)
	{
		m_player.giveExp(arg0);
	}

	@Override
	public void giveExpLevels(int arg0)
	{
		m_player.giveExpLevels(arg0);
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
	public boolean isFlying()
	{
		return m_player.isFlying();
	}

	@Override
	public boolean isHealthScaled()
	{
				FakeDebug("isHealthScaled");
		return false;
	}

	@Override
	public boolean isOnGround()
	{
				FakeDebug("isOnGround");
		return false;
	}

	@Override
	public boolean isPlayerTimeRelative()
	{
				FakeDebug("isPlayerTimeRelative");
		return false;
	}

	@Override
	public boolean isSleepingIgnored()
	{
				FakeDebug("isSleepingIgnored");
		return false;
	}

	@Override
	public boolean isSneaking()
	{
		return m_player.isSneaking();
	}

	@Override
	public boolean isSprinting()
	{
		return m_player.isSprinting();
	}

	@Override
	public void kickPlayer(String arg0)
	{
		MyPlugin.server.executeCommand("kick " + m_player.getName() + " " + arg0);
	}

	@Override
	public void loadData()
	{
				FakeDebug("loadData");
		
	}

	@Override
	public boolean performCommand(String arg0)
	{
		m_player.executeCommand(arg0);
		return true;
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
	public void playNote(Location arg0, byte arg1, byte arg2)
	{
		FakeDebug("playNote");
	}

	@Override
	public void playNote(Location arg0, Instrument arg1, Note arg2)
	{
		FakeDebug("playNote");
	}

	@Override
	public void playSound(Location arg0, Sound arg1, float arg2, float arg3)
	{
		FakeDebug("playSound");
	}

	@Override
	public void playSound(Location arg0, String arg1, float arg2, float arg3)
	{
		FakeDebug("playSound");
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
	public void sendRawMessage(String arg0)
	{
		FakeDebug("sendRawMessage");
	}

	@Override
	public void sendSignChange(Location arg0, String[] arg1) throws IllegalArgumentException
	{
		FakeDebug("sendSignChange");
	}

	@Override
	public void setAllowFlight(boolean arg0)
	{
		m_player.setAllowFlight(arg0);
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
	public void setCompassTarget(Location arg0)
	{
		FakeDebug("setCompassTarget");
	}

	@Override
	public void setDisplayName(String arg0)
	{
		FakeDebug("setDisplayName");
	}

	@Override
	public void setExhaustion(float arg0)
	{
		FakeDebug("setExhaustion");
	}

	@Override
	public void setExp(float arg0)
	{
		m_player.setExp(arg0);
	}

	@Override
	public void setFlySpeed(float arg0) throws IllegalArgumentException
	{
		m_player.setFlySpeed(arg0);
	}

	@Override
	public void setFlying(boolean arg0)
	{
		m_player.setFlying(arg0);
	}

	@Override
	public void setFoodLevel(int arg0)
	{
		FakeDebug("setFoodLevel");
	}

	@Override
	public void setHealthScale(double arg0) throws IllegalArgumentException
	{
		FakeDebug("setHealthScale");
	}

	@Override
	public void setHealthScaled(boolean arg0)
	{
		FakeDebug("setHealthScaled");
	}

	@Override
	public void setLevel(int arg0)
	{
		m_player.setLevel(arg0);
	}

	@Override
	public void setPlayerListName(String arg0)
	{
		FakeDebug("setPlayerListName");
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
	public void setResourcePack(String arg0)
	{
		FakeDebug("setResourcePack");
	}

	@Override
	public void setSaturation(float arg0)
	{
		FakeDebug("setSaturation");
	}

	@Override
	public void setScoreboard(Scoreboard arg0) throws IllegalArgumentException, IllegalStateException
	{
		FakeDebug("setScoreboard");
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
	public void setTexturePack(String arg0)
	{
		FakeDebug("setTexturePack");
	}

	@Override
	public void setTotalExperience(int arg0)
	{
		m_player.setTotalExperience(arg0);
	}

	@Override
	public void setWalkSpeed(float arg0) throws IllegalArgumentException
	{
		m_player.setWalkSpeed(arg0);
	}

	@Override
	public void showPlayer(Player arg0)
	{
		FakeDebug("showPlayer");
	}

	@Override
	public void updateInventory()
	{
		m_player.updateInventory();
	}

}
