package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Snowman;

/**
 * Created by florian on 12.10.14.
 */
public class FakeSnowman extends FakeCreature implements Snowman{
    public FakeSnowman(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public boolean isDerp() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void setDerp(boolean b) {
        MyPlugin.fixme();
    }
}
