package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.Location;
import org.bukkit.entity.EnderCrystal;

/**
 * Created by florian on 12.10.14.
 */
public class FakeEnderCrystal extends FakeEntity implements EnderCrystal {
    private boolean isShowingBottom;
    
    public FakeEnderCrystal(MC_Entity argEnt) {
        super(argEnt);
        this.isShowingBottom = false;
    }

    @Override
    public boolean isShowingBottom() {
        return this.isShowingBottom;
    }

    @Override
    public void setShowingBottom(boolean b) {
        this.isShowingBottom = b;
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
