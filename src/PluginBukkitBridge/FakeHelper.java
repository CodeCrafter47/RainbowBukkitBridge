package PluginBukkitBridge;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import PluginReference.MC_DamageType;
import PluginReference.MC_EntityType;

public class FakeHelper
{

	public static DamageCause GetDamageCause(MC_DamageType dmg)
	{
		if(dmg == MC_DamageType.ANVIL) return DamageCause.FALLING_BLOCK;
		if(dmg == MC_DamageType.CACTUS) return DamageCause.CONTACT;
		if(dmg == MC_DamageType.DROWN) return DamageCause.DROWNING;
		if(dmg == MC_DamageType.FALL) return DamageCause.FALL;
		if(dmg == MC_DamageType.FALLING_BLOCK) return DamageCause.FALLING_BLOCK;
		if(dmg == MC_DamageType.IN_FIRE) return DamageCause.FIRE;
		if(dmg == MC_DamageType.IN_WALL) return DamageCause.SUFFOCATION;
		if(dmg == MC_DamageType.LAVA) return DamageCause.FIRE;

		if(dmg == MC_DamageType.LIGHTING_BOLT) return DamageCause.LIGHTNING;
		if(dmg == MC_DamageType.MAGIC) return DamageCause.MAGIC;
		if(dmg == MC_DamageType.ON_FIRE) return DamageCause.FIRE_TICK;
		if(dmg == MC_DamageType.OUT_OF_WORLD) return DamageCause.VOID;
		if(dmg == MC_DamageType.STARVE) return DamageCause.STARVATION;
		if(dmg == MC_DamageType.WITHER) return DamageCause.WITHER;

		if(dmg == MC_DamageType.UNSPECIFIED) return DamageCause.MAGIC;		
		if(dmg == MC_DamageType.GENERIC) return DamageCause.MAGIC;
		return DamageCause.MAGIC;
	}
	
	public static String LocStringShort(Location loc) {
		if (loc == null)
			return "NULL";
		return loc.getWorld().getName() + "(" + (int) loc.getBlockX() + ","
				+ (int) loc.getBlockY() + "," + (int) loc.getBlockZ() + ")";
	}

	  public static String GetDimensionName(int dimension)
	  {
	    if (dimension == 0) return "world";
	    if (dimension == -1) return "world_nether";
	    if (dimension == 1) return "world_the_end";
	    if(dimension == 2) return "PlotWorld";
	    return "Dimension " + dimension;
	  }
	
	  
	  public static EntityType GetEntityType(MC_EntityType et)
	  {
		  // 1.8 specific
		  if(et == MC_EntityType.ARMOR_STAND) return EntityType.UNKNOWN;
		  if(et == MC_EntityType.ENDERMITE) return EntityType.UNKNOWN;
		  if(et == MC_EntityType.GUARDIAN) return EntityType.UNKNOWN;
		  if(et == MC_EntityType.RABBIT) return EntityType.UNKNOWN;
		  
		  // legacy
		  if(et == MC_EntityType.ARROW) return EntityType.ARROW;
		  if(et == MC_EntityType.BAT) return EntityType.BAT;
		  if(et == MC_EntityType.BLAZE) return EntityType.BLAZE;
		  if(et == MC_EntityType.BOAT) return EntityType.BOAT;
		  if(et == MC_EntityType.CAVE_SPIDER) return EntityType.CAVE_SPIDER;
		  if(et == MC_EntityType.CHICKEN) return EntityType.CHICKEN;
		  if(et == MC_EntityType.COW) return EntityType.COW;
		  if(et == MC_EntityType.CREEPER) return EntityType.CREEPER;
		  if(et == MC_EntityType.ENDER_CRYSTAL) return EntityType.ENDER_CRYSTAL;
		  if(et == MC_EntityType.ENDERDRAGON) return EntityType.ENDER_DRAGON;
		  if(et == MC_EntityType.ENDERMAN) return EntityType.ENDERMAN;
		  if(et == MC_EntityType.EYE_OF_ENDER_SIGNAL) return EntityType.ENDER_SIGNAL;
		  if(et == MC_EntityType.FALLING_SAND) return EntityType.FALLING_BLOCK;
		  if(et == MC_EntityType.FIREBALL) return EntityType.FIREBALL;
		  if(et == MC_EntityType.FIREWORK) return EntityType.FIREWORK;
		  if(et == MC_EntityType.FISHING_HOOK) return EntityType.FISHING_HOOK;
		  if(et == MC_EntityType.GIANT) return EntityType.GIANT;
		  if(et == MC_EntityType.GHAST) return EntityType.GHAST;
		  if(et == MC_EntityType.HANGING) return EntityType.PAINTING;
		  if(et == MC_EntityType.HORSE) return EntityType.HORSE;

		  if(et == MC_EntityType.ITEM) return EntityType.DROPPED_ITEM;
		  if(et == MC_EntityType.LAVA_SLIME) return EntityType.MAGMA_CUBE;
		  if(et == MC_EntityType.MINECART) return EntityType.MINECART;
		  if(et == MC_EntityType.MUSHROOM_COW) return EntityType.MUSHROOM_COW;
		  if(et == MC_EntityType.OCELOT) return EntityType.OCELOT;
		  if(et == MC_EntityType.PIG) return EntityType.PIG;
		  if(et == MC_EntityType.PIG_ZOMBIE) return EntityType.PIG_ZOMBIE;
		  if(et == MC_EntityType.PLAYER) return EntityType.PLAYER;
		  if(et == MC_EntityType.PRIMED_TNT) return EntityType.PRIMED_TNT;
		  if(et == MC_EntityType.SHEEP) return EntityType.SHEEP;
		  if(et == MC_EntityType.SILVERFISH) return EntityType.SILVERFISH;
		  if(et == MC_EntityType.SKELETON) return EntityType.SKELETON;
		  if(et == MC_EntityType.SLIME) return EntityType.SLIME;
		  if(et == MC_EntityType.SMALL_FIREBALL) return EntityType.SMALL_FIREBALL;
		  if(et == MC_EntityType.SNOWBALL) return EntityType.SNOWBALL;
		  if(et == MC_EntityType.SNOWMAN) return EntityType.SNOWMAN;
		  if(et == MC_EntityType.SPIDER) return EntityType.SPIDER;
		  if(et == MC_EntityType.SQUID) return EntityType.SQUID;

		  if(et == MC_EntityType.THROWN_EGG) return EntityType.EGG;
		  if(et == MC_EntityType.THROWN_ENDERPEARL) return EntityType.ENDER_PEARL;
		  if(et == MC_EntityType.THROWN_EXP_BOTTLE) return EntityType.THROWN_EXP_BOTTLE;
		  if(et == MC_EntityType.THROWN_POTION) return EntityType.SPLASH_POTION;

		  if(et == MC_EntityType.VILLAGER) return EntityType.VILLAGER;
		  if(et == MC_EntityType.VILLAGER_GOLEM) return EntityType.IRON_GOLEM;
		  if(et == MC_EntityType.WITCH) return EntityType.WITCH;
		  if(et == MC_EntityType.WITHERBOSS) return EntityType.WITHER;
		  if(et == MC_EntityType.WOLF) return EntityType.WOLF;
		  if(et == MC_EntityType.XP_ORB) return EntityType.EXPERIENCE_ORB;
		  if(et == MC_EntityType.ZOMBIE) return EntityType.ZOMBIE;
		  
		  return EntityType.UNKNOWN;
	  }
	  
}
