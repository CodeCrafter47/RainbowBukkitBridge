package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
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
        MyPlugin.fixme();
    }

    @Override
    public LivingEntity getTarget() {
        MyPlugin.fixme();
        return null;
    }
}
