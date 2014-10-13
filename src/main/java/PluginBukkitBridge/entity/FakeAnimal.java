package PluginBukkitBridge.entity;

import PluginReference.MC_EntityAgeable;
import org.bukkit.entity.Animals;

/**
 * Created by florian on 12.10.14.
 */
public class FakeAnimal extends FakeAgeable implements Animals {
    public FakeAnimal(MC_EntityAgeable ageable) {
        super(ageable);
    }
}
