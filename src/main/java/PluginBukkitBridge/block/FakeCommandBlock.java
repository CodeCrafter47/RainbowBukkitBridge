package PluginBukkitBridge.block;

import PluginBukkitBridge.MyPlugin;
import org.bukkit.block.CommandBlock;

/**
 * Created by florian on 14.10.14.
 */
public class FakeCommandBlock extends FakeBlockState implements CommandBlock{
    public FakeCommandBlock(FakeBlock arg) {
        super(arg);
    }

    @Override
    public String getCommand() {
        MyPlugin.fixme();
        return "";
    }

    @Override
    public void setCommand(String s) {
        MyPlugin.fixme();
    }

    @Override
    public String getName() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setName(String s) {
        MyPlugin.fixme();
    }
}
