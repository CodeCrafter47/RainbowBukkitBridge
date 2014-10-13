package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.Spider;

/**
 * Created by florian on 12.10.14.
 */
public class FakeSpider extends FakeCreature implements Spider {
    public FakeSpider(MC_Entity argEnt) {
        super(argEnt);
    }
}
