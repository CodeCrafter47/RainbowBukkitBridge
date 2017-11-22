package PluginBukkitBridge.entity;

import org.bukkit.entity.EnderDragon;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;

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
        return null;
    }

    @Override
    public void setPhase(Phase phase) {
        MyPlugin.fixme();
    }
}
