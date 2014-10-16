package PluginBukkitBridge.commands;

import PluginBukkitBridge.MyPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

/**
 * Created by florian on 16.10.14.
 */
public class DebugCommand extends BukkitCommand{
    protected DebugCommand(String name) {
        super(name);
        this.description = "Toggle bukkitbridge debug mode";
        this.usageMessage = "/bbdebug";
        this.setPermission("bukkitbridge.bbdebug");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        MyPlugin.DebugMode = !MyPlugin.DebugMode;
        commandSender.sendMessage(ChatColor.GREEN + "BukkitBridge Debug Mode: " + ChatColor.AQUA + MyPlugin.DebugMode);
        return true;
    }
}
