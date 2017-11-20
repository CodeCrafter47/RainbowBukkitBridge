package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.Location;
import org.bukkit.entity.EnderCrystal;

/**
 * Created by florian on 12.10.14.
 */
public class FakeEnderCrystal extends FakeEntity implements EnderCrystal {
    public FakeEnderCrystal(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public boolean isShowingBottom() {
        MyPlugin.fixme();
        return true; // Minecraft Default
    }

    @Override
    public void setShowingBottom(boolean b) {
        MyPlugin.fixme();
    }

    @Override
    public Location getBeamTarget() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setBeamTarget(Location location) {
        MyPlugin.fixme();
    }
}
