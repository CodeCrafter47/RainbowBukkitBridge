package PluginBukkitBridge.block;

import PluginBukkitBridge.MyPlugin;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.FurnaceInventory;

/**
 * Created by florian on 14.10.14.
 */
public class FakeFurnace extends FakeBlockState implements Furnace {
    public FakeFurnace(FakeBlock arg) {
        super(arg);
    }

    @Override
    public short getBurnTime() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setBurnTime(short i) {
        MyPlugin.fixme();
    }

    @Override
    public short getCookTime() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setCookTime(short i) {
        MyPlugin.fixme();
    }

    @Override
    public FurnaceInventory getInventory() {
        MyPlugin.fixme();
        return null;
    }
}
