package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Ocelot;
import org.bukkit.Bukkit;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Ocelot;

/**
 * Created by florian on 12.10.14.
 */
public class FakeOcelot extends FakeAnimal implements Ocelot {

    MC_Ocelot ocelot;

    public FakeOcelot(MC_Ocelot ocelot) {
        super(ocelot);
        this.ocelot = ocelot;
    }

    @Override
    public Type getCatType() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setCatType(Type type) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public boolean isSitting() {
        return ocelot.getSitting();
    }

    @Override
    public void setSitting(boolean b) {
        ocelot.setSitting(b);
    }

    @Override
    public boolean isTamed() {
        return ocelot.isTamed();
    }

    @Override
    public void setTamed(boolean b) {
        ocelot.setTamed(b);
    }

    @Override
    public AnimalTamer getOwner() {
        return Bukkit.getPlayer(ocelot.getUUIDOfOwner());
    }

    @Override
    public void setOwner(AnimalTamer animalTamer) {
        ocelot.setUUIDOfOwner(animalTamer.getUniqueId().toString());
    }
}
