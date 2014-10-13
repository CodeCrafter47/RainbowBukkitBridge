package PluginBukkitBridge.entity;

import PluginReference.MC_EntityAgeable;
import org.bukkit.entity.Chicken;

/**
 * Created by florian on 12.10.14.
 */
public class FakeChicken extends FakeAnimal implements Chicken {
    public FakeChicken(MC_EntityAgeable ageable) {
        super(ageable);
    }
}
