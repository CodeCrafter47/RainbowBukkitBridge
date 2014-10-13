package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Enderman;
import org.bukkit.material.MaterialData;

/**
 * Created by florian on 12.10.14.
 */
public class FakeEnderman extends FakeCreature implements Enderman{
    public FakeEnderman(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public MaterialData getCarriedMaterial() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setCarriedMaterial(MaterialData materialData) {
        MyPlugin.fixme("stub method");
    }
}
