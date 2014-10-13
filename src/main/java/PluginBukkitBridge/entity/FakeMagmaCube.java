package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.MagmaCube;

/**
 * Created by florian on 12.10.14.
 */
public class FakeMagmaCube extends FakeSlime implements MagmaCube {
    public FakeMagmaCube(MC_Entity argEnt) {
        super(argEnt);
    }
}
