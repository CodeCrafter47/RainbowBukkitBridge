package PluginBukkitBridge;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.BanList;
import org.bukkit.BanList.Type;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.UnsafeValues;
import org.bukkit.Warning.WarningState;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.defaults.PluginsCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;

import PluginReference.MC_Player;
import PluginReference.MC_Server;

import com.avaje.ebean.config.ServerConfig;


public class FakeCraftServer 	implements Server 
{
	public static MC_Server m_server = null;
	
	public static void FakeDebug(String msg)
	{
		System.out.println("FakeCraftServer Proxy: " + msg);
	}
	
	@Override
	public Set<String> getListeningPluginChannels()
	{
		FakeDebug("getListeningPluginChannels");
		
		return null;
	}

	@Override
	public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2)
	{
		FakeDebug("sendPluginMessage");
		
		
	}

	@Override
	public boolean addRecipe(Recipe arg0)
	{
		FakeDebug("addRecipe");
		
		return false;
	}

	@Override
	public void banIP(String arg0)
	{
		
		FakeDebug("banIP: " + arg0);
		
	}

	@Override
	public int broadcast(String arg0, String arg1)
	{
		FakeDebug("broadcast: arg0=" + arg0 + ", arg1=" + arg1);
		
		return 0;
	}

	@Override
	public int broadcastMessage(String arg0)
	{
		FakeDebug("broadcastMessage: arg0=" + arg0);
		
		return 0;
	}

	@Override
	public void clearRecipes()
	{
		FakeDebug("clearRecipes");
		
		
	}

	@Override
	public void configureDbConfig(ServerConfig arg0)
	{
		FakeDebug("configureDbConfig");
		
		
	}

	@Override
	public Inventory createInventory(InventoryHolder arg0, InventoryType arg1)
	{
		FakeDebug("createInventory");
		
		return null;
	}

	@Override
	public Inventory createInventory(InventoryHolder arg0, int arg1) throws IllegalArgumentException
	{
		FakeDebug("createInventory2");
		
		return null;
	}

	@Override
	public Inventory createInventory(InventoryHolder arg0, InventoryType arg1, String arg2)
	{
		FakeDebug("createInventory3");
		
		return null;
	}

	@Override
	public Inventory createInventory(InventoryHolder arg0, int arg1, String arg2) throws IllegalArgumentException
	{
		FakeDebug("createInventory4");
		
		return null;
	}

	@Override
	public MapView createMap(World arg0)
	{
		FakeDebug("createMap");
		
		return null;
	}

	@Override
	public World createWorld(WorldCreator arg0)
	{
		FakeDebug("createWorld");
		
		return null;
	}

	@Override
	public boolean dispatchCommand(CommandSender arg0, String arg1) throws CommandException
	{
		FakeDebug("dispatchCommand");
		
		return false;
	}

	@Override
	public boolean getAllowEnd()
	{
		FakeDebug("getAllowEnd");
		
		return false;
	}

	@Override
	public boolean getAllowFlight()
	{
		FakeDebug("getAllowFlight");
		
		return false;
	}

	@Override
	public boolean getAllowNether()
	{
		FakeDebug("getAllowNether");
		
		return false;
	}

	@Override
	public int getAmbientSpawnLimit()
	{
		FakeDebug("getAmbientSpawnLimit");
		
		return 0;
	}

	@Override
	public int getAnimalSpawnLimit()
	{
		FakeDebug("getAnimalSpawnLimit");
		
		return 0;
	}

	@Override
	public BanList getBanList(Type arg0)
	{
		FakeDebug("getBanList");
		
		return null;
	}

	@Override
	public Set<OfflinePlayer> getBannedPlayers()
	{
		FakeDebug("getBannedPlayers --- returning empty for now");
		return null;
	}

	@Override
	public String getBukkitVersion()
	{
		return "Bridge 1.7.9";
	}

	@Override
	public Map<String, String[]> getCommandAliases()
	{
		FakeDebug("getCommandAliases");
		
		return null;
	}

	@Override
	public long getConnectionThrottle()
	{
		FakeDebug("getConnectionThrottle");
		
		return 0;
	}

	@Override
	public ConsoleCommandSender getConsoleSender()
	{
		//FakeDebug("getConsoleSender");
		return MyPlugin.consoleCommandSender;
		
		//return null;
	}

	@Override
	public GameMode getDefaultGameMode()
	{
		FakeDebug("getDefaultGameMode");
		
		return null;
	}

	@Override
	public boolean getGenerateStructures()
	{
		FakeDebug("getGenerateStructures");
		
		return false;
	}

	@Override
	public HelpMap getHelpMap()
	{
		FakeDebug("getHelpMap");
		
		return null;
	}

	@Override
	public Set<String> getIPBans()
	{
		FakeDebug("getIPBans");
		
		return null;
	}

	@Override
	public int getIdleTimeout()
	{
		FakeDebug("getIdleTimeout");
		
		return 0;
	}

	@Override
	public String getIp()
	{
		FakeDebug("getIp");
		
		return null;
	}

	@Override
	public ItemFactory getItemFactory()
	{
		return new FakeItemFactory();
	}

	public Logger m_logger = null;
	@Override
	public Logger getLogger()
	{
		if(m_logger == null)
		{
			//FakeDebug("getLogger");
			//ConsoleHandler handler = new ConsoleHandler();
			ConsoleHandler handler = new ConsoleHandler();
			handler.setFormatter(new MyLogFormatter());
			//m_logger = Logger.getLogger("jkc");
			m_logger = Logger.getLogger("Minecraft");
			//m_logger.setLevel(Level.OFF);
			m_logger.addHandler(handler);
			return m_logger;
		}
		return m_logger;
		/*handler.setFormatter(new MyLogFormatter());
		
		Logger logger = Logger.getLogger(
		logger.
		return Logger.getAnonymousLogger();*/
				
		//return Logger.getGlobal();
		
		//return null;
	}

	@Override
	public MapView getMap(short arg0)
	{
		FakeDebug("getMap");
		
		return null;
	}

	@Override
	public int getMaxPlayers()
	{
		FakeDebug("getMaxPlayers");
		
		return 0;
	}

	@Override
	public Messenger getMessenger()
	{
		//FakeDebug("getMessenger");
		
		//return null;
		return MyPlugin.messenger;
	}

	@Override
	public int getMonsterSpawnLimit()
	{
		FakeDebug("getMonsterSpawnLimit");
		
		return 0;
	}

	@Override
	public String getMotd()
	{
		FakeDebug("getMotd");
		return null;
	}

	@Override
	public String getName()
	{
		return "Rainbow Mod (BukkitBridge)";
	}

	@Override
	public OfflinePlayer getOfflinePlayer(String arg0)
	{
		FakeDebug("getOfflinePlayer");
		
		return null;
	}

	@Override
	public OfflinePlayer getOfflinePlayer(UUID arg0)
	{
		FakeDebug("getOfflinePlayer");
		
		return null;
	}

	@Override
	public OfflinePlayer[] getOfflinePlayers()
	{	
		List<MC_Player> mcPlayers = MyPlugin.server.getOfflinePlayers();

		int len = mcPlayers.size();
		Player[] result = new Player[mcPlayers.size()];
		for(int i=0; i<len; i++)
		{
			FakePlayer plr = new FakePlayer();
			plr.m_player = mcPlayers.get(i);
			plr.m_loginName = plr.m_player.getName();
			result[i] = plr;
		}
		return result;
	}

	@Override
	public boolean getOnlineMode()
	{
		return MyPlugin.server.getOnlineMode();
	}

	@Override
	public Player[] getOnlinePlayers()
	{
		//FakeDebug("getOnlinePlayers");
		List<MC_Player> mcPlayers = MyPlugin.server.getPlayers();

		int len = mcPlayers.size();
		Player[] result = new Player[mcPlayers.size()];
		for(int i=0; i<len; i++)
		{
			FakePlayer plr = new FakePlayer();
			plr.m_player = mcPlayers.get(i);
			plr.m_loginName = plr.m_player.getName();
			result[i] = plr;
		}
		return result;
	}

	@Override
	public Set<OfflinePlayer> getOperators()
	{
		FakeDebug("getOperators");
		return null;
	}

	@Override
	public Player getPlayer(String arg0)
	{
		FakeDebug("getPlayer");
		return null;
	}

	@Override
	public Player getPlayer(UUID arg0)
	{
		FakeDebug("getPlayer");
		
		return null;
	}

	@Override
	public Player getPlayerExact(String arg0)
	{
		FakeDebug("getPlayerExact");
		
		return null;
	}

	@Override
	public PluginCommand getPluginCommand(String arg)
	{
		FakeDebug("getPluginCommand: " + arg);
		return (PluginCommand)MyPlugin.commandMap.getCommand(arg);
		
		//return null;
	}

	@Override
	public PluginManager getPluginManager()
	{
		//FakeDebug("getPluginManager");
		return MyPlugin.pluginManager;
	}

	@Override
	public int getPort()
	{
		return MyPlugin.server.getServerPort();
	}

	@Override
	public List<Recipe> getRecipesFor(ItemStack arg0)
	{
		FakeDebug("getRecipesFor");
		
		return null;
	}

	@Override
	public BukkitScheduler getScheduler()
	{
		return MyPlugin.scheduler;
	}

	@Override
	public ScoreboardManager getScoreboardManager()
	{
		FakeDebug("getScoreboardManager");
		
		return null;
	}

	@Override
	public CachedServerIcon getServerIcon()
	{
		FakeDebug("getServerIcon");
		
		return null;
	}

	@Override
	public String getServerId()
	{
		FakeDebug("getServerId");
		
		return null;
	}

	@Override
	public String getServerName()
	{
		FakeDebug("getServerName");
		
		return null;
	}

	@Override
	public ServicesManager getServicesManager()
	{
		return MyPlugin.servicesManager;
	}

	@Override
	public String getShutdownMessage()
	{
		FakeDebug("getShutdownMessage");		
		return null;
	}

	@Override
	public int getSpawnRadius()
	{
		return MyPlugin.server.getSpawnProtectionRadius();
	}

	@Override
	public int getTicksPerAnimalSpawns()
	{
		FakeDebug("getTicksPerAnimalSpawns");
		
		return 0;
	}

	@Override
	public int getTicksPerMonsterSpawns()
	{
		FakeDebug("getTicksPerMonsterSpawns");
		
		return 0;
	}

	@Override
	public UnsafeValues getUnsafe()
	{
		FakeDebug("getUnsafe");
		
		return null;
	}

	@Override
	public String getUpdateFolder()
	{
		//FakeDebug("getUpdateFolder");
		return "bkt_updates";
		
		//return null;
	}

	@Override
	public File getUpdateFolderFile()
	{
		FakeDebug("getUpdateFolderFile");
		
		return null;
	}

	@Override
	public String getVersion()
	{
		return "1.0";
	}

	@Override
	public int getViewDistance()
	{
		FakeDebug("getViewDistance");
		
		return 0;
	}

	@Override
	public WarningState getWarningState()
	{
		FakeDebug("getWarningState");
		
		return null;
	}

	@Override
	public int getWaterAnimalSpawnLimit()
	{
		FakeDebug("getWaterAnimalSpawnLimit");
		
		return 0;
	}

	@Override
	public Set<OfflinePlayer> getWhitelistedPlayers()
	{
		FakeDebug("getWhitelistedPlayers");
		
		return null;
	}

	public ConcurrentHashMap<String, FakeWorld> worldMap = null; 
	@Override
	public World getWorld(String worldName)
	{
		if(worldMap == null)
		{
			worldMap = new ConcurrentHashMap<String, FakeWorld>();
			worldMap.put("world", new FakeWorld(MyPlugin.server.getWorld(0)));
			worldMap.put("world_nether", new FakeWorld(MyPlugin.server.getWorld(-1)));
			worldMap.put("world_the_end", new FakeWorld(MyPlugin.server.getWorld(1)));
			worldMap.put("PlotWorld", new FakeWorld(MyPlugin.server.getWorld(2)));
		}
		
		if(worldName.equalsIgnoreCase("world")) return worldMap.get("world");
		if(worldName.equalsIgnoreCase("world_nether")) return worldMap.get("world_nether");
		if(worldName.equalsIgnoreCase("world_the_end")) return worldMap.get("world_the_end");
		if(worldName.equalsIgnoreCase("PlotWorld")) return worldMap.get("PlotWorld");

		FakeDebug("getWorld: " + worldName + " -- returning 'world' instead...");
		return worldMap.get("world");	}

	@Override
	public World getWorld(UUID arg0)
	{
		FakeDebug("getWorld");
		
		return null;
	}

	@Override
	public File getWorldContainer()
	{
		FakeDebug("getWorldContainer");
		
		return null;
	}

	@Override
	public String getWorldType()
	{
		FakeDebug("getWorldType");
		
		return null;
	}

	@Override
	public List<World> getWorlds()
	{
		FakeDebug("getWorlds");
		
		return null;
	}

	@Override
	public boolean hasWhitelist()
	{
		FakeDebug("hasWhitelist");
		
		return false;
	}

	@Override
	public boolean isHardcore()
	{
		FakeDebug("isHardcore");
		
		return false;
	}

	@Override
	public boolean isPrimaryThread()
	{
		FakeDebug("isPrimaryThread");
		
		return false;
	}

	@Override
	public CachedServerIcon loadServerIcon(File arg0) throws IllegalArgumentException, Exception
	{
		FakeDebug("loadServerIcon");
		
		return null;
	}

	@Override
	public CachedServerIcon loadServerIcon(BufferedImage arg0) throws IllegalArgumentException, Exception
	{
		FakeDebug("loadServerIcon");
		
		return null;
	}

	@Override
	public List<Player> matchPlayer(String arg0)
	{
		FakeDebug("matchPlayer");
		
		return null;
	}

	@Override
	public Iterator<Recipe> recipeIterator()
	{
		FakeDebug("recipeIterator");
		
		return null;
	}

	@Override
	public void reload()
	{
		FakeDebug("reload");
		
		
	}

	@Override
	public void reloadWhitelist()
	{
		FakeDebug("reloadWhitelist");
		
		
	}

	@Override
	public void resetRecipes()
	{
		FakeDebug("resetRecipes");
		
		
	}

	@Override
	public void savePlayers()
	{
		FakeDebug("savePlayers");
		
		
	}

	@Override
	public void setDefaultGameMode(GameMode arg0)
	{
		FakeDebug("setDefaultGameMode");
		
		
	}

	@Override
	public void setIdleTimeout(int arg0)
	{
		FakeDebug("setIdleTimeout");
		
		
	}

	@Override
	public void setSpawnRadius(int arg0)
	{
		FakeDebug("setSpawnRadius");
		
		
	}

	@Override
	public void setWhitelist(boolean arg0)
	{
		FakeDebug("setWhitelist");
		
		
	}

	@Override
	public void shutdown()
	{
		FakeDebug("shutdown");
		
		
	}

	@Override
	public void unbanIP(String arg0)
	{
		FakeDebug("unbanIP");
		
		
	}

	@Override
	public boolean unloadWorld(String arg0, boolean arg1)
	{
		FakeDebug("unloadWorld");
		
		return false;
	}

	@Override
	public boolean unloadWorld(World arg0, boolean arg1)
	{
		FakeDebug("unloadWorld");
		
		return false;
	}

	@Override
	public boolean useExactLoginLocation()
	{
		FakeDebug("useExactLoginLocation");
		return false;
	}

}
