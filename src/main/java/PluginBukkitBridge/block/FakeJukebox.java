package PluginBukkitBridge.block;

import PluginBukkitBridge.MyPlugin;
import org.bukkit.Material;
import org.bukkit.block.Jukebox;

/**
 * Created by florian on 14.10.14.
 */
public class FakeJukebox extends FakeBlockState implements Jukebox{
    public FakeJukebox(FakeBlock arg) {
        super(arg);
    }

    @Override
    public Material getPlaying() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setPlaying(Material material) {
        MyPlugin.fixme();
    }

    @Override
    public boolean isPlaying() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean eject() {
        MyPlugin.fixme();
        return false;
    }
}
