package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_EntityAgeable;
import org.bukkit.entity.Rabbit;

/**
 * Created by florian on 28.11.14.
 */
public class FakeRabbit extends FakeAnimal implements Rabbit {
	public FakeRabbit(MC_EntityAgeable ageable) {
		super(ageable);
	}

    @Override
    public Type getRabbitType() {
        MyPlugin.fixme();
        return Type.BROWN;
    }

    @Override
    public void setRabbitType(Type type) {
        MyPlugin.fixme();
    }
}
