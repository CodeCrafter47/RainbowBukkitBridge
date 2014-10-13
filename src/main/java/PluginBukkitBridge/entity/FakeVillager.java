package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_EntityAgeable;
import org.bukkit.entity.Villager;

/**
 * Created by florian on 12.10.14.
 */
public class FakeVillager extends FakeAgeable implements Villager {
    public FakeVillager(MC_EntityAgeable ageable) {
        super(ageable);
    }

    @Override
    public Profession getProfession() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setProfession(Profession profession) {
        MyPlugin.fixme("stub method");
    }
}
