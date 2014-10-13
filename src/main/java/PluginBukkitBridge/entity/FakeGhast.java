package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.Ghast;

/**
 * Created by florian on 12.10.14.
 */
public class FakeGhast extends FakeLivingEntity implements Ghast{
    public FakeGhast(MC_Entity argEnt) {
        super(argEnt);
    }
}
