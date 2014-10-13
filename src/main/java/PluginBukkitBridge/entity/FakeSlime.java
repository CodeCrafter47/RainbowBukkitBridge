package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Slime;

/**
 * Created by florian on 12.10.14.
 */
public class FakeSlime extends FakeLivingEntity implements Slime {
    public FakeSlime(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public int getSize() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setSize(int i) {
        MyPlugin.fixme("stub method");
    }
}
