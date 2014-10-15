package PluginBukkitBridge;

import PluginBukkitBridge.block.FakeBlock;
import PluginBukkitBridge.entity.FakePlayer;
import PluginBukkitBridge.logging.MyLogHandler;
import PluginReference.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.help.SimpleHelpMap;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.plugin.messaging.StandardMessenger;

import java.io.File;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyPlugin extends PluginReference.PluginBase {
    public static MC_Server server = null;
    public static Logger logger;
    public final static FakeConsoleCommandSender consoleCommandSender = new FakeConsoleCommandSender();
    public final static ServicesManager servicesManager = new SimpleServicesManager();
    public final static StandardMessenger messenger = new StandardMessenger();
    public final static FakeScheduler scheduler = new FakeScheduler();
    public final static FakeCraftServer fakeServer = new FakeCraftServer();
    public final static SimpleCommandMap commandMap = new MyCommandMap(fakeServer);
    public final static PluginManager pluginManager = new SimplePluginManager(fakeServer, commandMap);
    public static SimpleHelpMap helpMap;

    public final static File pluginDir = new File("plugins");
    public final static File updateDir = new File(pluginDir, "update");

    public static boolean DebugMode = false;

    public static void fixme() {
        logger.info("FIXME: stub method at " + new UnsupportedOperationException().getStackTrace()[1].toString());
    }

    public static void fixme(String s) {
        logger.info("FIXME: " + s + " at " + new UnsupportedOperationException().getStackTrace()[1].toString());
    }

    public MyPlugin() {
        super();
        //SimpleFormatter formatter = new SimpleFormatter();
        //Handler handler = new StreamHandler(System.out, new MyLogFormatter());
        logger = Logger.getLogger("");
        for(Handler h: logger.getHandlers())logger.removeHandler(h);
        logger.addHandler(new MyLogHandler());



        //logger = new MyLogger("", null);//Logger.getLogger("BukkitBridge");
        //logger.setLevel(Level.ALL);
        // logger = Logger.getLogger("Minecraft");

        pluginDir.mkdirs();
        updateDir.mkdirs();
        helpMap = new SimpleHelpMap(fakeServer);
    }

    public void onStartup(MC_Server argServer) {
        System.out.println("BukkitBridge v2.4 --- Starting up...");
        server = argServer;

        // Initialize Bukkit server object...
        fakeServer.server = server;
        Bukkit.setServer(fakeServer);
    }

    @Override
    public void onServerFullyLoaded() {
        super.onServerFullyLoaded();
        WorldManager.refresh();
        // load all plugins postworld

        // Load plugin JARs...
        loadPlugins();

        // Call onEnable for plugins...
        enablePlugins(PluginLoadOrder.STARTUP);
        enablePlugins(PluginLoadOrder.POSTWORLD);
    }

    public void onShutdown() {
        System.out.println("BukkitBridge v2.4 --- Shutting down...");
        pluginManager.disablePlugins();
    }

    public PluginInfo getPluginInfo() {
        PluginInfo info = new PluginInfo();
        info.description = "Partial Bukkit support (rbow.org)";
        info.eventSortOrder = 10000.0f; // call way later, lowest priority <-- Bukkit thinks that is highest priority
        return info;
    }


    public static void loadPlugins() {
        pluginManager.registerInterface(JavaPluginLoader.class);

        Plugin[] plugins = pluginManager.loadPlugins(pluginDir);
        for (Plugin plugin : plugins) {
            try {
                String message = String.format("[BukkitBridge] Loading Bukkit plugin: %s", plugin.getDescription().getFullName());
                System.out.println(message);
                try {
                    Field f = JavaPlugin.class.getDeclaredField("logger");
                    f.setAccessible(true);
                    f.set(plugin, new PluginBukkitBridge.logging.PluginLogger(plugin));
                } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                plugin.onLoad();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void enablePlugins(PluginLoadOrder type) {
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

    public void onTick(int tickNumber) {
        scheduler.mainThreadHeartbeat(tickNumber);

    }

    public void onPlayerLogin(String playerName, UUID uuid, String ip) {
        if (DebugMode) {
            String logMsg = String.format("%s onPlayerLogin from IP %s. UUID=%s", playerName, ip, uuid.toString());
            System.out.println("BukkitBridge -- " + logMsg);
        }


        AsyncPlayerPreLoginEvent event = new AsyncPlayerPreLoginEvent(playerName, new InetSocketAddress(ip, 0).getAddress(), uuid);
        pluginManager.callEvent(event);
        // fixme result
    }

    public void onPlayerLogout(String playerName, UUID uuid) {
        if (DebugMode) {
            String logMsg = String.format("%s onPlayerLogout. UUID=%s", playerName, uuid.toString());
            System.out.println("BukkitBridge -- " + logMsg);
        }

        // fixme message
        pluginManager.callEvent(new PlayerQuitEvent(PlayerManager.getPlayer(uuid), ""));
        PlayerManager.removePlayer(uuid);
    }

    // PlayerCommandPreprocessEvent
    public void onPlayerInput(MC_Player plr, String msg, MC_EventInfo ei) {
        if (msg == null) return;
        msg = msg.trim();
        if (msg.length() <= 0) return;

        if (plr.isOp() && msg.equalsIgnoreCase("/bb debug")) {
            DebugMode = !DebugMode;
            plr.sendMessage(ChatColor.GREEN + "BukkitBridge Debug Mode: " + ChatColor.AQUA + DebugMode);
            ei.isCancelled = true;
            return;
        }

        if (DebugMode) {
            String logMsg = String.format("%s onPlayerInput: %s", plr.getName(), msg);
            System.out.println("BukkitBridge -- " + logMsg);
        }

        super.onPlayerInput(plr, msg, ei);
        Matcher match = Pattern.compile(" */(.*)").matcher(msg);
        Player player = PlayerManager.getPlayer(plr);
        player.getLocation();
        if (match.matches()) {
            PlayerCommandPreprocessEvent event = new PlayerCommandPreprocessEvent(player, msg);
            event.setCancelled(ei.isCancelled);
            pluginManager.callEvent(event);
            ei.isCancelled = event.isCancelled();
            if (!event.isCancelled()) {
                // fixme message might be changed
                if (commandMap.dispatch(PlayerManager.getPlayer(plr), match.group(1))) ei.isCancelled = true;
            }
        } else {
            // fixme call PlayerChatEvent ?
        }

    } // end of onPlayerInput


    public void onAttemptEntityDamage(MC_Entity ent, MC_DamageType dmgType, double amt, MC_EventInfo ei) {
        if (DebugMode) {
            String logMsg = String.format("onAttemptEntityDamage: %s %s for %.2f", ent.getName(), dmgType.toString(), amt);
            System.out.println("BukkitBridge -- " + logMsg);
        }

        Entity fakeEnt = Util.wrapEntity(ent);
        EntityDamageEvent event = new EntityDamageEvent(fakeEnt, FakeHelper.GetDamageCause(dmgType), amt);
        event.setCancelled(ei.isCancelled);
        pluginManager.callEvent(event);
        ei.isCancelled = event.isCancelled();

    }

    @Override
    public void onBlockBroke(MC_Player plr, MC_Location loc, int blockKey) {
        BlockBreakEvent event = new BlockBreakEvent(new FakeBlock(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(),
                server.getWorld(loc.dimension)), PlayerManager.getPlayer(plr));
        pluginManager.callEvent(event);
        // fixme result, block break, replace?
    }

    public void onPlayerDeath(MC_Player plrVictim, MC_Player plrKiller, MC_DamageType dmgType, String deathMsg) {
        // fixme set killer
        PlayerDeathEvent event = new PlayerDeathEvent(PlayerManager.getPlayer(plrVictim), null, 0, "");
        // fixme use result
        pluginManager.callEvent(event);
    }

    public void onPlayerRespawn(MC_Player plr) {
        // fixme location, bed spawn
        PlayerRespawnEvent event = new PlayerRespawnEvent(PlayerManager.getPlayer(plr), Util.getLocation(plr.getLocation()), false);
        pluginManager.callEvent(event);
        // fixme result
    }

    public void onConsoleInput(String cmd, MC_EventInfo ei) {
        if (commandMap.dispatch(consoleCommandSender, cmd)) ei.isCancelled = true;
    }

    public void onAttemptBlockBreak(MC_Player plr, MC_Location loc, MC_EventInfo ei) {
        // fixme blockFace
        PlayerInteractEvent event = new PlayerInteractEvent(PlayerManager.getPlayer(plr), Action.LEFT_CLICK_BLOCK,
                Util.getItemStack(plr.getItemInHand()), new FakeBlock(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), plr.getWorld()), BlockFace.DOWN);
        event.setCancelled(ei.isCancelled);
        pluginManager.callEvent(event);
        ei.isCancelled = event.isCancelled();
    }

    public void onAttemptPlaceOrInteract(MC_Player plr, MC_Location loc, MC_EventInfo ei, MC_DirectionNESWUD dir) {
        PlayerInteractEvent event = new PlayerInteractEvent(PlayerManager.getPlayer(plr), Action.RIGHT_CLICK_BLOCK,
                Util.getItemStack(plr.getItemInHand()), new FakeBlock(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), plr.getWorld()), Util.getFace(dir));
        event.setCancelled(ei.isCancelled);
        pluginManager.callEvent(event);
        ei.isCancelled = event.isCancelled();
    }

    boolean allowTeleport = false;

    public void onAttemptPlayerTeleport(MC_Player plr, MC_Location loc, MC_EventInfo ei) {
        if (allowTeleport) return;
        PlayerTeleportEvent event = new PlayerTeleportEvent(PlayerManager.getPlayer(plr), Util.getLocation(plr.getLocation()), Util.getLocation(loc));
        event.setCancelled(ei.isCancelled);
        pluginManager.callEvent(event);
        ei.isCancelled = event.isCancelled();
    }

    public void onAttemptPlayerMove(MC_Player plr, MC_Location locFrom, MC_Location locTo, MC_EventInfo ei) {
        PlayerMoveEvent event = new PlayerMoveEvent(PlayerManager.getPlayer(plr), Util.getLocation(locFrom), Util.getLocation(locTo));
        event.setCancelled(ei.isCancelled);
        pluginManager.callEvent(event);
        Location to = event.getTo();
        if (!event.isCancelled() && (to.getX() != locTo.x || to.getY() != locTo.y || to.getZ() != locTo.z)) {
            ei.isCancelled = true;
            allowTeleport = true;
            plr.teleport(Util.getLocation(to));
            allowTeleport = false;
            return;
        }
        ei.isCancelled = event.isCancelled();
    }

    public void onPlayerJoin(MC_Player plr) {
        PlayerManager.addPlayer(plr);

        PlayerLoginEvent event1 = new PlayerLoginEvent(PlayerManager.getPlayer(plr), plr.getIPAddress(), new InetSocketAddress(plr.getIPAddress(), 0).getAddress());
        pluginManager.callEvent(event1);

        // fixme result
        PlayerJoinEvent event = new PlayerJoinEvent(PlayerManager.getPlayer(plr), "");
        pluginManager.callEvent(event);
    }

    public void onItemPlaced(MC_Player plr, MC_Location loc, MC_ItemStack isHandItem, MC_Location locPlacedAgainst, MC_DirectionNESWUD dir) {
        if (DebugMode) System.out.println("BukkitBridge -- onItemPlaced to BlockPlaceEvent");

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

        FakeBlock fakeBlockPlaced = new FakeBlock(x, y, z, world);

        FakeBlock fakeBlockAgainst = new FakeBlock(x2, y2, z2, world);

        BlockPlaceEvent event = new BlockPlaceEvent(fakeBlockPlaced, fakeBlockPlaced.getState(), fakeBlockAgainst, isPlaced, who, true);
        pluginManager.callEvent(event);
    }

    @Override
    public void onAttemptItemUse(MC_Player plr, MC_ItemStack is, MC_EventInfo ei) {
        PlayerInteractEvent event = new PlayerInteractEvent(PlayerManager.getPlayer(plr), Action.RIGHT_CLICK_AIR,
                Util.getItemStack(is), null, null);
        event.setCancelled(ei.isCancelled);
        pluginManager.callEvent(event);
        ei.isCancelled = event.isCancelled();
    }

    @Override
    public void onAttemptItemDrop(MC_Player plr, MC_ItemStack is, MC_EventInfo ei) {
        PlayerDropItemEvent event = new PlayerDropItemEvent(PlayerManager.getPlayer(plr), new FakedFakeItem(Util.getItemStack(is)));
        event.setCancelled(ei.isCancelled);
        pluginManager.callEvent(event);
        ei.isCancelled = event.isCancelled();
    }

    @Override
    public void onAttemptItemPickup(MC_Player plr, MC_ItemStack is, boolean isXpOrb, MC_EventInfo ei) {
        PlayerPickupItemEvent event = new PlayerPickupItemEvent(PlayerManager.getPlayer(plr), new FakedFakeItem(Util.getItemStack(is)), 0);
        event.setCancelled(ei.isCancelled);
        pluginManager.callEvent(event);
        ei.isCancelled = event.isCancelled();
    }

    @Override
    public boolean onAttemptExplodeSpecific(MC_Entity ent, List<MC_Location> locs) {
        // fixme yield, cancel
        List<Block> blocks = new ArrayList<>();
        for(MC_Location loc: locs){
            Location l = Util.getLocation(loc);
            blocks.add(l.getWorld().getBlockAt(l));
        }
        EntityExplodeEvent event = new EntityExplodeEvent(Util.wrapEntity(ent),Util.getLocation(ent.getLocation()),blocks,1);
        pluginManager.callEvent(event);
        return false;
    }
}
