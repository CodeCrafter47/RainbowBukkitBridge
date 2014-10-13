package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Creeper;

/**
 * Created by florian on 12.10.14.
 */
public class FakeCreeper extends FakeCreature implements Creeper {
    public FakeCreeper(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public boolean isPowered() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setPowered(boolean b) {
        MyPlugin.fixme("stub method");
    }
}
