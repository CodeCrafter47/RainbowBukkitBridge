package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.EnderSignal;

/**
 * Created by florian on 12.10.14.
 */
public class FakeEnderSignal extends FakeEntity implements EnderSignal {
    public FakeEnderSignal(MC_Entity argEnt) {
        super(argEnt);
    }
}
