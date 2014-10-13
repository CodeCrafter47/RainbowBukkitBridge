package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_EntityAgeable;
import org.bukkit.DyeColor;
import org.bukkit.entity.Sheep;

/**
 * Created by florian on 12.10.14.
 */
public class FakeSheep extends FakeAnimal implements Sheep{
    public FakeSheep(MC_EntityAgeable ageable) {
        super(ageable);
    }

    @Override
    public boolean isSheared() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setSheared(boolean b) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public DyeColor getColor() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setColor(DyeColor dyeColor) {
        MyPlugin.fixme("stub method");
    }
}
