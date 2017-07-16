package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;

/**
 * Created by florian on 12.10.14.
 */
public class FakeMinecart extends FakeVehicle implements Minecart {
    public FakeMinecart(MC_Entity argEnt) {
        super(argEnt);
    }

    // 1.12 removed @Override
    public void _INVALID_setDamage(int i) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public void setDamage(double v) {
        MyPlugin.fixme("stub method");
    }

    // 1.12 removed @Override
    public int _INVALID_getDamage() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public double getDamage() {
        MyPlugin.fixme("stub method");
        return 0;
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
    public boolean isSlowWhenEmpty() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setSlowWhenEmpty(boolean b) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public Vector getFlyingVelocityMod() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setFlyingVelocityMod(Vector vector) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public Vector getDerailedVelocityMod() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setDerailedVelocityMod(Vector vector) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public void setDisplayBlock(MaterialData materialData) {
        MyPlugin.fixme();
    }

    @Override
    public MaterialData getDisplayBlock() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setDisplayBlockOffset(int i) {
        MyPlugin.fixme();
    }

    @Override
    public int getDisplayBlockOffset() {
        MyPlugin.fixme();
        return 0;
    }
}
