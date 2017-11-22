package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;

import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Llama;
import org.bukkit.inventory.LlamaInventory;

import PluginBukkitBridge.MyPlugin;

@SuppressWarnings("all")
public class FakeLlama extends FakeCreature implements Llama {
    private MC_Entity e;
    private boolean isCarryingChest = false;
    
    public FakeLlama(MC_Entity argEnt) {
        super(argEnt);
        this.e = argEnt;
    }

    @Override
    public boolean isCarryingChest() {
        return isCarryingChest;
    }

    @Override
    public void setCarryingChest(boolean arg) {
        this.isCarryingChest = arg;  
    }

    @Override
    public int getDomestication() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public double getJumpStrength() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int getMaxDomestication() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public Variant getVariant() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setDomestication(int arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setJumpStrength(double arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setMaxDomestication(int arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setVariant(Variant arg0) {
        MyPlugin.fixme();
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
    public Color getColor() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public LlamaInventory getInventory() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public int getStrength() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setColor(Color arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setStrength(int arg0) {
        MyPlugin.fixme();
    }
}
