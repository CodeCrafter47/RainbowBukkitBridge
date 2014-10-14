package PluginBukkitBridge;

import PluginReference.MC_Player;
import PluginReference.MC_Server;
import com.avaje.ebean.config.ServerConfig;
import org.bukkit.*;
import org.bukkit.BanList.Type;
import org.bukkit.Warning.WarningState;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;


public class FakeCraftServer implements Server
{
	public static MC_Server server = null;
	
	public static void FakeDebug(String msg)
	{
		System.out.println("FakeCraftServer Proxy: " + msg);
	}

    @Override
    public String getName() {
        return "Rainbow";
    }

    @Override
    public String getVersion() {
        return "v" + server.getRainbowVersion();
    }

    @Override
    public String getBukkitVersion() {
        return "1.8";
    }

    @Override
    public Player[] _INVALID_getOnlinePlayers() {
        Collection<? extends Player> players = getOnlinePlayers();
        Player pls[] = new Player[players.size()];
        int i = 0;
        for(Player p: players)pls[i++] = p;
        return pls;
    }

    @Override
    public Collection<? extends Player> getOnlinePlayers() {
        List<Player> players = new ArrayList<>();
        try {
            for (MC_Player plr : server.getPlayers()) players.add(getPlayer(plr.getUUID()));
        } catch (NullPointerException ex){
            // server is not yet initialized
        }
        return players;
    }

    @Override
    public int getPort() {
        return server.getServerPort();
    }

    @Override
    public String getServerName() {
        return getName();
    }

    @Override
    public String getServerId() {
        return getServerName();
    }

    @Override
    public int broadcastMessage(String message) {
        int i = 0;
        for(Player player: getOnlinePlayers()){
            player.sendMessage(message);
            i++;
        }
        return i;
    }

    @Override
    public String getUpdateFolder() {
        return MyPlugin.updateDir.getAbsolutePath();
    }

    @Override
    public File getUpdateFolderFile() {
        return MyPlugin.updateDir;
    }

    @Override
    public Player getPlayer(String name) {
        Player p = getPlayerExact(server.getPlayerExactName(name));
        return p;
    }

    @Override
    public Player getPlayerExact(String name) {
        return PlayerManager.getPlayer(name);
    }

    @Override
    public List<Player> matchPlayer(String name) {
        List<Player> list = new ArrayList<>();
        for(String s: server.getMatchingOnlinePlayerNames(name)){
            list.add(getPlayer(s));
        }
        return list;
    }

    @Override
    public Player getPlayer(UUID id) {
        return PlayerManager.getPlayer(id);
    }

    @Override
    public PluginManager getPluginManager() {
        return MyPlugin.pluginManager;
    }

    @Override
    public BukkitScheduler getScheduler() {
        return MyPlugin.scheduler;
    }

    @Override
    public ServicesManager getServicesManager() {
        return MyPlugin.servicesManager;
    }

    @Override
    public Logger getLogger() {
        return MyPlugin.logger;
    }

    @Override
    public boolean dispatchCommand(CommandSender sender, String commandLine) throws CommandException {
        // fixme also execute rainbow commands
        return MyPlugin.commandMap.dispatch(sender, commandLine);
    }

    @Override
    public int getSpawnRadius() {
        return server.getSpawnProtectionRadius();
    }

    @Override
    public boolean getOnlineMode() {
        return server.getOnlineMode();
    }

    @Override
    public int broadcast(String message, String permission) {
        int i = 0;
        for(Player player: getOnlinePlayers()){
            if(player.hasPermission(permission)){
                player.sendMessage(message);
                i++;
            }
        }
        return i;
    }

    @Override
    public ConsoleCommandSender getConsoleSender() {
        return MyPlugin.consoleCommandSender;
    }

    @Override
    public Messenger getMessenger() {
        return MyPlugin.messenger;
    }

    @Override
    public HelpMap getHelpMap() {
        return MyPlugin.helpMap;
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
		
		return "127.0.0.0";
	}

	@Override
	public ItemFactory getItemFactory()
	{
		return new FakeItemFactory();
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
	public int getMonsterSpawnLimit()
	{
		FakeDebug("getMonsterSpawnLimit");
		
		return 0;
	}

	@Override
	public String getMotd()
	{
		return server.getServerMOTD() == null ? "MOTD" : server.getServerMOTD();
	}

    @Override
    public String getShutdownMessage() {
        FakeDebug("getShutdownMessage");
        return null;
    }

    @Override
	public OfflinePlayer getOfflinePlayer(String arg0)
	{
		return getOfflinePlayer(UUID.fromString(server.getPlayerUUIDFromName(arg0)));
	}

	@Override
	public OfflinePlayer getOfflinePlayer(UUID arg0)
	{
        for(OfflinePlayer player: getOfflinePlayers()){
            if(player.getUniqueId().equals(arg0))return player;
        }
		return null;
	}

	@Override
	public OfflinePlayer[] getOfflinePlayers()
	{
		List<MC_Player> mcPlayers = MyPlugin.server.getOfflinePlayers();

		int len = mcPlayers.size();
        OfflinePlayer[] result = new OfflinePlayer[mcPlayers.size()];
		for(int i=0; i<len; i++)
		{
			FakeOfflinePlayer plr = new FakeOfflinePlayer(mcPlayers.get(i));
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
	public PluginCommand getPluginCommand(String arg)
	{
		FakeDebug("getPluginCommand: " + arg);
		return (PluginCommand)MyPlugin.commandMap.getCommand(arg);
		
		//return null;
	}

	@Override
	public List<Recipe> getRecipesFor(ItemStack arg0)
	{
		FakeDebug("getRecipesFor");
		
		return null;
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

		return null;
    }

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
        if(worldMap == null)
        {
            worldMap = new ConcurrentHashMap<String, FakeWorld>();
            worldMap.put("world", new FakeWorld(MyPlugin.server.getWorld(0)));
            worldMap.put("world_nether", new FakeWorld(MyPlugin.server.getWorld(-1)));
            worldMap.put("world_the_end", new FakeWorld(MyPlugin.server.getWorld(1)));
            worldMap.put("PlotWorld", new FakeWorld(MyPlugin.server.getWorld(2)));
        }
		
		return new ArrayList<World>(worldMap.values());
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
		server.executeCommand("stop");
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
