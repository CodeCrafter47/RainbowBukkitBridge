package PluginBukkitBridge;

import PluginBukkitBridge.entity.FakePlayer;
import PluginBukkitBridge.item.FakeItemFactory;
import PluginReference.MC_Block;
import PluginReference.MC_Player;
import PluginReference.MC_Server;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;
import com.avaje.ebeaninternal.server.lib.sql.TransactionIsolation;
import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.BanList;
import org.bukkit.BanList.Type;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.UnsafeValues;
import org.bukkit.Warning.WarningState;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.inventory.CraftFurnaceRecipe;
import org.bukkit.craftbukkit.inventory.CraftRecipe;
import org.bukkit.craftbukkit.inventory.CraftShapedRecipe;
import org.bukkit.craftbukkit.inventory.CraftShapelessRecipe;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
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
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FakeCraftServer implements Server {
    public static MC_Server server = null;

    private FakeItemFactory itemFactory = new FakeItemFactory();

    @Override
    public String getName() {
        return "Rainbow";
    }

    @Override
    public String getVersion() {
        return "v" + server.getRainbowVersion() + " (MC: 1.8)";
    }

    @Override
    public String getBukkitVersion() {
        return "v" + server.getRainbowVersion();
    }

    @Override
    public Player[] _INVALID_getOnlinePlayers() {
        Collection<? extends Player> players = getOnlinePlayers();
        Player pls[] = new Player[players.size()];
        int i = 0;
        for (Player p : players) pls[i++] = p;
        return pls;
    }

    @Override
    public Collection<? extends Player> getOnlinePlayers() {
        List<Player> players = new ArrayList<>();
        try {
            for (MC_Player plr : server.getPlayers())
                players.add(getPlayer(plr.getUUID()));
        } catch (NullPointerException ex) {
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
        for (Player player : getOnlinePlayers()) {
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
        for (String s : server.getMatchingOnlinePlayerNames(name)) {
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
        if (!MyPlugin.commandMap.dispatch(sender, commandLine)) {
            if (sender instanceof Player) {
                ((FakePlayer) sender).player.executeCommand(commandLine);
                return true;
            } else {
                server.executeCommand(commandLine);
                return true;
            }
        }
        return true;
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
        for (Player player : getOnlinePlayers()) {
            if (player.hasPermission(permission)) {
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
    public Set<String> getListeningPluginChannels() {
        return MyPlugin.messenger.getIncomingChannels();
    }

    @Override
    public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2) {
        for (Player player : getOnlinePlayers()) {
            player.sendPluginMessage(arg0, arg1, arg2);
        }
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        try {
            CraftRecipe toAdd;
            if (recipe instanceof CraftRecipe) {
                toAdd = (CraftRecipe) recipe;
            } else {
                if (recipe instanceof ShapedRecipe) {
                    toAdd = CraftShapedRecipe.fromBukkitRecipe((ShapedRecipe) recipe);
                } else if (recipe instanceof ShapelessRecipe) {
                    toAdd = CraftShapelessRecipe.fromBukkitRecipe((ShapelessRecipe) recipe);
                } else if (recipe instanceof FurnaceRecipe) {
                    toAdd = CraftFurnaceRecipe.fromBukkitRecipe((FurnaceRecipe) recipe);
                } else {
                    return false;
                }
            }
            toAdd.addToCraftingManager();
            Object RecipeBook = Class.forName("joebkt.RecipeBook").getMethod("getRecipeBook").invoke(null);
            Constructor<?> constructor = Class.forName("joebkt.CraftingResultEntry2").getDeclaredConstructor(RecipeBook.getClass());
            constructor.setAccessible(true);
            Collections.sort((List) Class.forName("joebkt.RecipeBook").getMethod("getCraftingResultEntries").invoke(RecipeBook), (Comparator) constructor.newInstance(RecipeBook));
        } catch (Throwable th) {
            MyPlugin.logger.log(Level.WARNING, "addRecipe() failed", th);
            return false;
        }
        return true;
    }

    @Override
    public void banIP(String arg0) {
        MyPlugin.server.executeCommand("ban-ip " + arg0);
    }

    @Override
    public void clearRecipes() {
        MyPlugin.fixme();
    }

    @Override
    public void configureDbConfig(ServerConfig config) {
        Validate.notNull(config, "Config cannot be null");

        DataSourceConfig ds = new DataSourceConfig();
        ds.setDriver(MyPlugin.bridgeConfig.config.getString("database.driver"));
        ds.setUrl(MyPlugin.bridgeConfig.config.getString("database.url"));
        ds.setUsername(MyPlugin.bridgeConfig.config.getString("database.username"));
        ds.setPassword(MyPlugin.bridgeConfig.config.getString("database.password"));
        ds.setIsolationLevel(TransactionIsolation.getLevel(MyPlugin.bridgeConfig.config.getString("database.isolation")));

        if (ds.getDriver().contains("sqlite")) {
            config.setDatabasePlatform(new SQLitePlatform());
            config.getDatabasePlatform().getDbDdlSyntax().setIdentity("");
        }

        config.setDataSourceConfig(ds);
    }

    @Override
    public Inventory createInventory(InventoryHolder arg0, InventoryType arg1) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public Inventory createInventory(InventoryHolder arg0, int arg1) throws IllegalArgumentException {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public Inventory createInventory(InventoryHolder arg0, InventoryType arg1, String arg2) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public Inventory createInventory(InventoryHolder arg0, int arg1, String arg2) throws IllegalArgumentException {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public MapView createMap(World arg0) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public World createWorld(WorldCreator arg0) {
        // I can create a world
        World world = getWorld(arg0.name());
        if (world == null) {
            // no I can't
            MyPlugin.fixme();
        }
        return world;
    }

    @Override
    public boolean getAllowEnd() {
        // rainbow doesn't has a setting for this
        // --> it's allowed
        return true;
    }

    @Override
    public boolean getAllowFlight() {
        MyPlugin.fixme("unable to get property allow-flight");
        // return default
        return false;
    }

    @Override
    public boolean getAllowNether() {
        MyPlugin.fixme("unable to get property allow-nether");
        // return default
        return false;
    }

    @Override
    public int getAmbientSpawnLimit() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int getAnimalSpawnLimit() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public BanList getBanList(Type arg0) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        MyPlugin.fixme();
        return Collections.emptySet();
    }

    @Override
    public Map<String, String[]> getCommandAliases() {
        Map<String, String[]> map = new HashMap<>();
        for (Command c : MyPlugin.commandMap.getCommands()) {
            List<String> alias = c.getAliases();
            String[] s2 = new String[alias.size()];
            for (int i = 0; i < s2.length; i++) {
                s2[i] = alias.get(i);
            }
            map.put(c.getName(), s2);
        }
        return map;
    }

    @Override
    public long getConnectionThrottle() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public GameMode getDefaultGameMode() {
        MyPlugin.fixme("unable to get property gamemode");
        // return default
        return GameMode.SURVIVAL;
    }

    @Override
    public boolean getGenerateStructures() {
        MyPlugin.fixme("unable to get property generate-structures");
        // return default
        return false;
    }

    @Override
    public Set<String> getIPBans() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public int getIdleTimeout() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public ChunkGenerator.ChunkData createChunkData(World world) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public BossBar createBossBar(String s, BarColor barColor, BarStyle barStyle, BarFlag... barFlags) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public String getIp() {
        MyPlugin.fixme();
        return "127.0.0.0";
    }

    @Override
    public ItemFactory getItemFactory() {
        return itemFactory;
    }

    @Override
    public MapView getMap(short arg0) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public int getMaxPlayers() {
        MyPlugin.fixme("unable to get property max-players");
        // return default
        return 20;
    }

    @Override
    public int getMonsterSpawnLimit() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public String getMotd() {
        return server.getServerMOTD() == null ? "MOTD" : server.getServerMOTD();
    }

    @Override
    public String getShutdownMessage() {
        MyPlugin.fixme();
        return "<insert shutdown message here>";
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String arg0) {
        if (arg0.length() > 16) return getOfflinePlayer(UUID.fromString(arg0));
        try {
            return getOfflinePlayer(UUID.fromString(server.getPlayerUUIDFromName(arg0)));
        } catch (Exception e) {
            return getPlayer(arg0);
        }
    }

    @Override
    public OfflinePlayer getOfflinePlayer(UUID arg0) {
        for (OfflinePlayer player : getOfflinePlayers()) {
            if (player.getUniqueId().equals(arg0)) return player;
        }
        return null;
    }

    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        List<MC_Player> mcPlayers = MyPlugin.server.getOfflinePlayers();

        int len = mcPlayers.size();
        OfflinePlayer[] result = new OfflinePlayer[mcPlayers.size()];
        for (int i = 0; i < len; i++) {
            FakeOfflinePlayer plr = new FakeOfflinePlayer(mcPlayers.get(i));
            result[i] = plr;
        }
        return result;
    }

    @Override
    public Set<OfflinePlayer> getOperators() {
        MyPlugin.fixme();
        return new HashSet<>();
    }

    @Override
    public PluginCommand getPluginCommand(String arg) {
        return (PluginCommand) MyPlugin.commandMap.getCommand(arg);
    }

    @Override
    public List<Recipe> getRecipesFor(ItemStack arg0) {
        MyPlugin.fixme();
        return new ArrayList<>();
    }

    @Override
    public ScoreboardManager getScoreboardManager() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public CachedServerIcon getServerIcon() {
        return new FakeServerIcon();
    }

    @Override
    public int getTicksPerAnimalSpawns() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int getTicksPerMonsterSpawns() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public UnsafeValues getUnsafe() {
        MyPlugin.fixme();
        return new UnsafeValues() {
            @Override
            public Material getMaterialFromInternalName(String s) {
                MC_Block block = MyPlugin.server.getBlockFromName(s);
                if (block == null) {
                    MyPlugin.fixme();
                    return null;
                }
                return Material.getMaterial(block.getId());
            }

            @Override
            public List<String> tabCompleteInternalMaterialName(String s, List<String> strings) {
                MyPlugin.fixme();
                return null;
            }

            @Override
            public ItemStack modifyItemStack(ItemStack itemStack, String s) {
                MyPlugin.fixme();
                return null;
            }

            @Override
            public Statistic getStatisticFromInternalName(String s) {
                MyPlugin.fixme();
                return null;
            }

            @Override
            public Achievement getAchievementFromInternalName(String s) {
                MyPlugin.fixme();
                return null;
            }

            @Override
            public List<String> tabCompleteInternalStatisticOrAchievementName(String s, List<String> strings) {
                MyPlugin.fixme();
                return null;
            }
        };
    }

    @Override
    public Spigot spigot() {
        return new Spigot() {
            @Override
            public YamlConfiguration getConfig() {
                MyPlugin.fixme();
                return null;
            }
        };
    }

    @Override
    public int getViewDistance() {
        MyPlugin.fixme("unable to get property view-distance");
        // return default
        return 10;
    }

    @Override
    public WarningState getWarningState() {
        return WarningState.DEFAULT;
    }

    @Override
    public int getWaterAnimalSpawnLimit() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        MyPlugin.fixme();
        return new HashSet<>();
    }

    @Override
    public World getWorld(String worldName) {
        return WorldManager.getWorld(worldName);
    }

    @Override
    public World getWorld(UUID arg0) {
        return WorldManager.getWorld(arg0);
    }

    @Override
    public File getWorldContainer() {
        return new File("./");
    }

    @Override
    public String getWorldType() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public List<World> getWorlds() {
        return WorldManager.getWorlds();
    }

    @Override
    public boolean hasWhitelist() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isHardcore() {
        MyPlugin.fixme("unable to get property hardcore");
        // return default
        return false;
    }

    @Override
    public boolean isPrimaryThread() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public CachedServerIcon loadServerIcon(File arg0) throws IllegalArgumentException, Exception {
        server.setServerIconFilename(arg0.getAbsolutePath());
        return new FakeServerIcon();
    }

    @Override
    public CachedServerIcon loadServerIcon(BufferedImage arg0) throws IllegalArgumentException, Exception {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public Iterator<Recipe> recipeIterator() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void reload() {
        MyPlugin.instance.reload();
    }

    @Override
    public void reloadWhitelist() {
        MyPlugin.fixme();
    }

    @Override
    public void resetRecipes() {
        MyPlugin.fixme();
    }

    @Override
    public void savePlayers() {
        MyPlugin.fixme();
    }

    @Override
    public void setDefaultGameMode(GameMode arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setIdleTimeout(int arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setSpawnRadius(int arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void setWhitelist(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void shutdown() {
        server.executeCommand("stop");
    }

    @Override
    public void unbanIP(String arg0) {
        server.executeCommand("pardon-ip " + arg0);
    }

    @Override
    public boolean unloadWorld(String arg0, boolean arg1) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean unloadWorld(World arg0, boolean arg1) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean useExactLoginLocation() {
        MyPlugin.fixme();
        return false;
    }

}
