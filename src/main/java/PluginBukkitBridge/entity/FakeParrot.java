package PluginBukkitBridge.entity;

import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Parrot;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;

public class FakeParrot extends FakeCreature implements Parrot {    
    public FakeParrot(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public boolean canBreed() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public int getAge() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public boolean getAgeLock() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isAdult() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void setAdult() {
        MyPlugin.fixme();
    }

    @Override
    public void setAge(int arg0) {
        MyPlugin.fixme(); 
    }

    @Override
    public void setAgeLock(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setBaby() {
        MyPlugin.fixme();
    }

    @Override
    public void setBreed(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public AnimalTamer getOwner() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean isTamed() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void setOwner(AnimalTamer arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setTamed(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public boolean isSitting() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void setSitting(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public Variant getVariant() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setVariant(Variant arg0) {
        MyPlugin.fixme();
    }
}
