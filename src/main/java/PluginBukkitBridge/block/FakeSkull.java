package PluginBukkitBridge.block;

import PluginBukkitBridge.MyPlugin;
import org.bukkit.SkullType;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;

/**
 * Created by florian on 14.10.14.
 */
public class FakeSkull extends FakeBlockState implements Skull {
    public FakeSkull(FakeBlock arg) {
        super(arg);
    }

    @Override
    public boolean hasOwner() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public String getOwner() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean setOwner(String s) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public BlockFace getRotation() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setRotation(BlockFace blockFace) {
        MyPlugin.fixme();
    }

    @Override
    public SkullType getSkullType() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setSkullType(SkullType skullType) {
        MyPlugin.fixme();
    }
}
