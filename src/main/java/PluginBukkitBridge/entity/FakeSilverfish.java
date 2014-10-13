package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.Silverfish;

/**
 * Created by florian on 12.10.14.
 */
public class FakeSilverfish extends FakeCreature implements Silverfish{
    public FakeSilverfish(MC_Entity argEnt) {
        super(argEnt);
    }
}
