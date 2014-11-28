package PluginBukkitBridge.entity;

import PluginReference.MC_EntityAgeable;
import org.bukkit.entity.Rabbit;

/**
 * Created by florian on 28.11.14.
 */
public class FakeRabbit extends FakeAnimal implements Rabbit {
	public FakeRabbit(MC_EntityAgeable ageable) {
		super(ageable);
	}
}
