package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.LargeFireball;

/**
 * Created by florian on 12.10.14.
 */
public class FakeLargeFireball extends FakeFireball implements LargeFireball{
    public FakeLargeFireball(MC_Entity argEnt) {
        super(argEnt);
    }
}
