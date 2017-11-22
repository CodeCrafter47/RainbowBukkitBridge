package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.Util;
import PluginReference.MC_Skeleton;
import org.bukkit.entity.Skeleton;

/**
 * Created by florian on 12.10.14.
 */
public class FakeSkeleton extends FakeCreature implements Skeleton {
    private MC_Skeleton skeleton;

    public FakeSkeleton(MC_Skeleton argEnt) {
        super(argEnt);
        skeleton = argEnt;
    }

    @Override
    public SkeletonType getSkeletonType() {
        MyPlugin.fixme();
        return SkeletonType.NORMAL;
    }

    @Override
    public void setSkeletonType(SkeletonType skeletonType) {
        MyPlugin.fixme();
    }
}
