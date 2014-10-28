package PluginBukkitBridge.block;

import PluginBukkitBridge.ReflectionUtil;
import PluginBukkitBridge.Util;
import PluginBukkitBridge.inventory.FakeFurnaceInventory;
import PluginReference.MC_Container;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.FurnaceInventory;

/**
 * Created by florian on 14.10.14.
 */
public class FakeFurnace extends FakeBlockState implements Furnace {

    short burnTime;
    short cookTime;

    public FakeFurnace(FakeBlock arg) {
        super(arg);
        MC_Container furnace = arg.world.getContainerAt(Util.getLocation(arg.getLocation()));
        burnTime = ReflectionUtil.readFurnaceBurnTime(furnace);
        cookTime = ReflectionUtil.readFurnaceCookTime(furnace);
    }

    @Override
    public short getBurnTime() {
        return burnTime;
    }

    @Override
    public void setBurnTime(short i) {
        burnTime = i;
    }

    @Override
    public short getCookTime() {
        return cookTime;
    }

    @Override
    public void setCookTime(short i) {
        cookTime = i;
    }

    @Override
    public FurnaceInventory getInventory() {
        return new FakeFurnaceInventory();
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        if(super.update(force, applyPhysics)){
            FakeBlock arg = (FakeBlock) getBlock();
            MC_Container furnace = arg.world.getContainerAt(Util.getLocation(arg.getLocation()));
            ReflectionUtil.writeFurnaceBurnTime(furnace, burnTime);
            ReflectionUtil.writeFurnaceCookTime(furnace, cookTime);
            return true;
        }
        return false;
    }
}
