package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.projectiles.ProjectileSource;

/**
 * Created by florian on 12.10.14.
 */
public class FakeProjectile extends FakeEntity implements Projectile {
    public FakeProjectile(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public LivingEntity _INVALID_getShooter() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public ProjectileSource getShooter() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void _INVALID_setShooter(LivingEntity livingEntity) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public void setShooter(ProjectileSource projectileSource) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public boolean doesBounce() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setBounce(boolean b) {
        MyPlugin.fixme("stub method");
    }
}
