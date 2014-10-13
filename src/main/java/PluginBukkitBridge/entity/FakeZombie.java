package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Zombie;

/**
 * Created by florian on 12.10.14.
 */
public class FakeZombie extends FakeCreature implements Zombie{
    public FakeZombie(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public boolean isBaby() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setBaby(boolean b) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public boolean isVillager() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setVillager(boolean b) {
        MyPlugin.fixme("stub method");
    }
}
