package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Enderman;
import org.bukkit.Material;
import org.bukkit.entity.Enderman;
import org.bukkit.material.MaterialData;

/**
 * Created by florian on 12.10.14.
 */
public class FakeEnderman extends FakeCreature implements Enderman{
    MC_Enderman enderman;

    public FakeEnderman(MC_Enderman argEnt) {
        super(argEnt);
        enderman = argEnt;
    }

    @Override
    public MaterialData getCarriedMaterial() {
        return Material.getMaterial(enderman.getCarriedBlock().getId()).getNewData((byte) enderman.getCarriedBlock().getSubtype());
    }

    @Override
    public void setCarriedMaterial(MaterialData materialData) {
        MyPlugin.fixme("stub method");
    }
}
