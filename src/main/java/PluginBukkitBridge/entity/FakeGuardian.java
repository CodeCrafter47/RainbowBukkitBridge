package PluginBukkitBridge.entity;

import org.bukkit.entity.Guardian;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;

/**
 * Created by florian on 28.11.14.
 */
public class FakeGuardian extends FakeCreature implements Guardian {
	public FakeGuardian(MC_Entity argEnt) {
		super(argEnt);
	}

    @Override
    public boolean isElder() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void setElder(boolean b) {
        MyPlugin.fixme();
    }
}
