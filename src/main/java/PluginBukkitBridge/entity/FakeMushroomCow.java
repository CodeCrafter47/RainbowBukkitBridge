package PluginBukkitBridge.entity;

import PluginReference.MC_EntityAgeable;
import org.bukkit.entity.MushroomCow;

/**
 * Created by florian on 12.10.14.
 */
public class FakeMushroomCow extends FakeCow implements MushroomCow {
    public FakeMushroomCow(MC_EntityAgeable ageable) {
        super(ageable);
    }
}
