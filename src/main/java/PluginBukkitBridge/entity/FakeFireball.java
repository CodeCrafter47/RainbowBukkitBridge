package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.util.Vector;

/**
 * Created by florian on 12.10.14.
 */
public class FakeFireball extends FakeProjectile implements Fireball {
    public FakeFireball(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public void setDirection(Vector vector) {
        super.setVelocity(vector.normalize().multiply(super.getVelocity().length()));
    }

    @Override
    public Vector getDirection() {
        return getVelocity().normalize();
    }

    @Override
    public void setYield(float v) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public float getYield() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setIsIncendiary(boolean b) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public boolean isIncendiary() {
        MyPlugin.fixme("stub method");
        return false;
    }
}
