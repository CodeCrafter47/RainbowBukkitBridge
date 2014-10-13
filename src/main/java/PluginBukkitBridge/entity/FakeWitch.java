package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.Witch;

/**
 * Created by florian on 12.10.14.
 */
public class FakeWitch extends FakeCreature implements Witch {
    public FakeWitch(MC_Entity argEnt) {
        super(argEnt);
    }
}
