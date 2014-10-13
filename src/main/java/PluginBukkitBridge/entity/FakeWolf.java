package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_EntityAgeable;
import PluginReference.MC_Wolf;
import org.bukkit.DyeColor;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Wolf;

/**
 * Created by florian on 12.10.14.
 */
public class FakeWolf extends FakeAnimal implements Wolf{
    MC_Wolf wolf;

    public FakeWolf(MC_Wolf wolf) {
        super((MC_EntityAgeable) wolf);
        this.wolf = wolf;
    }

    @Override
    public boolean isAngry() {
        return wolf.getAngry();
    }

    @Override
    public void setAngry(boolean b) {
        wolf.setAngry(b);
    }

    @Override
    public boolean isSitting() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setSitting(boolean b) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public DyeColor getCollarColor() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setCollarColor(DyeColor dyeColor) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public boolean isTamed() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setTamed(boolean b) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public AnimalTamer getOwner() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setOwner(AnimalTamer animalTamer) {
        MyPlugin.fixme("stub method");
    }
}
