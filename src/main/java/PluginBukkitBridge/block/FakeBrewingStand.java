package PluginBukkitBridge.block;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.inventory.FakeBrewerInventory;
import org.bukkit.block.BrewingStand;
import org.bukkit.inventory.BrewerInventory;

/**
 * Created by florian on 14.10.14.
 */
public class FakeBrewingStand extends FakeContainerBlockState implements BrewingStand {
    public FakeBrewingStand(FakeBlock arg) {
        super(arg);
    }

    @Override
    public int getBrewingTime() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setBrewingTime(int i) {
        MyPlugin.fixme();

    }

    @Override
    public int getFuelLevel() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setFuelLevel(int i) {
        MyPlugin.fixme();
    }

    @Override
    public BrewerInventory getInventory() {
        return new FakeBrewerInventory(this, 64);
    }

    @Override
    public boolean isLocked() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public String getLock() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setLock(String s) {
        MyPlugin.fixme();
    }
}
