package PluginBukkitBridge.block;

import PluginBukkitBridge.MyPlugin;
import org.bukkit.block.BrewingStand;
import org.bukkit.inventory.BrewerInventory;

/**
 * Created by florian on 14.10.14.
 */
public class FakeBrewingStand extends FakeBlockState implements BrewingStand {
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
    public BrewerInventory getInventory() {
        MyPlugin.fixme();
        return null;
    }
}
