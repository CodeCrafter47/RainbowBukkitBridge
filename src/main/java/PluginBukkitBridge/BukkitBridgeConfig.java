package PluginBukkitBridge;

import com.google.common.base.Charsets;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by florian on 04.11.14.
 */
public class BukkitBridgeConfig {
	private YamlConfiguration config;

	public BukkitBridgeConfig() {
		File dir = new File("plugins_mod/PluginBukkitBridge");
		dir.mkdirs();
		File yamlFile = new File("plugins_mod/PluginBukkitBridge/BukkitBridge.yml");
		YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("configurations/BukkitBridge.yml"), Charsets.UTF_8));

		try {
			config = YamlConfiguration.loadConfiguration(yamlFile);
			config.options().copyDefaults(true);
			config.setDefaults(defaultConfig);

			try {
				if (!yamlFile.exists()) {
					config.save(yamlFile);
				}
			} catch (IOException ex) {
				MyPlugin.logger.log(Level.SEVERE, "Could not save " + yamlFile, ex);
			}
		} catch (Exception ex) {
			MyPlugin.logger.severe("Failed to load BukkitBridge.yml. Verify the yaml indentation is correct. Reverting to default BukkitBridge.yml.");
			config = defaultConfig;
		}
	}

	public List<String> getIgnoredCommands(){
		return config.getStringList("ignoreCommands");
	}
}
