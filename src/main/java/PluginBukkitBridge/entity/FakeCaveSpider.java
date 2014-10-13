package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.CaveSpider;

/**
 * Created by florian on 12.10.14.
 */
public class FakeCaveSpider extends FakeCreature implements CaveSpider {
    public FakeCaveSpider(MC_Entity argEnt) {
        super(argEnt);
    }
}
