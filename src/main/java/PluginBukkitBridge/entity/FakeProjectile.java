package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.Util;
import PluginReference.MC_Entity;
import PluginReference.MC_Projectile;
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

    // 1.12 removed @Override
    public LivingEntity _INVALID_getShooter() {
        return (LivingEntity) Util.wrapEntity(((MC_Projectile)m_ent).getProjectileSource());
    }

    @Override
    public ProjectileSource getShooter() {
        return (ProjectileSource) Util.wrapEntity(((MC_Projectile)m_ent).getProjectileSource());
    }

    // 1.12 removed @Override
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
