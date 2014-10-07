package PluginBukkitBridge;

import PluginReference.*;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.help.SimpleHelpMap;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.plugin.messaging.StandardMessenger;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyPlugin extends PluginReference.PluginBase
{
	public static MC_Server server = null;
    public static Logger logger;
	public final static FakeConsoleCommandSender consoleCommandSender = new FakeConsoleCommandSender();
	public final static ServicesManager servicesManager = new SimpleServicesManager();
	public final static StandardMessenger messenger = new StandardMessenger();
    public final static FakeScheduler scheduler = new FakeScheduler();
	public final static FakeCraftServer fakeServer = new FakeCraftServer();
	public final static SimpleCommandMap commandMap = new SimpleCommandMap(fakeServer);
    public final static PluginManager pluginManager = new SimplePluginManager(fakeServer, commandMap);
    public final static SimpleHelpMap helpMap = new SimpleHelpMap(fakeServer);

    public final static File pluginDir = new File("plugins");
    public final static File updateDir = new File(pluginDir, "update");

    public static Map<UUID, FakePlayer> players = new HashMap<>();

    public static boolean DebugMode = false;

    public static void fixme() {
        new UnsupportedOperationException("FIXME").printStackTrace();
    }

    public MyPlugin() {
        super();
        Handler handler = new StreamHandler(System.out, new MyLogFormatter());
        logger = Logger.getLogger("Minecraft");
        logger.addHandler(handler);
        pluginDir.mkdirs();
        updateDir.mkdirs();
    }

    public void onStartup(MC_Server argServer)
	{
		System.out.println("BukkitBridge v2.4 --- Starting up...");
		server = argServer;
		
		// Initialize Bukkit server object...
		fakeServer.server = server;
		Bukkit.setServer(fakeServer);
		
		// Load plugin JARs...
        loadPlugins();
        
        // Call onEnable for plugins...
        enablePlugins(PluginLoadOrder.STARTUP);
        enablePlugins(PluginLoadOrder.POSTWORLD);
		
	}
	public void onShutdown()
	{
		System.out.println("BukkitBridge v2.4 --- Shutting down...");
        pluginManager.disablePlugins();
	}

	public PluginInfo getPluginInfo() 
	{ 
		PluginInfo info = new PluginInfo();
		info.description = "Partial Bukkit support (rbow.org)";
		info.eventSortOrder = 10000.0f; // call way later, lowest priority
		return info;
	}
	
	
    public static void loadPlugins()
    {
        pluginManager.registerInterface(JavaPluginLoader.class);
        
        Plugin[] plugins = pluginManager.loadPlugins(pluginDir);
        for (Plugin plugin : plugins) {
            try {
                String message = String.format("[BukkitBridge] Loading Bukkit plugin: %s", plugin.getDescription().getFullName());
                System.out.println(message); 
                plugin.onLoad();
                plugin.getLogger().setLevel(Level.OFF);
            } catch (Throwable ex) {
            	ex.printStackTrace();
            }
        }
        
    }
    
    public static void enablePlugins(PluginLoadOrder type)
    {
        Plugin[] plugins = pluginManager.getPlugins();

        for (Plugin plugin : plugins) {
            if ((!plugin.isEnabled()) && (plugin.getDescription().getLoad() == type)) {
            	
                loadPlugin(plugin);
            }
        }
    	
    }
    
    private static void loadPlugin(Plugin plugin) {
        try {
            pluginManager.enablePlugin(plugin);

            List<Permission> perms = plugin.getDescription().getPermissions();

            for (Permission perm : perms) {
                try {
                    pluginManager.addPermission(perm);
                } catch (IllegalArgumentException ex) {
                	ex.printStackTrace();
                }
            }
        } catch (Throwable ex) {
        	ex.printStackTrace();
        }
    }
	
	public void onTick(int tickNumber)
	{
        scheduler.mainThreadHeartbeat(tickNumber);
		
	}
	
	public void onPlayerLogin(String playerName, UUID uuid, String ip)
	{
		if(DebugMode) 
		{
			String logMsg = String.format("%s onPlayerLogin from IP %s. UUID=%s", playerName, ip, uuid.toString());
			System.out.println("BukkitBridge -- " + logMsg);
		}


        AsyncPlayerPreLoginEvent event = new AsyncPlayerPreLoginEvent(playerName, new InetSocketAddress(ip, 0).getAddress(), uuid);
        pluginManager.callEvent(event);
        // fixme result
	}
	
	public void onPlayerLogout(String playerName, UUID uuid)
	{
		if(DebugMode)
		{
			String logMsg = String.format("%s onPlayerLogout. UUID=%s", playerName, uuid.toString());
			System.out.println("BukkitBridge -- " + logMsg);
		}

        // fixme message
        pluginManager.callEvent(new PlayerQuitEvent(players.get(uuid), ""));
        players.remove(uuid);
		
	}

	// PlayerCommandPreprocessEvent
	public void onPlayerInput(MC_Player plr, String msg, MC_EventInfo ei)
	{
		if(msg == null) return;
        msg = msg.trim();
        if(msg.length() <= 0) return;
        
        if(plr.isOp() && msg.equalsIgnoreCase("/bb debug"))
        {
        	DebugMode = !DebugMode;
        	plr.sendMessage(ChatColor.GREEN + "BukkitBridge Debug Mode: " + ChatColor.AQUA + DebugMode);
        	ei.isCancelled = true;
        	return;
        }
        
		if(DebugMode)
		{
			String logMsg = String.format("%s onPlayerInput: %s", plr.getName(), msg);
			System.out.println("BukkitBridge -- " + logMsg);
		}

        super.onPlayerInput(plr, msg, ei);
        Matcher match = Pattern.compile(" */(.*)").matcher(msg);
        Player player = getPlayer(plr.getName());
        player.getLocation();
        if (match.matches()) {
            PlayerCommandPreprocessEvent event = new PlayerCommandPreprocessEvent(player, msg);
            pluginManager.callEvent(event);
            if(!event.isCancelled()) {
                // fixme message might be changed
                if (commandMap.dispatch(getPlayer(plr.getName()), match.group(1))) ei.isCancelled = true;
            }
        } else {
            // fixme call PlayerChatEvent ?
        }
        
	} // end of onPlayerInput

    public static Player getPlayer(String name) {
        if (!players.containsKey(UUID.fromString(server.getPlayerUUIDFromName(name)))){
            MC_Player player = server.getOnlinePlayerByName(name);
            if(player == null)return null;
            players.put(player.getUUID(), new FakePlayer(player));
        }
        return players.get(UUID.fromString(server.getPlayerUUIDFromName(name)));
    }


    public void onAttemptEntityDamage(MC_Entity ent, MC_DamageType dmgType, double amt, MC_EventInfo ei)
	{
		if(DebugMode)
		{
			String logMsg = String.format("onAttemptEntityDamage: %s %s for %.2f", ent.getName(), dmgType.toString(), amt);
			System.out.println("BukkitBridge -- " + logMsg);
		}
		
		FakeEntity fakeEnt = new FakeEntity(ent);
		EntityDamageEvent event = new EntityDamageEvent(fakeEnt, FakeHelper.GetDamageCause(dmgType), amt);
		event.setCancelled(ei.isCancelled);
        pluginManager.callEvent(event);
		
		
	}

    @Override
    public void onBlockBroke(MC_Player plr, MC_Location loc, int blockKey) {
        super.onBlockBroke(plr, loc, blockKey);
        BlockBreakEvent event = new BlockBreakEvent(new FakeBlock(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(),
                server.getWorld(loc.dimension)), getPlayer(plr.getName()));
        pluginManager.callEvent(event);
        // fixme result, block break, replace?
    }

    public void onPlayerDeath(MC_Player plrVictim, MC_Player plrKiller, MC_DamageType dmgType, String deathMsg) {
        super.onPlayerDeath(plrVictim, plrKiller, dmgType, deathMsg);
        // fixme set killer
        PlayerDeathEvent event = new PlayerDeathEvent(getPlayer(plrVictim.getName()), null, 0, "");
        // fixme use result
        pluginManager.callEvent(event);
    }

    public void onPlayerRespawn(MC_Player plr) {
        super.onPlayerRespawn(plr);
        // fixme location, bed spawn
        PlayerRespawnEvent event = new PlayerRespawnEvent(getPlayer(plr.getName()), Util.getLocation(plr.getLocation()), false);
        pluginManager.callEvent(event);
        // fixme result
    }

    public void onConsoleInput(String cmd, MC_EventInfo ei) {
        super.onConsoleInput(cmd, ei);
        if (commandMap.dispatch(consoleCommandSender, cmd)) ei.isCancelled = true;
    }

    public void onAttemptBlockBreak(MC_Player plr, MC_Location loc, MC_EventInfo ei) {
        super.onAttemptBlockBreak(plr, loc, ei);
        // fixme blockFace
        PlayerInteractEvent event = new PlayerInteractEvent(getPlayer(plr.getName()), Action.LEFT_CLICK_BLOCK,
                Util.getItemStack(plr.getItemInHand()), new FakeBlock(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), plr.getWorld()), BlockFace.DOWN);
        pluginManager.callEvent(event);
        ei.isCancelled = event.isCancelled();
    }

    public void onAttemptPlaceOrInteract(MC_Player plr, MC_Location loc, MC_EventInfo ei, MC_DirectionNESWUD dir) {
        super.onAttemptPlaceOrInteract(plr, loc, ei, dir);
        PlayerInteractEvent event = new PlayerInteractEvent(getPlayer(plr.getName()), Action.RIGHT_CLICK_BLOCK,
                Util.getItemStack(plr.getItemInHand()), new FakeBlock(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), plr.getWorld()), Util.getFace(dir));
        pluginManager.callEvent(event);
        ei.isCancelled = event.isCancelled();
    }

    public void onAttemptPlayerTeleport(MC_Player plr, MC_Location loc, MC_EventInfo ei) {
        super.onAttemptPlayerTeleport(plr, loc, ei);
        PlayerTeleportEvent event = new PlayerTeleportEvent(getPlayer(plr.getName()), Util.getLocation(plr.getLocation()), Util.getLocation(loc));
        pluginManager.callEvent(event);
        ei.isCancelled = event.isCancelled();
    }

    public void onAttemptPlayerMove(MC_Player plr, MC_Location locFrom, MC_Location locTo, MC_EventInfo ei) {
        super.onAttemptPlayerMove(plr, locFrom, locTo, ei);
        PlayerMoveEvent event = new PlayerMoveEvent(getPlayer(plr.getName()), Util.getLocation(locFrom), Util.getLocation(locTo));
        pluginManager.callEvent(event);
        ei.isCancelled = event.isCancelled();
    }

    public void onPlayerJoin(MC_Player plr) {
        super.onPlayerJoin(plr);
        PlayerLoginEvent event1 = new PlayerLoginEvent(getPlayer(plr.getName()), plr.getIPAddress(), new InetSocketAddress(plr.getIPAddress(), 0).getAddress());
        pluginManager.callEvent(event1);

        // fixme result
        PlayerJoinEvent event = new PlayerJoinEvent(getPlayer(plr.getName()), "");
        pluginManager.callEvent(event);
    }
	

	public void onItemPlaced(MC_Player plr, MC_Location loc, MC_ItemStack isHandItem, MC_Location locPlacedAgainst, MC_DirectionNESWUD dir)
	{
		if(DebugMode) System.out.println("BukkitBridge -- onItemPlaced to BlockPlaceEvent");
		
		Player who = new FakePlayer(plr);
		MC_ItemStack isHand = plr.getItemInHand();
		ItemStack isPlaced = new ItemStack(isHand.getId(), isHand.getCount());

		MC_World world = plr.getWorld();
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		
		int x2 = locPlacedAgainst.getBlockX();
		int y2 = locPlacedAgainst.getBlockY();
		int z2 = locPlacedAgainst.getBlockZ();

		MC_Block blkPlaced = world.getBlockAt(x,  y, z);
		FakeBlock fakeBlockPlaced = new FakeBlock(x, y, z, world);

		MC_Block blkAgainst = world.getBlockAt(x2,  y2, z2);
		FakeBlock fakeBlockAgainst = new FakeBlock(x2, y2, z2, world);
				
		BlockPlaceEvent event = new BlockPlaceEvent(fakeBlockPlaced, new FakeBlockState(fakeBlockPlaced), fakeBlockAgainst, isPlaced, who, true);	
        pluginManager.callEvent(event);
	}
}
