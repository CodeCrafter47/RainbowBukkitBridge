package PluginBukkitBridge.entity;

import PluginReference.MC_Entity;
import org.bukkit.entity.Guardian;

/**
 * Created by florian on 28.11.14.
 */
public class FakeGuardian extends FakeCreature implements Guardian {
	public FakeGuardian(MC_Entity argEnt) {
		super(argEnt);
	}
}
