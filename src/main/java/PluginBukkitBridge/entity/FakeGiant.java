package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.Giant;

/**
 * Created by florian on 12.10.14.
 */
public class FakeGiant extends FakeCreature implements Giant{
    public FakeGiant(MC_Entity argEnt) {
        super(argEnt);
    }
}
