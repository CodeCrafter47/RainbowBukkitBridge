package PluginBukkitBridge.block;

import PluginBukkitBridge.MyPlugin;
import org.bukkit.block.Dispenser;
import org.bukkit.inventory.Inventory;
import org.bukkit.projectiles.BlockProjectileSource;

/**
 * Created by florian on 14.10.14.
 */
public class FakeDispenser extends FakeBlockState implements Dispenser {
    public FakeDispenser(FakeBlock arg) {
        super(arg);
    }

    @Override
    public BlockProjectileSource getBlockProjectileSource() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean dispense() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public Inventory getInventory() {
        MyPlugin.fixme();
        return null;
    }
}
