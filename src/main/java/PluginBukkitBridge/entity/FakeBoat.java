package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Boat;

/**
 * Created by florian on 12.10.14.
 */
public class FakeBoat extends FakeVehicle implements Boat {
    public FakeBoat(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public double getMaxSpeed() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setMaxSpeed(double v) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public double getOccupiedDeceleration() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setOccupiedDeceleration(double v) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public double getUnoccupiedDeceleration() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setUnoccupiedDeceleration(double v) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public boolean getWorkOnLand() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setWorkOnLand(boolean b) {
        MyPlugin.fixme("stub method");
    }
}
