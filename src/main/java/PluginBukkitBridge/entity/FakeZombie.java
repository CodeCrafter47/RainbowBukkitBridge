package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Zombie;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;

/**
 * Created by florian on 12.10.14.
 */
public class FakeZombie extends FakeCreature implements Zombie{
    MC_Zombie zombie;

    public FakeZombie(MC_Zombie argEnt) {
        super(argEnt);
        zombie = argEnt;
    }

    @Override
    public boolean isBaby() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setBaby(boolean b) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public boolean isVillager() {
        return zombie.isVillager();
    }

    @Override
    public void setVillager(boolean b) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public void setVillagerProfession(Villager.Profession profession) {
        MyPlugin.fixme();
    }

    @Override
    public Villager.Profession getVillagerProfession() {
        MyPlugin.fixme();
        return Villager.Profession.FARMER;
    }
}
