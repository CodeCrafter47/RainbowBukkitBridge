package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_EntityAgeable;
import org.bukkit.entity.Pig;

/**
 * Created by florian on 12.10.14.
 */
public class FakePig extends FakeAnimal implements Pig {
    public FakePig(MC_EntityAgeable ageable) {
        super(ageable);
    }

    @Override
    public boolean hasSaddle() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setSaddle(boolean b) {
        MyPlugin.fixme("stub method");
    }
}
