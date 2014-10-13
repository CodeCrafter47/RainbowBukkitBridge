package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.Vehicle;

/**
 * Created by florian on 12.10.14.
 */
public class FakeVehicle extends FakeEntity implements Vehicle {
    public FakeVehicle(MC_Entity argEnt) {
        super(argEnt);
    }
}
