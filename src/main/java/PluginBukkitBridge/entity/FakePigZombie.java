package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Zombie;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Villager;

/**
 * Created by florian on 12.10.14.
 */
public class FakePigZombie extends FakeZombie implements PigZombie {
    public FakePigZombie(MC_Zombie argEnt) {
        super(argEnt);
    }

    @Override
    public int getAnger() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setAnger(int i) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public void setAngry(boolean b) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public boolean isAngry() {
        MyPlugin.fixme("stub method");
        return false;
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
