package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.Wither;

/**
 * Created by florian on 12.10.14.
 */
public class FakeWither extends FakeCreature implements Wither {
    public FakeWither(MC_Entity argEnt) {
        super(argEnt);
    }
}
