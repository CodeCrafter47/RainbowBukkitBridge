package PluginBukkitBridge.entity;

import org.bukkit.entity.Creeper;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;

/**
 * Created by florian on 12.10.14.
 */
public class FakeCreeper extends FakeCreature implements Creeper {
    public FakeCreeper(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public boolean isPowered() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void setPowered(boolean b) {
        MyPlugin.fixme();
    }

    @Override
    public int getExplosionRadius() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int getMaxFuseTicks() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setExplosionRadius(int arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setMaxFuseTicks(int arg0) {
        MyPlugin.fixme();
    }
}
