package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.ThrownExpBottle;

/**
 * Created by florian on 12.10.14.
 */
public class FakeThrownExpBottle extends FakeProjectile implements ThrownExpBottle{
    public FakeThrownExpBottle(MC_Entity argEnt) {
        super(argEnt);
    }
}
