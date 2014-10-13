package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.Snowman;

/**
 * Created by florian on 12.10.14.
 */
public class FakeSnowman extends FakeCreature implements Snowman{
    public FakeSnowman(MC_Entity argEnt) {
        super(argEnt);
    }
}
