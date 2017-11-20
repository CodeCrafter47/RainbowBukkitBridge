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
}
