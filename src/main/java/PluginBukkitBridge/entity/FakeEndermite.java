package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.Endermite;

/**
 * Created by florian on 28.11.14.
 */
public class FakeEndermite extends FakeCreature implements Endermite {
	public FakeEndermite(MC_Entity argEnt) {
		super(argEnt);
	}
}
