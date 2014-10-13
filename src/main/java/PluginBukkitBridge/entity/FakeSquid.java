package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.Squid;

/**
 * Created by florian on 12.10.14.
 */
public class FakeSquid extends FakeCreature implements Squid {
    public FakeSquid(MC_Entity argEnt) {
        super(argEnt);
    }
}
