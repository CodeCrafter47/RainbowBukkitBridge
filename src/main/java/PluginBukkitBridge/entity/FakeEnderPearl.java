package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.EnderPearl;

/**
 * Created by florian on 12.10.14.
 */
public class FakeEnderPearl extends FakeProjectile implements EnderPearl {
    public FakeEnderPearl(MC_Entity argEnt) {
        super(argEnt);
    }
}
