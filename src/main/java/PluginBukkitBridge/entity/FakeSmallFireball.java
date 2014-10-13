package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.SmallFireball;

/**
 * Created by florian on 12.10.14.
 */
public class FakeSmallFireball extends FakeFireball implements SmallFireball{
    public FakeSmallFireball(MC_Entity argEnt) {
        super(argEnt);
    }
}
