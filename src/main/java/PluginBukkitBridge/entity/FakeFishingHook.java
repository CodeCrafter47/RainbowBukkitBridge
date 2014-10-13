package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.FishHook;

/**
 * Created by florian on 12.10.14.
 */
public class FakeFishingHook extends FakeProjectile implements FishHook {
    public FakeFishingHook(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public double getBiteChance() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setBiteChance(double v) throws IllegalArgumentException {
        MyPlugin.fixme("stub method");
    }
}
