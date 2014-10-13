package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.Snowball;

/**
 * Created by florian on 12.10.14.
 */
public class FakeSnowball extends FakeProjectile implements Snowball {
    public FakeSnowball(MC_Entity argEnt) {
        super(argEnt);
    }
}
