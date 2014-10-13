package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.Egg;

/**
 * Created by florian on 12.10.14.
 */
public class FakeEgg extends FakeProjectile implements Egg{
    public FakeEgg(MC_Entity argEnt) {
        super(argEnt);
    }
}
