package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;
import org.bukkit.entity.Guardian;

/**
 * Created by florian on 28.11.14.
 */
public class FakeGuardian extends FakeCreature implements Guardian {
    private boolean isElder;

	public FakeGuardian(MC_Entity argEnt) {
		super(argEnt);
		this.isElder = false;
	}

    @Override
    public boolean isElder() {
        return isElder;
    }

    @Override
    public void setElder(boolean b) {
        this.isElder = b;
    }
}
