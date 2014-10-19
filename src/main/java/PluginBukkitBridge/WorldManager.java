package PluginBukkitBridge;

import PluginReference.MC_World;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by florian on 15.10.14.
 */
public class WorldManager {

    static List<World> worlds = new ArrayList<>();

    public static void refresh(){
        for(int i = -1; i <= 2; i++){
            MC_World world = MyPlugin.server.getWorld(i);
            if(world != null && world.getName() != null && !world.getName().isEmpty()){
                if(!hasWorld(world.getName())){
                    worlds.add(new FakeWorld(world));
                }
            }
        }
    }

    public static boolean hasWorld(String name){
        for(World w: worlds){
            if(w.getName().equals(name))return true;
        }
        return false;
    }

    public static List<World> getWorlds(){
        return new ArrayList<>(worlds);
    }

    public static World getWorld(String name){
        for(World w: worlds){
            if(w.getName().equals(name))return w;
        }
        return null;
    }

    public static World getWorld(UUID arg0) {
        for(World w: getWorlds()){
            if(w.getUID().equals(arg0))return w;
        }
        return null;
    }
}
