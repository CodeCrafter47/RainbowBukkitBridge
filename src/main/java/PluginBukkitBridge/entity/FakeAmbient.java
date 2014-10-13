package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.Ambient;

/**
 * Created by florian on 12.10.14.
 */
public class FakeAmbient extends FakeLivingEntity implements Ambient {
    public FakeAmbient(MC_Entity argEnt) {
        super(argEnt);
    }
}
