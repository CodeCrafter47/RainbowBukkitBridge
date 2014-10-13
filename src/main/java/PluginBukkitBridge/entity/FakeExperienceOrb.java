package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.ExperienceOrb;

/**
 * Created by florian on 12.10.14.
 */
public class FakeExperienceOrb extends FakeEntity implements ExperienceOrb {
    public FakeExperienceOrb(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public int getExperience() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setExperience(int i) {
        MyPlugin.fixme("stub method");
    }
}
