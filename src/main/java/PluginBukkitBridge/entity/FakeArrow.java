package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Arrow;

/**
 * Created by florian on 12.10.14.
 */
public class FakeArrow extends FakeProjectile implements Arrow {
    public FakeArrow(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public int getKnockbackStrength() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setKnockbackStrength(int i) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public boolean isCritical() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setCritical(boolean b) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public Arrow.Spigot spigot() {
        return new Arrow.Spigot(){
            @Override
            public double getDamage() {
                MyPlugin.fixme();
                return 0;
            }

            @Override
            public void setDamage(double damage) {
                MyPlugin.fixme();
            }

            @Override
            public boolean isInvulnerable() {
                MyPlugin.fixme();
                return false;
            }
        };
    }
}
