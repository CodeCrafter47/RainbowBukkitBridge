package PluginBukkitBridge.entity;

import PluginReference.MC_EntityAgeable;
import org.bukkit.entity.Cow;

/**
 * Created by florian on 12.10.14.
 */
public class FakeCow extends FakeAnimal implements Cow {
    public FakeCow(MC_EntityAgeable ageable) {
        super(ageable);
    }
}
