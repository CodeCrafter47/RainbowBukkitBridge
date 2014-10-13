package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 * Created by florian on 12.10.14.
 */
public class FakeFirework extends FakeEntity implements Firework{
    public FakeFirework(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public FireworkMeta getFireworkMeta() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setFireworkMeta(FireworkMeta fireworkMeta) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public void detonate() {
        MyPlugin.fixme("stub method");
    }
}
