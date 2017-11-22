package PluginBukkitBridge.entity;

import org.bukkit.entity.Vex;

import PluginReference.MC_Entity;

public class FakeVex extends FakeCreature implements Vex {
    public FakeVex(MC_Entity argEnt) {
        super(argEnt);
    }
}
