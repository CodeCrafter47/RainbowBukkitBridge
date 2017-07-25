package PluginBukkitBridge.entity;

import org.bukkit.entity.Ageable;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_EntityAgeable;

/**
 * Created by florian on 12.10.14.
 */
public class FakeAgeable extends FakeCreature implements Ageable {

    MC_EntityAgeable ageable;

    public FakeAgeable(MC_EntityAgeable ageable) {
        super(ageable);
        this.ageable = ageable;
    }

    @Override
    public int getAge() {
        return ageable.getAge();
    }

    @Override
    public void setAge(int i) {
        ageable.setAge(i);
    }

    @Override
    public void setAgeLock(boolean b) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public boolean getAgeLock() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setBaby() {
        MyPlugin.fixme("stub method");
    }

    @Override
    public void setAdult() {
        MyPlugin.fixme("stub method");
    }

    @Override
    public boolean isAdult() {
        return getAge()>=0;
    }

    @Override
    public boolean canBreed() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setBreed(boolean b) {
        MyPlugin.fixme("stub method");
    }
}
