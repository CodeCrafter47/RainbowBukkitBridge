package PluginBukkitBridge;

import PluginReference.MC_DamageType;
import PluginReference.MC_EntityType;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class FakeHelper {

    public static DamageCause GetDamageCause(MC_DamageType dmg) {

        if (dmg == MC_DamageType.ANVIL) return DamageCause.FALLING_BLOCK;
        if (dmg == MC_DamageType.CACTUS) return DamageCause.CONTACT;
        if (dmg == MC_DamageType.DROWN) return DamageCause.DROWNING;
        if (dmg == MC_DamageType.FALL) return DamageCause.FALL;
        if (dmg == MC_DamageType.FALLING_BLOCK) return DamageCause.FALLING_BLOCK;
        if (dmg == MC_DamageType.IN_FIRE) return DamageCause.FIRE;
        if (dmg == MC_DamageType.IN_WALL) return DamageCause.SUFFOCATION;
        if (dmg == MC_DamageType.LAVA) return DamageCause.FIRE;

        if (dmg == MC_DamageType.LIGHTING_BOLT) return DamageCause.LIGHTNING;
        if (dmg == MC_DamageType.MAGIC) return DamageCause.MAGIC;
        if (dmg == MC_DamageType.ON_FIRE) return DamageCause.FIRE_TICK;
        if (dmg == MC_DamageType.OUT_OF_WORLD) return DamageCause.VOID;
        if (dmg == MC_DamageType.STARVE) return DamageCause.STARVATION;
        if (dmg == MC_DamageType.WITHER) return DamageCause.WITHER;

        if (dmg == MC_DamageType.UNSPECIFIED) return DamageCause.MAGIC;
        if (dmg == MC_DamageType.GENERIC) return DamageCause.MAGIC;
        return DamageCause.MAGIC;
    }


    public static EntityType GetEntityType(MC_EntityType et) {
        // 1.8 specific
		switch (et) {
			case ARMOR_STAND:
				return EntityType.ARMOR_STAND;
			case ENDERMITE:
				return EntityType.ENDERMITE;
			case GUARDIAN:
				return EntityType.GUARDIAN;
			case RABBIT:
				return EntityType.RABBIT;

			// legacy
			case ARROW:
				return EntityType.ARROW;
			case BAT:
				return EntityType.BAT;
			case BLAZE:
				return EntityType.BLAZE;
			case BOAT:
				return EntityType.BOAT;
			case CAVE_SPIDER:
				return EntityType.CAVE_SPIDER;
			case CHICKEN:
				return EntityType.CHICKEN;
			case COW:
				return EntityType.COW;
			case CREEPER:
				return EntityType.CREEPER;
			case ENDER_CRYSTAL:
				return EntityType.ENDER_CRYSTAL;
			case ENDERDRAGON:
				return EntityType.ENDER_DRAGON;
			case ENDERMAN:
				return EntityType.ENDERMAN;
			case EYE_OF_ENDER_SIGNAL:
				return EntityType.ENDER_SIGNAL;
			case FALLING_SAND:
				return EntityType.FALLING_BLOCK;
			case FIREBALL:
				return EntityType.FIREBALL;
			case FIREWORK:
				return EntityType.FIREWORK;
			case FISHING_HOOK:
				return EntityType.FISHING_HOOK;
			case GIANT:
				return EntityType.GIANT;
			case GHAST:
				return EntityType.GHAST;
			case PAINTING:
				return EntityType.PAINTING;
			case HORSE:
				return EntityType.HORSE;
			case ITEM:
				return EntityType.DROPPED_ITEM;
			case LAVA_SLIME:
				return EntityType.MAGMA_CUBE;
			case MINECART:
				return EntityType.MINECART;
			case MUSHROOM_COW:
				return EntityType.MUSHROOM_COW;
			case OCELOT:
				return EntityType.OCELOT;
			case PIG:
				return EntityType.PIG;
			case PIG_ZOMBIE:
				return EntityType.PIG_ZOMBIE;
			case UNSPECIFIED:
				break;
			case PLAYER:
				return EntityType.PLAYER;
			case PRIMED_TNT:
				return EntityType.PRIMED_TNT;
			case SHEEP:
				return EntityType.SHEEP;
			case SILVERFISH:
				return EntityType.SILVERFISH;
			case SKELETON:
				return EntityType.SKELETON;
			case SLIME:
				return EntityType.SLIME;
			case SMALL_FIREBALL:
				return EntityType.SMALL_FIREBALL;
			case SNOWBALL:
				return EntityType.SNOWBALL;
			case SNOWMAN:
				return EntityType.SNOWMAN;
			case SPIDER:
				return EntityType.SPIDER;
			case SQUID:
				return EntityType.SQUID;
			case THROWN_EGG:
				return EntityType.EGG;
			case THROWN_ENDERPEARL:
				return EntityType.ENDER_PEARL;
			case THROWN_EXP_BOTTLE:
				return EntityType.THROWN_EXP_BOTTLE;
			case THROWN_POTION:
				return EntityType.SPLASH_POTION;
			case VILLAGER:
				return EntityType.VILLAGER;
			case VILLAGER_GOLEM:
				return EntityType.IRON_GOLEM;
			case WITCH:
				return EntityType.WITCH;
			case WITHERBOSS:
				return EntityType.WITHER;
			case WOLF:
				return EntityType.WOLF;
			case XP_ORB:
				return EntityType.EXPERIENCE_ORB;
			case ZOMBIE:
				return EntityType.ZOMBIE;
			case WITHER_SKULL:
				return EntityType.WITHER_SKULL;
			case LEASH_KNOT:
				return EntityType.LEASH_HITCH;
			case ITEM_FRAME:
				return EntityType.ITEM_FRAME;
			case LIGHTNING_BOLT:
				return EntityType.LIGHTNING;
			case SHULKER:
				return EntityType.SHULKER;
			case SHULKER_BULLET:
				return EntityType.SHULKER_BULLET;
			case AREA_EFFECT_CLOUD:
				return EntityType.AREA_EFFECT_CLOUD;
			case SPECTRAL_ARROW:
				return EntityType.SPECTRAL_ARROW;
			case DRAGON_FIREBALL:
				return EntityType.DRAGON_FIREBALL;
			case POLAR_BEAR:
				return EntityType.POLAR_BEAR;
			default:
                return EntityType.UNKNOWN;
        }
        return EntityType.UNKNOWN;
    }

}
