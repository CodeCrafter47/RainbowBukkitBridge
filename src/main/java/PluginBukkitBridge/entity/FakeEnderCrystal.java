package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.EnderCrystal;

/**
 * Created by florian on 12.10.14.
 */
public class FakeEnderCrystal extends FakeEntity implements EnderCrystal {
    public FakeEnderCrystal(MC_Entity argEnt) {
        super(argEnt);
    }
}
