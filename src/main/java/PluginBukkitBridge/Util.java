package PluginBukkitBridge;

import PluginBukkitBridge.entity.*;
import PluginReference.*;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Util {

    public static MC_Location getLocation(Location loc){
        return new MC_Location(loc.getX(), loc.getY(), loc.getZ(), loc.getWorld().getEnvironment().getId(), loc.getYaw(), loc.getPitch());
    }
    public static Location getLocation(MC_Location loc){
        return new Location(new FakeWorld(MyPlugin.server.getWorld(loc.dimension)), loc.x, loc.y, loc.z, loc.yaw, loc.pitch);
    }
    public static Vector getDirection(MC_MotionData md){
        return new Vector(md.xMotion, md.yMotion, md.zMotion);
    }
    public static MC_MotionData getMotionData(Vector v, MC_MotionData md){
        md.xMotion = v.getX();
        md.yMotion = v.getY();
        md.zMotion = v.getZ();
        return md;
    }
    public static ItemStack getItemStack(MC_ItemStack i){
        if(i == null)return null;
        if(i.getId() == 0 || i.getCount() == 0)return null;
        ItemStack is = new ItemStack(i.getId(), i.getCount(), (short)i.getDamage());
        is.setItemMeta(new FakeItemMeta(i));
        return is;
    }
    public static MC_ItemStack getItemStack(final ItemStack is){
        if(is == null){
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
        MC_ItemStack is2 = ((FakeItemMeta)is.getItemMeta()).is;
        is2.setCount(is.getAmount());
        is2.setDamage(is.getDurability());
        return is2;
    }

    public static BlockFace getFace(MC_DirectionNESWUD dir){
        return BlockFace.valueOf(dir.name());
    }

    public static MC_Enchantment wrapEnchantment(Enchantment enchantment, int level){
        MC_EnchantmentType type = wrapEnchantmentType(enchantment.getId());
        if(type == null){
            MyPlugin.logger.warning("Unknown Enchantment ID: " + enchantment.getId());
            return null;
        }
        return new MC_Enchantment(type, level);
    }

    public static MC_EnchantmentType wrapEnchantmentType(int id){
        switch (id){
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

    public static Enchantment wrapEnchantmentType(MC_EnchantmentType enchantment){
        switch (enchantment){
            case PROTECTION:
                return Enchantment.PROTECTION_ENVIRONMENTAL;
            case FIRE_PROTECTION:
                return Enchantment.PROTECTION_FIRE;
            case FEATHER_FALLING:
                return Enchantment.PROTECTION_FALL;
            case BLAST_PROTECTION:
                return Enchantment.PROTECTION_EXPLOSIONS;
            case PROJECTILE_PROTECTION:
                return Enchantment.PROTECTION_PROJECTILE;
            case RESPIRATION:
                return Enchantment.OXYGEN;
            case AQUA_AFFINITY:
                return Enchantment.WATER_WORKER;
            case THORNS:
                return Enchantment.THORNS;
            case DEPTH_STRIDER:
                // fixme
                return null;
            case SHARPNESS:
                return Enchantment.DAMAGE_ALL;
            case SMITE:
                return Enchantment.DAMAGE_UNDEAD;
            case BANE_OF_ARTHROPODS:
                return Enchantment.DAMAGE_ARTHROPODS;
            case KNOCKBACK:
                return Enchantment.KNOCKBACK;
            case FIRE_ASPECT:
                return Enchantment.FIRE_ASPECT;
            case LOOTING:
                return Enchantment.LOOT_BONUS_MOBS;
            case EFFICIENCY:
                return Enchantment.DIG_SPEED;
            case SILK_TOUCH:
                return Enchantment.SILK_TOUCH;
            case UNBREAKING:
                return Enchantment.DURABILITY;
            case FORTUNE:
                return Enchantment.LOOT_BONUS_BLOCKS;
            case POWER:
                return Enchantment.ARROW_DAMAGE;
            case PUNCH:
                return Enchantment.ARROW_KNOCKBACK;
            case FLAME:
                return Enchantment.ARROW_FIRE;
            case INFINITY:
                return Enchantment.ARROW_INFINITE;
            case LUCK_OF_THE_SEA:
                return Enchantment.LUCK;
            case LURE:
                return Enchantment.LURE;
        }
        return null;
    }

    public static Entity wrapEntity(MC_Entity entity)
    {
        try {
            switch (entity.getType()){
                case UNSPECIFIED:
                    return new FakeEntity(entity);
                case PLAYER:
                    return PlayerManager.getPlayer((MC_Player)entity);
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
                    return new FakeEnderman(entity);
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
                    return new FakePigZombie(entity);
                case SILVERFISH:
                    return new FakeSilverfish(entity);
                case SKELETON:
                    return new FakeSkeleton(entity);
                case SLIME:
                    return new FakeSlime(entity);
                case SPIDER:
                    return new FakeSpider(entity);
                case WITCH:
                    return new FakeWitch(entity);
                case ZOMBIE:
                    return new FakeZombie(entity);
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
        }
        MyPlugin.fixme("unable to create specific wrapper for " + entity.getType());
        return new FakeEntity(entity);
    }

    public static Horse.Variant wrapHorseType(MC_HorseType type){
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

    public static MC_HorseType wrapHorseType(Horse.Variant type){
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

    public static MC_HorseVariant wrapHorseVariant(Horse.Style type){
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

    public static Horse.Style wrapHorseVariant(MC_HorseVariant type){
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
}
