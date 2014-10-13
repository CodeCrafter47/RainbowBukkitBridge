package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.ReflectionUtil;
import PluginBukkitBridge.Util;
import PluginReference.MC_Entity;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;

/**
 * Created by florian on 12.10.14.
 */
public class FakeCreature extends FakeLivingEntity implements Creature {
    public FakeCreature(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public void setTarget(LivingEntity livingEntity) {
        try {
            ReflectionUtil.setTarget(m_ent, ((FakeEntity)livingEntity).m_ent);
        } catch (Exception e) {
            MyPlugin.fixme("execution failed");
        }
    }

    @Override
    public LivingEntity getTarget() {
        try {
            return (LivingEntity) Util.wrapEntity(ReflectionUtil.getTarget(m_ent));
        } catch (Exception ex){
            MyPlugin.fixme("execution failed");
            return null;
        }
    }
}
