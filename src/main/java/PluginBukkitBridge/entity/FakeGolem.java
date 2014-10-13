package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.IronGolem;

/**
 * Created by florian on 12.10.14.
 */
public class FakeGolem extends FakeCreature implements IronGolem{
    public FakeGolem(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public boolean isPlayerCreated() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setPlayerCreated(boolean b) {
        MyPlugin.fixme("stub method");
    }
}
