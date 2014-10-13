package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.ComplexEntityPart;
import org.bukkit.entity.ComplexLivingEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by florian on 12.10.14.
 */
public class FakeComplexLivingEntity extends FakeLivingEntity implements ComplexLivingEntity {
    public FakeComplexLivingEntity(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public Set<ComplexEntityPart> getParts() {
        MyPlugin.fixme("stub method");
        return new HashSet<>();
    }
}
