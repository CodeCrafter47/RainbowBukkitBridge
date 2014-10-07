package PluginBukkitBridge;

import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.plugin.messaging.StandardMessenger;

import PluginReference.*;

public class MyPlugin extends PluginReference.PluginBase
{
	public static MC_Server server = null;
	public final static FakeConsoleCommandSender consoleCommandSender = new FakeConsoleCommandSender();
	public final static ServicesManager servicesManager = new SimpleServicesManager();
	public final static StandardMessenger messenger = new StandardMessenger();
    public final static FakeScheduler scheduler = new FakeScheduler();
	public final static FakeCraftServer fakeServer = new FakeCraftServer();
	public final static SimpleCommandMap commandMap = new SimpleCommandMap(fakeServer);
    public final static PluginManager pluginManager = new SimplePluginManager(fakeServer, commandMap);

    public static boolean DebugMode = false;
    
	public void onStartup(MC_Server argServer)
	{
		System.out.println("BukkitBridge v2.4 --- Starting up...");
		server = argServer;
		
		// Initialize Bukkit server object...
		fakeServer.m_server = server;
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

        File pluginFolder = new File("plugins");

        if (!pluginFolder.exists()) {
            pluginFolder.mkdir();
        }
        
        Plugin[] plugins = pluginManager.loadPlugins(pluginFolder);
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

		// Call Bukkit Event
		// ------------------------------------------------
    	FakePlayer player = new FakePlayer();
    	player.m_loginName = playerName;
    	player.m_inetSocketAddress = new InetSocketAddress(ip, 0);
		InetAddress addr = null; 
    	try
    	{
    		addr = InetAddress.getByName(ip);
    	}
    	catch(Exception exc)
    	{
    		
    	}
		PlayerLoginEvent event = new PlayerLoginEvent((Player)player, ip, addr);
        pluginManager.callEvent(event);
		// ------------------------------------------------
		

        // Also call PlayerJoinEvent...
		PlayerJoinEvent joinEvent = new PlayerJoinEvent((Player)player, ip);
        pluginManager.callEvent(joinEvent);
	}
	
	public void onPlayerLogout(String playerName, UUID uuid)
	{
		if(DebugMode)
		{
			String logMsg = String.format("%s onPlayerLogout. UUID=%s", playerName, uuid.toString());
			System.out.println("BukkitBridge -- " + logMsg);
		}
		
		// Call Bukkit Event
		// ------------------------------------------------
    	FakePlayer player = new FakePlayer();
    	player.m_loginName = playerName;
    	PlayerQuitEvent event = new PlayerQuitEvent((Player)player, "Goodbye");
        pluginManager.callEvent(event);
		// ------------------------------------------------
		
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
		
    	FakePlayer player = new FakePlayer();
    	player.m_player = plr;
		PlayerCommandPreprocessEvent event = new PlayerCommandPreprocessEvent((Player)player, msg);
		event.setCancelled(ei.isCancelled);
        pluginManager.callEvent(event);
        
        if(!ei.isCancelled) ei.isCancelled = event.isCancelled();
        if(ei.isCancelled) return;



        FakeCommandSender cs = new FakeCommandSender(plr);
        
        // Issue chat event...
        if(!msg.startsWith("/"))
        {
        	AsyncPlayerChatEvent chatEvent = new AsyncPlayerChatEvent(false, cs, msg, null);
        	chatEvent.setCancelled(ei.isCancelled);
            pluginManager.callEvent(chatEvent);
            if(!ei.isCancelled) ei.isCancelled = chatEvent.isCancelled(); 
            return;
        }

        
        // Call server command...
    	{
    		ServerCommandEvent cmdEvent = new ServerCommandEvent(cs, msg);
    		pluginManager.callEvent(cmdEvent);
    	}
        
        // Call onCommand...
        //--------------------------------------------------------------
        // If not a command, skip...
        if(!msg.startsWith("/") || (msg.length() <= 1) ) return;
        msg = msg.substring(1);
        int idxSpace = msg.indexOf(' ');
        String[] args = new String[0];
        String cmdName = msg;
        String argStart = msg;
        if(idxSpace >= 0) 
        {
        	cmdName = msg.substring(0, idxSpace);
        	argStart = msg.substring(idxSpace+1);
        	args = argStart.split("\\s+");
        }
        
        Plugin[] plugins = pluginManager.getPlugins();
        FakeCommand cmd = new FakeCommand(cmdName);
        cs.m_player = plr;
        for (Plugin plugin : plugins) {
        	if(plugin.isEnabled())
        	{
        		try
        		{
	        		if(plugin.getDescription().getCommands().containsKey(cmdName))
	        		{
		        		if(plugin.onCommand(cs, cmd, cmdName, args)) 
		        		{
		        			//ei.isCancelled = true;
		        			//return;
		        		}
	        			
	        			if(DebugMode) System.out.println("BukkitBridge: Assuming " + plugin.getName() + " handles command: " + cmdName);
	        			ei.isCancelled = true;
	        			return;
	        		}
        		}
        		catch(Exception exc)
        		{
        			exc.printStackTrace();
        		}
        	}
        }
        
        
        //if (this.server.dispatchCommand(event.getPlayer(), event.getMessage().substring(1))) {
            //return;
        //}
        
	} // end of onPlayerInput
	

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

	//public void onAttemptPlaceOrInteract(MC_Player plr, MC_Location loc, MC_EventInfo ei)
	public void onAttemptPlaceOrInteract(MC_Player plr, MC_Location loc, MC_EventInfo ei, MC_DirectionNESWUD dir) 
	{
	
		if(DebugMode)
		{
			String logMsg = String.format("%s Attempting Block Place or Interact @ %s", plr.getName(), loc.toString());
			System.out.println("BukkitBridge -- " + logMsg);
		}

		Player who = new FakePlayer(plr);
		MC_ItemStack isHand = plr.getItemInHand();
		ItemStack is = new ItemStack(isHand.getId(), isHand.getCount());
		
		MC_World world = plr.getWorld();
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		MC_Block blk = world.getBlockAt(x,y,z);
		FakeBlock block = new FakeBlock(blk, world, x, y, z); 
		
		PlayerInteractEvent event = new PlayerInteractEvent(who, Action.RIGHT_CLICK_BLOCK, is, block, BlockFace.SELF);
		event.setCancelled(ei.isCancelled);
        pluginManager.callEvent(event);
        if(!ei.isCancelled) ei.isCancelled = event.isCancelled();       
	}
	
	public void onBlockBroke(MC_Player plr, MC_Location loc, int blockKey)
	{
		if(DebugMode) System.out.println("BukkitBridge -- onBlockBroke to BlockBreakEvent");
		
		int blockID = BlockHelper.getBlockID_FromKey(blockKey);
		int blockID_Subtype = BlockHelper.getBlockSubtype_FromKey(blockKey);
		String strBlockName = BlockHelper.getBlockName(blockID);
		if(blockID_Subtype != 0) strBlockName += ":" + blockID_Subtype;

		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		MC_World world = plr.getWorld();
		MC_Block blk = world.getBlockAt(x,  y, z);

		FakeBlock fakeBlock = new FakeBlock(blk, world, x, y, z, blockID, blockID_Subtype);

		BlockBreakEvent event = new BlockBreakEvent(fakeBlock, new FakePlayer(plr));
		
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
		FakeBlock fakeBlockPlaced = new FakeBlock(blkPlaced, world, x, y, z, isHandItem.getId(), isHandItem.getDamage());

		MC_Block blkAgainst = world.getBlockAt(x2,  y2, z2);
		FakeBlock fakeBlockAgainst = new FakeBlock(blkAgainst, world, x2, y2, z2);
				
		BlockPlaceEvent event = new BlockPlaceEvent(fakeBlockPlaced, new FakeBlockState(fakeBlockPlaced), fakeBlockAgainst, isPlaced, who, true);	
        pluginManager.callEvent(event);
	}
}
