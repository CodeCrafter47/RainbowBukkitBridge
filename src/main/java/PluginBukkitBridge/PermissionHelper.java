package PluginBukkitBridge;

import PluginReference.MC_Player;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionHelper
{
    public static Map<Permission, List<Permission>> parents = new HashMap<>();

	public static boolean hasPermission(MC_Player plr, String name)
	{
        boolean result = MyPlugin.server.hasPermission(plr.getName(), name);

        if(result)return true;

        Permission perm = Bukkit.getPluginManager().getPermission(name);

        if(perm == null){
            if(plr.isOp())return true;
            // check regex
            while ((name = getSuper(name)) != null){
                if (MyPlugin.server.hasPermission(plr.getName(), name + ".*"))return true;
            }

        }else{
            // check parents
            for(Permission p: getParents(perm)){
                if(MyPlugin.server.hasPermission(plr.getName(), p.getName()))return true;
                if(p.getDefault().getValue(plr.isOp()))return true;
            }

            // check regex
            while ((name = getSuper(name)) != null){
                if (MyPlugin.server.hasPermission(plr.getName(), name + ".*"))return true;
            }

            // check defaults
            if(perm.getDefault().getValue(plr.isOp()))return true;
        }

        return false;
	}

    private static List<Permission> getParents(Permission perm){
        if(parents.containsKey(perm))return parents.get(perm);

        List <Permission> perms = new ArrayList<>();

        for(Permission perm2 : Bukkit.getPluginManager().getPermissions()){
            Map<String, Boolean> children = perm2.getChildren();
            if(children.containsKey(perm.getName()) && children.get(perm.getName())){
                perms.add(perm2);
            }
        }

        parents.put(perm, perms);

        return perms;
    }

    private static String getSuper(String s){
        int end = s.lastIndexOf('.');
        if(end <= 0)return null;
        return s.substring(0, end);
    }
}
