package PluginBukkitBridge;

import PluginReference.*;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
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
        // fixme enchantments, lore, etc
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

}
