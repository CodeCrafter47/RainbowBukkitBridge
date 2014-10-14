package PluginBukkitBridge;

import PluginBukkitBridge.entity.FakePlayer;
import PluginReference.MC_Player;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by florian on 14.10.14.
 */
public class PlayerManager {

    public static Map<UUID, FakePlayer> uuidMap = new HashMap<>();
    public static Map<String, FakePlayer> nameMap = new HashMap<>();

    public static void addPlayer(MC_Player player){
        FakePlayer player1 = new FakePlayer(player);
        uuidMap.put(player1.getUniqueId(), player1);
        nameMap.put(player1.getName(), player1);
        for(Player p: Bukkit.getOnlinePlayers()){
            if(p.getPlayerListName() != null)ReflectionUtil.sendPlayerListItemChangeDisplayName(player, ((FakePlayer)p).player, p.getPlayerListName());
        }
    }

    public static void removePlayer(UUID player){
        FakePlayer player1 = uuidMap.remove(player);
        nameMap.remove(player1.getName());
    }

    public static Player getPlayer(MC_Player player){
        return uuidMap.get(player.getUUID());
    }

    public static Player getPlayer(UUID uuid){
        if(uuidMap.containsKey(uuid))return uuidMap.get(uuid);
        return null;
    }

    public static Player getPlayer(String name){
        if(nameMap.containsKey(name))return nameMap.get(name);
        return null;
    }
}
