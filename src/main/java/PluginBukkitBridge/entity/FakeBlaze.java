package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.Blaze;

/**
 * Created by florian on 12.10.14.
 */
public class FakeBlaze extends FakeCreature implements Blaze{
    public FakeBlaze(MC_Entity argEnt) {
        super(argEnt);
    }
}
