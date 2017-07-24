package PluginBukkitBridge.entity;

import org.bukkit.entity.Vindicator;

import PluginReference.MC_Entity;

public class FakeVindicator extends FakeCreature implements Vindicator {

    public FakeVindicator(MC_Entity argEnt) {
        super(argEnt);
    }

}
