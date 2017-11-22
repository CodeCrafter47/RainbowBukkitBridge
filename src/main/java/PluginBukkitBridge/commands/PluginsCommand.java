package PluginBukkitBridge.commands;

import PluginBukkitBridge.MyPlugin;
import PluginReference.PluginInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

/**
 * Created by florian on 16.10.14.
 */
public class PluginsCommand extends BukkitCommand {
    public PluginsCommand(String name) {
        super(name);
        this.description = "Gets a list of plugins running on the server";
        this.usageMessage = "/plugins";
        this.setPermission("rainbow.plugins");
        this.setAliases(Arrays.asList("pl"));
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;

        sender.sendMessage("Plugins " + getPluginList());
        sender.sendMessage("Bukkit Plugins " + getBukkitPluginList());
        return true;
    }

    private String getPluginList() {
        StringBuilder pluginList = new StringBuilder();
        List<PluginInfo> plugins = MyPlugin.server.getPlugins();

        for (PluginInfo plugin : plugins) {
            if (pluginList.length() > 0) {
                pluginList.append(ChatColor.WHITE);
                pluginList.append(", ");
            }

            pluginList.append(ChatColor.GREEN);
            pluginList.append(plugin.name);
        }

        return "(" + plugins.size() + "): " + pluginList.toString();
    }

    private String getBukkitPluginList() {
        StringBuilder pluginList = new StringBuilder();
        Plugin[] plugins = Bukkit.getPluginManager().getPlugins();

        for (Plugin plugin : plugins) {
            if (pluginList.length() > 0) {
                pluginList.append(ChatColor.WHITE);
                pluginList.append(", ");
            }

            pluginList.append(plugin.isEnabled() ? ChatColor.GREEN : ChatColor.RED);
            pluginList.append(plugin.getDescription().getName());
        }

        return "(" + plugins.length + "): " + pluginList.toString();
    }
}