package PluginBukkitBridge;

import PluginBukkitBridge.block.*;
import PluginBukkitBridge.entity.*;
import PluginBukkitBridge.item.*;
import PluginReference.*;
import lombok.SneakyThrows;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.jnbt.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static PluginReference.MC_WorldBiomeType.BEACH;
import static PluginReference.MC_WorldBiomeType.COLD_TAIGA;
import static PluginReference.MC_WorldBiomeType.COLD_TAIGA_HILLS;
import static PluginReference.MC_WorldBiomeType.EXTREME_HILLS_PLUS;
import static PluginReference.MC_WorldBiomeType.FLOWER_FOREST;
import static PluginReference.MC_WorldBiomeType.ICE_PLAINS;
import static PluginReference.MC_WorldBiomeType.ICE_PLAINS_SPIKES;
import static PluginReference.MC_WorldBiomeType.MEGA_SPRUCE_TAIGA;
import static PluginReference.MC_WorldBiomeType.MEGA_TAIGA;
import static PluginReference.MC_WorldBiomeType.MEGA_TAIGA_HILLS;
import static PluginReference.MC_WorldBiomeType.REDWOOD_TAIGA_HILLS_M;
import static PluginReference.MC_WorldBiomeType.TAIGA_M;

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

    @SneakyThrows
    public static ItemStack getItemStack(MC_ItemStack i) {
        if (i == null || i.getId() == 0 || i.getCount() == 0) return null;
        ByteArrayInputStream is1 = new ByteArrayInputStream(i.serialize());
        NBTInputStream in = new NBTInputStream(is1);
        Map<String,Tag> tags = new HashMap<>();
        while(is1.available() > 1) {
            Tag tag = in.readTag();
            tags.put(tag.getName(), tag);
        }
        CompoundTag tag = new CompoundTag("item", tags);
        ItemStack is = new ItemStack(i.getId(), i.getCount(), (short) i.getDamage());
        is.setItemMeta(getItemMeta(is.getType(), (CompoundTag) tag.getValue().get("tag")));
        return is;
    }

    public static ItemMeta getItemMeta(Material material, CompoundTag tag) {
        switch (material) {
            case AIR:
                return null;
            case WRITTEN_BOOK:
            case BOOK_AND_QUILL:
                return new FakeBookMeta(tag);
            case SKULL_ITEM:
                return new FakeSkullMeta(tag);
            case LEATHER_HELMET:
            case LEATHER_CHESTPLATE:
            case LEATHER_LEGGINGS:
            case LEATHER_BOOTS:
                return new FakeLeatherArmorMeta(tag);
            case POTION:
                return new FakePotionMeta(tag);
            case MAP:
                return new FakeMapMeta(tag);
            case FIREWORK:
                return new FakeFireworkMeta(tag);
            case FIREWORK_CHARGE:
                return new FakeFireworkEffectMeta(tag);
            case ENCHANTED_BOOK:
                return new FakeEnchantedBookMeta(tag);
            default:
                return new FakeItemMeta(tag);
        }
    }

    @SneakyThrows
    public static MC_ItemStack getItemStack(final ItemStack is) {
        if (is == null || is.getType() == Material.AIR) {
            return null;
        }
        MC_ItemStack is2 = MyPlugin.server.createItemStack(is.getTypeId(), is.getAmount(), is.getDurability());
        ByteArrayInputStream is1 = new ByteArrayInputStream(is2.serialize());
        NBTInputStream in = new NBTInputStream(is1);
        Map<String,Tag> tags = new HashMap<>();
        while(is1.available() > 1) {
            Tag tag = in.readTag();
            tags.put(tag.getName(), tag);
        }
        if(((FakeItemMeta)is.getItemMeta()).getTag() != null)
            tags.put("tag", ((FakeItemMeta)is.getItemMeta()).getTag());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        NBTOutputStream out = new NBTOutputStream(os);
        for(Tag tag: tags.values())out.writeTag(tag);
        out.close();
        os.write(NBTConstants.TYPE_END);
        return MyPlugin.server.createItemStack(os.toByteArray());
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
                    return new FakeRabbit((MC_EntityAgeable) entity);
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
                    return new FakeEndermite(entity);
                case GHAST:
                    return new FakeGhast(entity);
                case GIANT:
                    return new FakeGiant(entity);
                case GUARDIAN:
                    return new FakeGuardian(entity);
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
                    return new FakeItem((MC_ItemEntity) entity);
                case MINECART:
                    return new FakeMinecart(entity);
                case BOAT:
                    return new FakeBoat(entity);
                case PRIMED_TNT:
                    return new FakeTNTPrimed(entity);
                case FALLING_SAND:
                    return new FakeFallingBlock(entity);
                case ITEM_FRAME:
                case LEASH_KNOT:
                case PAINTING:
                    return new FakeHanging(entity);
                case ARMOR_STAND:
                    return new FakeArmorStand((MC_ArmorStand) entity);
                case XP_ORB:
                    return new FakeExperienceOrb(entity);
                case ENDER_CRYSTAL:
                    return new FakeEnderCrystal(entity);
            default:
                break;
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        MyPlugin.fixme("unable to create specific wrapper for " + entity.getType());
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
                return MC_EntityType.LEASH_KNOT;
            case PAINTING:
                return MC_EntityType.PAINTING;
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
                return MC_EntityType.ITEM_FRAME;
            case WITHER_SKULL:
                return MC_EntityType.WITHER_SKULL;
            case PRIMED_TNT:
                return MC_EntityType.PRIMED_TNT;
            case FALLING_BLOCK:
                return MC_EntityType.FALLING_SAND;
            case FIREWORK:
                return MC_EntityType.FIREWORK;
			case ARMOR_STAND:
				return MC_EntityType.ARMOR_STAND;
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
			case ENDERMITE:
				return MC_EntityType.ENDERMITE;
			case GUARDIAN:
				return MC_EntityType.GUARDIAN;
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
			case RABBIT:
				return MC_EntityType.RABBIT;
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
                return MC_EntityType.LIGHTNING_BOLT;
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
        return PotionEffectType.getByName(type.name());
    }

    public static MC_PotionEffectType getPotionEffectType(PotionEffectType type) {
        if (type.getName().equals("ABSORPTION")) return MC_PotionEffectType.ABSORPTION;
        if (type.getName().equals("BLINDNESS")) return MC_PotionEffectType.BLINDNESS;
        if (type.getName().equals("NAUSEA")) return MC_PotionEffectType.NAUSEA;
        if (type.getName().equals("RESISTANCE")) return MC_PotionEffectType.RESISTANCE;
        if (type.getName().equals("HASTE")) return MC_PotionEffectType.HASTE;
        if (type.getName().equals("FIRE_RESISTANCE")) return MC_PotionEffectType.FIRE_RESISTANCE;
        if (type.getName().equals("INSTANT_DAMAGE")) return MC_PotionEffectType.INSTANT_DAMAGE;
        if (type.getName().equals("INSTANT_HEALTH")) return MC_PotionEffectType.INSTANT_HEALTH;
        if (type.getName().equals("HEALTH_BOOST")) return MC_PotionEffectType.HEALTH_BOOST;
        if (type.getName().equals("HUNGER")) return MC_PotionEffectType.HUNGER;
        if (type.getName().equals("STRENGTH")) return MC_PotionEffectType.STRENGTH;
        if (type.getName().equals("RESISTANCE")) return MC_PotionEffectType.RESISTANCE;
        if (type.getName().equals("INVISIBILITY")) return MC_PotionEffectType.INVISIBILITY;
        if (type.getName().equals("JUMP_BOOST")) return MC_PotionEffectType.JUMP_BOOST;
        if (type.getName().equals("NIGHT_VISION")) return MC_PotionEffectType.NIGHT_VISION;
        if (type.getName().equals("POISON")) return MC_PotionEffectType.POISON;
        if (type.getName().equals("REGENERATION")) return MC_PotionEffectType.REGENERATION;
        if (type.getName().equals("SATURATION")) return MC_PotionEffectType.SATURATION;
        if (type.getName().equals("SLOWNESS")) return MC_PotionEffectType.SLOWNESS;
        if (type.getName().equals("MINING_FATIGUE")) return MC_PotionEffectType.MINING_FATIGUE;
        if (type.getName().equals("SPEED")) return MC_PotionEffectType.SPEED;
        if (type.getName().equals("WATER_BREATHING")) return MC_PotionEffectType.WATER_BREATHING;
        if (type.getName().equals("WEAKNESS")) return MC_PotionEffectType.WEAKNESS;
        if (type.getName().equals("WITHER")) return MC_PotionEffectType.WITHER;
        MyPlugin.fixme("unable to unwrap potionEffect " + type.getName());
        return null;
    }

	public static MC_WorldBiomeType wrapBiome(Biome arg2) {
		switch (arg2) {
			case SWAMPLAND:
				return MC_WorldBiomeType.SWAMPLAND;
			case FOREST:
				return MC_WorldBiomeType.FOREST;
			case TAIGA:
				return MC_WorldBiomeType.TAIGA;
			case DESERT:
				return MC_WorldBiomeType.DESERT;
			case PLAINS:
				return MC_WorldBiomeType.PLAINS;
			case HELL:
				return MC_WorldBiomeType.HELL;
			case SKY:
				return MC_WorldBiomeType.THE_END;
			case OCEAN:
				return MC_WorldBiomeType.OCEAN;
			case RIVER:
				return MC_WorldBiomeType.RIVER;
			case EXTREME_HILLS:
				return MC_WorldBiomeType.EXTREME_HILLS;
			case FROZEN_OCEAN:
				return MC_WorldBiomeType.FROZEN_OCEAN;
			case FROZEN_RIVER:
				return MC_WorldBiomeType.FROZEN_RIVER;
            case ICE_FLATS:
                break;
            case ICE_MOUNTAINS:
				return MC_WorldBiomeType.ICE_MOUNTAINS;
			case MUSHROOM_ISLAND:
				return MC_WorldBiomeType.MUSHROOM_ISLAND;
            case MUSHROOM_ISLAND_SHORE:
                break;
            case BEACHES:
                break;
            case DESERT_HILLS:
				return MC_WorldBiomeType.DESERT_HILLS;
			case FOREST_HILLS:
				return MC_WorldBiomeType.FOREST_HILLS;
			case TAIGA_HILLS:
				return MC_WorldBiomeType.TAIGA_HILLS;
            case SMALLER_EXTREME_HILLS:
                break;
            case JUNGLE:
				return MC_WorldBiomeType.JUNGLE;
			case JUNGLE_HILLS:
				return MC_WorldBiomeType.JUNGLE_HILLS;
			case JUNGLE_EDGE:
				return MC_WorldBiomeType.JUNGLE_EDGE;
			case DEEP_OCEAN:
				return MC_WorldBiomeType.DEEP_OCEAN;
			case STONE_BEACH:
				return MC_WorldBiomeType.STONE_BEACH;
			case COLD_BEACH:
				return MC_WorldBiomeType.COLD_BEACH;
			case BIRCH_FOREST:
				return MC_WorldBiomeType.BIRCH_FOREST;
			case BIRCH_FOREST_HILLS:
				return MC_WorldBiomeType.BIRCH_FOREST_HILLS;
			case ROOFED_FOREST:
				return MC_WorldBiomeType.ROOFED_FOREST;
            case TAIGA_COLD:
                return COLD_TAIGA;
            case TAIGA_COLD_HILLS:
                return COLD_TAIGA_HILLS;
        }
		return MC_WorldBiomeType.UNSPECIFIED;
	}

	public static Biome wrapBiome(MC_WorldBiomeType biomeType) {
		switch (biomeType) {
			case UNSPECIFIED:
				MyPlugin.fixme("Unknown biome " + biomeType);
				return null;
			case OCEAN:
				return Biome.OCEAN;
			case PLAINS:
				return Biome.PLAINS;
			case DESERT:
				return Biome.DESERT;
			case EXTREME_HILLS:
				return Biome.EXTREME_HILLS;
			case FOREST:
				return Biome.FOREST;
			case TAIGA:
				return Biome.TAIGA;
			case SWAMPLAND:
				return Biome.SWAMPLAND;
			case RIVER:
				return Biome.RIVER;
			case HELL:
				return Biome.HELL;
			case THE_END:
				return Biome.SKY;
			case FROZEN_OCEAN:
				return Biome.FROZEN_OCEAN;
			case FROZEN_RIVER:
				return Biome.FROZEN_RIVER;
			case ICE_MOUNTAINS:
				return Biome.ICE_MOUNTAINS;
			case MUSHROOM_ISLAND:
				return Biome.MUSHROOM_ISLAND;
			case DESERT_HILLS:
				return Biome.DESERT_HILLS;
			case FOREST_HILLS:
				return Biome.FOREST_HILLS;
			case TAIGA_HILLS:
				return Biome.TAIGA_HILLS;
			case EXTREME_HILLS_EDGE:
				return Biome.EXTREME_HILLS;
			case JUNGLE:
				return Biome.JUNGLE;
			case JUNGLE_HILLS:
				return Biome.JUNGLE_HILLS;
			case JUNGLE_EDGE:
				return Biome.JUNGLE_EDGE;
			case DEEP_OCEAN:
				return Biome.DEEP_OCEAN;
			case STONE_BEACH:
				return Biome.STONE_BEACH;
			case COLD_BEACH:
				return Biome.COLD_BEACH;
			case BIRCH_FOREST:
				return Biome.BIRCH_FOREST;
			case BIRCH_FOREST_HILLS:
				return Biome.BIRCH_FOREST_HILLS;
			case ROOFED_FOREST:
				return Biome.ROOFED_FOREST;
			case SAVANNA:
				return Biome.SAVANNA;
			case MESA:
				return Biome.MESA;
		}
		return null;
	}

    public static MC_AttributeType wrapAttributeType(Attribute attribute) {
        switch (attribute) {
            case GENERIC_MAX_HEALTH:
                return MC_AttributeType.MAX_HEALTH;
            case GENERIC_FOLLOW_RANGE:
                return MC_AttributeType.FOLLOW_RANGE;
            case GENERIC_KNOCKBACK_RESISTANCE:
                return MC_AttributeType.KNOCKBACK_RESISTANCE;
            case GENERIC_MOVEMENT_SPEED:
                return MC_AttributeType.MOVEMENT_SPEED;
            case GENERIC_ATTACK_DAMAGE:
                return MC_AttributeType.ATTACK_DAMAGE;
            case GENERIC_ATTACK_SPEED:
                return MC_AttributeType.PLAYER_ATTACK_SPEED;
            case GENERIC_ARMOR:
                return MC_AttributeType.ARMOR;
            case GENERIC_LUCK:
                return MC_AttributeType.PLAYER_LUCK;
            case HORSE_JUMP_STRENGTH:
                return MC_AttributeType.HORSE_JUMP_STRENGTH;
            case ZOMBIE_SPAWN_REINFORCEMENTS:
                return MC_AttributeType.ZOMBIE_REINFORCEMENT_CHANCE;
        }
        MyPlugin.logger.warning("Unknown attribute " + attribute);
        return null;
    }

    public static Attribute unwrapAttributeType(MC_AttributeType attribute) {
        switch (attribute) {
            case MAX_HEALTH:
                return Attribute.GENERIC_MAX_HEALTH;
            case FOLLOW_RANGE:
                return Attribute.GENERIC_FOLLOW_RANGE;
            case KNOCKBACK_RESISTANCE:
                return Attribute.GENERIC_KNOCKBACK_RESISTANCE;
            case MOVEMENT_SPEED:
                return Attribute.GENERIC_MOVEMENT_SPEED;
            case ATTACK_DAMAGE:
                return Attribute.GENERIC_ATTACK_DAMAGE;
            case ARMOR:
                return Attribute.GENERIC_ARMOR;
            case PLAYER_ATTACK_SPEED:
                return Attribute.GENERIC_ATTACK_SPEED;
            case PLAYER_LUCK:
                return Attribute.GENERIC_LUCK;
            case HORSE_JUMP_STRENGTH:
                return Attribute.HORSE_JUMP_STRENGTH;
            case ZOMBIE_REINFORCEMENT_CHANCE:
                return Attribute.ZOMBIE_SPAWN_REINFORCEMENTS;
        }
        MyPlugin.logger.warning("Unknown attribute " + attribute);
        return null;
    }

    public static AttributeModifier wrapAttributeModifier(MC_AttributeModifier modifier) {
        return new AttributeModifier(modifier.getUUID(), modifier.getName(), modifier.getValue(), wrapOperator(modifier.getOperator()));
    }

    public static MC_AttributeModifier wrapAttributeModifier(AttributeModifier modifier) {
        return MyPlugin.server.createAttributeModifier(modifier.getUniqueId(), modifier.getName(), wrapOperator(modifier.getOperation()), modifier.getAmount());
    }

    private static AttributeModifier.Operation wrapOperator(MC_AttributeModifier.Operator operator) {
        switch (operator) {
            case ADD_CONSTANT:
                return AttributeModifier.Operation.ADD_NUMBER;
            case ADD_SCALAR_BASE:
                return AttributeModifier.Operation.ADD_SCALAR;
            case ADD_SCALAR:
                return AttributeModifier.Operation.MULTIPLY_SCALAR_1;
        }
        MyPlugin.logger.warning("Unknown operator " + operator);
        return null;
    }

    private static MC_AttributeModifier.Operator wrapOperator(AttributeModifier.Operation operation) {
        switch (operation) {
            case ADD_NUMBER:
                return MC_AttributeModifier.Operator.ADD_CONSTANT;
            case ADD_SCALAR:
                return MC_AttributeModifier.Operator.ADD_SCALAR_BASE;
            case MULTIPLY_SCALAR_1:
                return MC_AttributeModifier.Operator.ADD_SCALAR;
        }
        MyPlugin.logger.warning("Unknown operation " + operation);
        return null;
    }
}
