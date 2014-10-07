package PluginBukkitBridge;

import PluginReference.MC_DirectionNESWUD;
import PluginReference.MC_ItemStack;
import PluginReference.MC_Location;
import PluginReference.MC_MotionData;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * Created by florian on 07.10.14.
 */
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
        return new ItemStack(i.getId(), i.getCount(), (short)i.getDamage());
    }
    public static MC_ItemStack getItemStack(final ItemStack is){
        return MyPlugin.server.createItemStack(is.getTypeId(), is.getAmount(), is.getDurability());
    }
    public static BlockFace getFace(MC_DirectionNESWUD dir){
        return BlockFace.valueOf(dir.name());
    }

}
