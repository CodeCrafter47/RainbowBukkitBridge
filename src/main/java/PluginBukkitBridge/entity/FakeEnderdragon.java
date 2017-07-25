package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.EnderDragon;

/**
 * Created by florian on 12.10.14.
 */
public class FakeEnderdragon extends FakeComplexLivingEntity implements EnderDragon {
    private Phase phase;
    
    public FakeEnderdragon(MC_Entity argEnt) {
        super(argEnt);
        this.phase = Phase.CIRCLING;
    }

    @Override
    public Phase getPhase() {;
        return this.phase;
    }

    @Override
    public void setPhase(Phase phase) {
        this.phase = phase;
    }
}
