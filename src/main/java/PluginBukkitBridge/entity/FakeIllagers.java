package PluginBukkitBridge.entity;

import org.bukkit.entity.Illager;

import PluginReference.MC_Entity;

public class FakeIllagers extends FakeCreature implements Illager {

    public FakeIllagers(MC_Entity argEnt) {
        super(argEnt);
    }

}
