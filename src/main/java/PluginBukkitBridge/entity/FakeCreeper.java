package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Creeper;

/**
 * Created by florian on 12.10.14.
 */
public class FakeCreeper extends FakeCreature implements Creeper {
    private boolean isPowered;
    
    public FakeCreeper(MC_Entity argEnt) {
        super(argEnt);
        this.isPowered = false;
    }

    @Override
    public boolean isPowered() {
        return isPowered;
    }

    @Override
    public void setPowered(boolean b) {
        this.isPowered = b;
    }
}
