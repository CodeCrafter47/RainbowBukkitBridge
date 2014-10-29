package PluginBukkitBridge;

import PluginBukkitBridge.block.*;
import PluginBukkitBridge.entity.*;
import PluginReference.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Util {

    public static MC_Location getLocation(Location loc) {
        return new MC_Location(loc.getX(), loc.getY(), loc.getZ(), loc.getWorld().getEnvironment().getId(), loc.getYaw(), loc.getPitch());
    }

    public static Location getLocation(MC_Location loc) {
        return new Location(WorldManager.getWorld(MyPlugin.server.getWorld(loc.dimension).getName()), loc.x, loc.y, loc.z, loc.yaw, loc.pitch);
    }

    public static Vector getDirection(MC_MotionData md) {
        return new Vector(md.xMotion, md.yMotion, md.zMotion);
    }

    public static MC_MotionData getMotionData(Vector v, MC_MotionData md) {
        md.xMotion = v.getX();
        md.yMotion = v.getY();
        md.zMotion = v.getZ();
        return md;
    }

    public static ItemStack getItemStack(MC_ItemStack i) {
        if (i == null || i.getId() == 0 || i.getCount() == 0) return null;
        ItemStack is = new ItemStack(i.getId(), i.getCount(), (short) i.getDamage());
        is.setItemMeta(new FakeItemMeta(i));
        return is;
    }

    public static MC_ItemStack getItemStack(final ItemStack is) {
        if (is == null || is.getType() == Material.AIR) {
            MC_ItemStack is2 = MyPlugin.server.createItemStack(1, 1, 1);
            try {
                is2.getClass().getDeclaredField("is").set(is2, null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            return is2;
        }
        MC_ItemStack is2 = ((FakeItemMeta) is.getItemMeta()).is;
        is2.setCount(is.getAmount());
        is2.setDamage(is.getDurability());
        return is2;
    }

    public static BlockFace getFace(MC_DirectionNESWUD dir) {
        switch (dir) {
            case UNSPECIFIED:
                return BlockFace.SELF;
            case DOWN:
                return BlockFace.DOWN;
            case UP:
                return BlockFace.UP;
            case NORTH:
                return BlockFace.NORTH;
            case SOUTH:
                return BlockFace.SOUTH;
            case WEST:
                return BlockFace.WEST;
            case EAST:
                return BlockFace.EAST;
        }
        return BlockFace.SELF;
    }

    public static MC_Enchantment wrapEnchantment(Enchantment enchantment, int level) {
        MC_EnchantmentType type = wrapEnchantmentType(enchantment.getId());
        if (type == null) {
            MyPlugin.logger.warning("Unknown Enchantment ID: " + enchantment.getId());
            return null;
        }
        return new MC_Enchantment(type, level);
    }

    public static MC_EnchantmentType wrapEnchantmentType(int id) {
        switch (id) {
            case 0:
                return MC_EnchantmentType.PROTECTION;
            case 1:
                return MC_EnchantmentType.FIRE_PROTECTION;
            case 2:
                return MC_EnchantmentType.FEATHER_FALLING;
            case 3:
                return MC_EnchantmentType.BLAST_PROTECTION;
            case 4:
                return MC_EnchantmentType.PROJECTILE_PROTECTION;
            case 5:
                return MC_EnchantmentType.RESPIRATION;
            case 6:
                return MC_EnchantmentType.AQUA_AFFINITY;
            case 7:
                return MC_EnchantmentType.THORNS;
            case 8:
                return MC_EnchantmentType.DEPTH_STRIDER;
            case 16:
                return MC_EnchantmentType.SHARPNESS;
            case 17:
                return MC_EnchantmentType.SMITE;
            case 18:
                return MC_EnchantmentType.BANE_OF_ARTHROPODS;
            case 19:
                return MC_EnchantmentType.KNOCKBACK;
            case 20:
                return MC_EnchantmentType.FIRE_ASPECT;
            case 21:
                return MC_EnchantmentType.LOOTING;
            case 32:
                return MC_EnchantmentType.EFFICIENCY;
            case 33:
                return MC_EnchantmentType.SILK_TOUCH;
            case 34:
                return MC_EnchantmentType.UNBREAKING;
            case 35:
                return MC_EnchantmentType.FORTUNE;
            case 48:
                return MC_EnchantmentType.POWER;
            case 49:
                return MC_EnchantmentType.PUNCH;
            case 50:
                return MC_EnchantmentType.FLAME;
            case 51:
                return MC_EnchantmentType.INFINITY;
            case 61:
                return MC_EnchantmentType.LUCK_OF_THE_SEA;
            case 62:
                return MC_EnchantmentType.LURE;
        }
        return null;
    }

    public static Enchantment wrapEnchantmentType(MC_EnchantmentType enchantment) {
        return Enchantment.getByName(enchantment.name());
    }

    public static Entity wrapEntity(MC_Entity entity) {
        if (entity == null) return null;
        try {
            switch (entity.getType()) {
                case UNSPECIFIED:
                    return new FakeEntity(entity);
                case PLAYER:
                    return PlayerManager.getPlayer((MC_Player) entity);
                case BAT:
                    return new FakeBat(entity);
                case CHICKEN:
                    return new FakeChicken((MC_EntityAgeable) entity);
                case COW:
                    return new FakeCow((MC_EntityAgeable) entity);
                case HORSE:
                    return new FakeHorse((MC_Horse) entity);
                case MUSHROOM_COW:
                    return new FakeMushroomCow((MC_EntityAgeable) entity);
                case OCELOT:
                    return new FakeOcelot((MC_Ocelot) entity);
                case PIG:
                    return new FakePig((MC_EntityAgeable) entity);
                case RABBIT:
                    // fixme
                    return new FakeAnimal((MC_EntityAgeable) entity);
                case SHEEP:
                    return new FakeSheep((MC_EntityAgeable) entity);
                case SNOWMAN:
                    return new FakeSnowman(entity);
                case SQUID:
                    return new FakeSquid(entity);
                case VILLAGER_GOLEM:
                    return new FakeGolem(entity);
                case WOLF:
                    return new FakeWolf((MC_Wolf) entity);
                case VILLAGER:
                    return new FakeVillager((MC_EntityAgeable) entity);
                case ENDERDRAGON:
                    return new FakeEnderdragon(entity);
                case WITHERBOSS:
                    return new FakeWither(entity);
                case BLAZE:
                    return new FakeBlaze(entity);
                case CAVE_SPIDER:
                    return new FakeCaveSpider(entity);
                case CREEPER:
                    return new FakeCreeper(entity);
                case ENDERMAN:
                    return new FakeEnderman((MC_Enderman) entity);
                case ENDERMITE:
                    // fixme
                    break;
                case GHAST:
                    return new FakeGhast(entity);
                case GIANT:
                    return new FakeGiant(entity);
                case GUARDIAN:
                    // fixme
                    break;
                case LAVA_SLIME:
                    return new FakeMagmaCube(entity);
                case PIG_ZOMBIE:
                    return new FakePigZombie((MC_Zombie) entity);
                case SILVERFISH:
                    return new FakeSilverfish(entity);
                case SKELETON:
                    return new FakeSkeleton((MC_Skeleton) entity);
                case SLIME:
                    return new FakeSlime(entity);
                case SPIDER:
                    return new FakeSpider(entity);
                case WITCH:
                    return new FakeWitch(entity);
                case ZOMBIE:
                    return new FakeZombie((MC_Zombie) entity);
                case FISHING_HOOK:
                    return new FakeFishingHook(entity);
                case ARROW:
                    return new FakeArrow(entity);
                case SMALL_FIREBALL:
                    return new FakeSmallFireball(entity);
                case FIREBALL:
                    return new FakeLargeFireball(entity);
                case SNOWBALL:
                    return new FakeSnowball(entity);
                case THROWN_ENDERPEARL:
                    return new FakeEnderPearl(entity);
                case EYE_OF_ENDER_SIGNAL:
                    return new FakeEnderSignal(entity);
                case THROWN_EGG:
                    return new FakeEgg(entity);
                case THROWN_POTION:
                    return new FakeThrownPotion(entity);
                case THROWN_EXP_BOTTLE:
                    return new FakeThrownExpBottle(entity);
                case FIREWORK:
                    return new FakeFirework(entity);
                case ITEM:
                    return new FakeItem(entity);
                case MINECART:
                    return new FakeMinecart(entity);
                case BOAT:
                    return new FakeBoat(entity);
                case PRIMED_TNT:
                    return new FakeTNTPrimed(entity);
                case FALLING_SAND:
                    return new FakeFallingBlock(entity);
                case HANGING:
                    return new FakeHanging(entity);
                case ARMOR_STAND:
                    // fixme
                    break;
                case XP_ORB:
                    return new FakeExperienceOrb(entity);
                case ENDER_CRYSTAL:
                    return new FakeEnderCrystal(entity);
            }
        } catch (Exception ignored) {
            if (MyPlugin.DebugMode) ignored.printStackTrace();
        }
        if (MyPlugin.DebugMode) MyPlugin.fixme("unable to create specific wrapper for " + entity.getType());
        return new FakeEntity(entity);
    }

    public static Horse.Variant wrapHorseType(MC_HorseType type) {
        switch (type) {
            case HORSE:
                return Horse.Variant.HORSE;
            case DONKEY:
                return Horse.Variant.DONKEY;
            case MULE:
                return Horse.Variant.MULE;
            case ZOMBIE:
                return Horse.Variant.UNDEAD_HORSE;
            case SKELETON:
                return Horse.Variant.SKELETON_HORSE;
            case UNKNOWN:
                return Horse.Variant.HORSE;
        }
        return Horse.Variant.HORSE;
    }

    public static MC_HorseType wrapHorseType(Horse.Variant type) {
        switch (type) {
            case HORSE:
                return MC_HorseType.HORSE;
            case DONKEY:
                return MC_HorseType.DONKEY;
            case MULE:
                return MC_HorseType.MULE;
            case UNDEAD_HORSE:
                return MC_HorseType.ZOMBIE;
            case SKELETON_HORSE:
                return MC_HorseType.SKELETON;
        }
        return MC_HorseType.UNKNOWN;
    }

    public static MC_HorseVariant wrapHorseVariant(Horse.Style type) {
        MyPlugin.fixme("cry");
        switch (type) {
            case NONE:
                return MC_HorseVariant.BROWN;
            case WHITE:
                return MC_HorseVariant.WHITE;
            case WHITEFIELD:
                return MC_HorseVariant.CREAMY;
            case WHITE_DOTS:
                return MC_HorseVariant.CHESTNUT;
            case BLACK_DOTS:
                return MC_HorseVariant.DARK_BROWN;
        }
        return MC_HorseVariant.UNKNOWN;
    }

    public static Horse.Style wrapHorseVariant(MC_HorseVariant type) {
        MyPlugin.fixme("cry");
        switch (type) {
            case WHITE:
                return Horse.Style.WHITE;
            case CREAMY:
                return Horse.Style.WHITEFIELD;
            case CHESTNUT:
                return Horse.Style.WHITE_DOTS;
            case BROWN:
                return Horse.Style.NONE;
            case BLACK:
                return Horse.Style.NONE;
            case GRAY:
                return Horse.Style.NONE;
            case DARK_BROWN:
                return Horse.Style.NONE;
            case UNKNOWN:
                return Horse.Style.NONE;
        }
        return Horse.Style.NONE;
    }

    public static BlockState wrapBlockState(FakeBlock fakeBlock) {
        if (fakeBlock.getType() == Material.BEACON) return new FakeBeacon(fakeBlock);
        if (fakeBlock.getType() == Material.BREWING_STAND) return new FakeBrewingStand(fakeBlock);
        if (fakeBlock.getType() == Material.CHEST || fakeBlock.getType() == Material.TRAPPED_CHEST)
            return new FakeChest(fakeBlock);
        if (fakeBlock.getType() == Material.COMMAND) return new FakeCommandBlock(fakeBlock);
        if (fakeBlock.getType() == Material.MOB_SPAWNER) return new FakeCreatureSpawner(fakeBlock);
        if (fakeBlock.getType() == Material.DISPENSER) return new FakeDispenser(fakeBlock);
        if (fakeBlock.getType() == Material.DROPPER) return new FakeDropper(fakeBlock);
        if (fakeBlock.getType() == Material.FURNACE || fakeBlock.getType() == Material.BURNING_FURNACE)
            return new FakeFurnace(fakeBlock);
        if (fakeBlock.getType() == Material.HOPPER) return new FakeHopper(fakeBlock);
        if (fakeBlock.getType() == Material.JUKEBOX) return new FakeJukebox(fakeBlock);
        if (fakeBlock.getType() == Material.NOTE_BLOCK) return new FakeNoteBlock(fakeBlock);
        if (fakeBlock.getType() == Material.SIGN_POST || fakeBlock.getType() == Material.SIGN || fakeBlock.getType() == Material.WALL_SIGN)
            return new FakeSign(fakeBlock);
        if (fakeBlock.getType() == Material.SKULL) return new FakeSkull(fakeBlock);
        return new FakeBlockState(fakeBlock);
    }

    public static Skeleton.SkeletonType getSkeletonType(MC_SkeletonType type) {
        switch (type) {
            case UNSPECIFIED:
                return Skeleton.SkeletonType.NORMAL;
            case SKELETON:
                return Skeleton.SkeletonType.NORMAL;
            case WITHER_SKELETON:
                return Skeleton.SkeletonType.WITHER;
        }
        return Skeleton.SkeletonType.NORMAL;
    }

    public static MC_SkeletonType getSkeletonType(Skeleton.SkeletonType type) {
        switch (type) {
            case NORMAL:
                return MC_SkeletonType.SKELETON;
            case WITHER:
                return MC_SkeletonType.WITHER_SKELETON;
        }
        return MC_SkeletonType.UNSPECIFIED;
    }

    public static MC_GameRuleType getGameRule(String arg0) {
        return MC_GameRuleType.valueOf(arg0);
    }

    public static MC_EntityType getEntityType(EntityType arg1) {
        switch (arg1) {
            case DROPPED_ITEM:
                return MC_EntityType.ITEM;
            case EXPERIENCE_ORB:
                return MC_EntityType.XP_ORB;
            case LEASH_HITCH:
                //fixme
                return MC_EntityType.HANGING;
            case PAINTING:
                //fixme
                return MC_EntityType.HANGING;
            case ARROW:
                return MC_EntityType.ARROW;
            case SNOWBALL:
                return MC_EntityType.SNOWBALL;
            case FIREBALL:
                return MC_EntityType.FIREBALL;
            case SMALL_FIREBALL:
                return MC_EntityType.SMALL_FIREBALL;
            case ENDER_PEARL:
                return MC_EntityType.THROWN_ENDERPEARL;
            case ENDER_SIGNAL:
                return MC_EntityType.EYE_OF_ENDER_SIGNAL;
            case THROWN_EXP_BOTTLE:
                return MC_EntityType.THROWN_EXP_BOTTLE;
            case ITEM_FRAME:
                // fixme
                return MC_EntityType.HANGING;
            case WITHER_SKULL:
                // fixme
                return MC_EntityType.UNSPECIFIED;
            case PRIMED_TNT:
                return MC_EntityType.PRIMED_TNT;
            case FALLING_BLOCK:
                return MC_EntityType.FALLING_SAND;
            case FIREWORK:
                return MC_EntityType.FIREWORK;
            case MINECART_COMMAND:
                // fixme
                return MC_EntityType.MINECART;
            case BOAT:
                return MC_EntityType.BOAT;
            case MINECART:
                return MC_EntityType.MINECART;
            case MINECART_CHEST:
                // fixme
                return MC_EntityType.MINECART;
            case MINECART_FURNACE:
                // fixme
                return MC_EntityType.MINECART;
            case MINECART_TNT:
                // fixme
                return MC_EntityType.MINECART;
            case MINECART_HOPPER:
                // fixme
                return MC_EntityType.MINECART;
            case MINECART_MOB_SPAWNER:
                // fixme
                return MC_EntityType.MINECART;
            case CREEPER:
                return MC_EntityType.CREEPER;
            case SKELETON:
                return MC_EntityType.SKELETON;
            case SPIDER:
                return MC_EntityType.SPIDER;
            case GIANT:
                return MC_EntityType.GIANT;
            case ZOMBIE:
                return MC_EntityType.ZOMBIE;
            case SLIME:
                return MC_EntityType.SLIME;
            case GHAST:
                return MC_EntityType.GHAST;
            case PIG_ZOMBIE:
                return MC_EntityType.PIG_ZOMBIE;
            case ENDERMAN:
                return MC_EntityType.ENDERMAN;
            case CAVE_SPIDER:
                return MC_EntityType.CAVE_SPIDER;
            case SILVERFISH:
                return MC_EntityType.SILVERFISH;
            case BLAZE:
                return MC_EntityType.BLAZE;
            case MAGMA_CUBE:
                return MC_EntityType.LAVA_SLIME;
            case ENDER_DRAGON:
                return MC_EntityType.ENDERDRAGON;
            case WITHER:
                return MC_EntityType.WITHERBOSS;
            case BAT:
                return MC_EntityType.BAT;
            case WITCH:
                return MC_EntityType.WITCH;
            case PIG:
                return MC_EntityType.PIG;
            case SHEEP:
                return MC_EntityType.SHEEP;
            case COW:
                return MC_EntityType.COW;
            case CHICKEN:
                return MC_EntityType.CHICKEN;
            case SQUID:
                return MC_EntityType.SQUID;
            case WOLF:
                return MC_EntityType.WOLF;
            case MUSHROOM_COW:
                return MC_EntityType.MUSHROOM_COW;
            case SNOWMAN:
                return MC_EntityType.SNOWMAN;
            case OCELOT:
                return MC_EntityType.OCELOT;
            case IRON_GOLEM:
                return MC_EntityType.VILLAGER_GOLEM;
            case HORSE:
                return MC_EntityType.HORSE;
            case VILLAGER:
                return MC_EntityType.VILLAGER;
            case ENDER_CRYSTAL:
                return MC_EntityType.ENDER_CRYSTAL;
            case SPLASH_POTION:
                return MC_EntityType.THROWN_POTION;
            case EGG:
                return MC_EntityType.THROWN_EGG;
            case FISHING_HOOK:
                return MC_EntityType.FISHING_HOOK;
            case LIGHTNING:
                // fixme
                return MC_EntityType.UNSPECIFIED;
            case WEATHER:
                // fixme
                return MC_EntityType.UNSPECIFIED;
            case PLAYER:
                return MC_EntityType.PLAYER;
            case COMPLEX_PART:
                // fixme
                return MC_EntityType.UNSPECIFIED;
            case UNKNOWN:
            default:
                return MC_EntityType.UNSPECIFIED;
        }
    }

    public static PotionEffectType getPotionEffectType(MC_PotionEffectType type) {
        switch (type) {
            default:
            case UNSPECIFIED:
                MyPlugin.fixme("potion effect type is " + type.name());
                return null;
            case INSTANT_HEALTH:
                return PotionEffectType.HEAL;
            case INSTANT_DAMAGE:
                return PotionEffectType.HARM;
            case SPEED:
                return PotionEffectType.SPEED;
            case SLOWNESS:
                return PotionEffectType.SLOW;
            case HASTE:
                return PotionEffectType.FAST_DIGGING;
            case MINING_FATIGUE:
                return PotionEffectType.SLOW_DIGGING;
            case STRENGTH:
                return PotionEffectType.INCREASE_DAMAGE;
            case JUMP_BOOST:
                return PotionEffectType.JUMP;
            case NAUSEA:
                return PotionEffectType.CONFUSION;
            case REGENERATION:
                return PotionEffectType.REGENERATION;
            case RESISTANCE:
                return PotionEffectType.DAMAGE_RESISTANCE;
            case FIRE_RESISTANCE:
                return PotionEffectType.FIRE_RESISTANCE;
            case WATER_BREATHING:
                return PotionEffectType.WATER_BREATHING;
            case INVISIBILITY:
                return PotionEffectType.INVISIBILITY;
            case BLINDNESS:
                return PotionEffectType.BLINDNESS;
            case NIGHT_VISION:
                return PotionEffectType.NIGHT_VISION;
            case HUNGER:
                return PotionEffectType.HUNGER;
            case WEAKNESS:
                return PotionEffectType.WEAKNESS;
            case POISON:
                return PotionEffectType.POISON;
            case WITHER:
                return PotionEffectType.WITHER;
            case HEALTH_BOOST:
                return PotionEffectType.HEALTH_BOOST;
            case ABSORPTION:
                return PotionEffectType.ABSORPTION;
            case SATURATION:
                return PotionEffectType.SATURATION;
        }
    }

    public static MC_PotionEffectType getPotionEffectType(PotionEffectType type) {
        if (type == PotionEffectType.ABSORPTION) return MC_PotionEffectType.ABSORPTION;
        if (type == PotionEffectType.BLINDNESS) return MC_PotionEffectType.BLINDNESS;
        if (type == PotionEffectType.CONFUSION) return MC_PotionEffectType.NAUSEA;
        if (type == PotionEffectType.DAMAGE_RESISTANCE) return MC_PotionEffectType.RESISTANCE;
        if (type == PotionEffectType.FAST_DIGGING) return MC_PotionEffectType.HASTE;
        if (type == PotionEffectType.FIRE_RESISTANCE) return MC_PotionEffectType.FIRE_RESISTANCE;
        if (type == PotionEffectType.HARM) return MC_PotionEffectType.INSTANT_DAMAGE;
        if (type == PotionEffectType.HEAL) return MC_PotionEffectType.INSTANT_HEALTH;
        if (type == PotionEffectType.HEALTH_BOOST) return MC_PotionEffectType.HEALTH_BOOST;
        if (type == PotionEffectType.HUNGER) return MC_PotionEffectType.HUNGER;
        if (type == PotionEffectType.INCREASE_DAMAGE) return MC_PotionEffectType.STRENGTH;
        if (type == PotionEffectType.DAMAGE_RESISTANCE) return MC_PotionEffectType.RESISTANCE;
        if (type == PotionEffectType.INVISIBILITY) return MC_PotionEffectType.INVISIBILITY;
        if (type == PotionEffectType.JUMP) return MC_PotionEffectType.JUMP_BOOST;
        if (type == PotionEffectType.NIGHT_VISION) return MC_PotionEffectType.NIGHT_VISION;
        if (type == PotionEffectType.POISON) return MC_PotionEffectType.POISON;
        if (type == PotionEffectType.REGENERATION) return MC_PotionEffectType.REGENERATION;
        if (type == PotionEffectType.SATURATION) return MC_PotionEffectType.SATURATION;
        if (type == PotionEffectType.SLOW) return MC_PotionEffectType.SLOWNESS;
        if (type == PotionEffectType.SLOW_DIGGING) return MC_PotionEffectType.MINING_FATIGUE;
        if (type == PotionEffectType.SPEED) return MC_PotionEffectType.SPEED;
        if (type == PotionEffectType.WATER_BREATHING) return MC_PotionEffectType.WATER_BREATHING;
        if (type == PotionEffectType.WEAKNESS) return MC_PotionEffectType.WEAKNESS;
        if (type == PotionEffectType.WITHER) return MC_PotionEffectType.WITHER;
        MyPlugin.fixme("unable to unwrap potionEffect " + type.getName());
        return null;
    }
}
