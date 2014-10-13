package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;

/**
 * Created by florian on 12.10.14.
 */
public class FakeTNTPrimed extends FakeEntity implements TNTPrimed {
    public FakeTNTPrimed(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public void setFuseTicks(int i) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public int getFuseTicks() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public Entity getSource() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setYield(float v) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public float getYield() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setIsIncendiary(boolean b) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public boolean isIncendiary() {
        MyPlugin.fixme("stub method");
        return false;
    }
}
