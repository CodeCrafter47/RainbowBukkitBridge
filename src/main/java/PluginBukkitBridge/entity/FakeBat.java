package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Bat;

/**
 * Created by florian on 12.10.14.
 */
public class FakeBat extends FakeAmbient implements Bat {
    public FakeBat(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public boolean isAwake() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setAwake(boolean b) {
        MyPlugin.fixme("stub method");
    }
}
