package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.EnderDragon;

/**
 * Created by florian on 12.10.14.
 */
public class FakeEnderdragon extends FakeComplexLivingEntity implements EnderDragon {
    public FakeEnderdragon(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public Phase getPhase() {
        MyPlugin.fixme();
        return Phase.CIRCLING;
    }

    @Override
    public void setPhase(Phase phase) {
        MyPlugin.fixme();
    }
}
