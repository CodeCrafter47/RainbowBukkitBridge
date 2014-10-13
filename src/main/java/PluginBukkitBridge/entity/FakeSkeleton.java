package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Skeleton;

/**
 * Created by florian on 12.10.14.
 */
public class FakeSkeleton extends FakeCreature implements Skeleton {
    public FakeSkeleton(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public SkeletonType getSkeletonType() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setSkeletonType(SkeletonType skeletonType) {
        MyPlugin.fixme("stub method");
    }
}
