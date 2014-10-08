package PluginBukkitBridge;

import java.util.concurrent.ConcurrentHashMap;

import PluginReference.MC_Player;

public class PermissionHelper
{
	public static boolean hasPermission(MC_Player plr, String perm)
	{
		// DBG
		// -----------------------------------------------
		String strName = "?";
		if(plr != null) strName = plr.getName();
		// -----------------------------------------------
		
		boolean res = false;
		if(plr != null)
		{
			if(plr.isOp()) res = true;
			else
			{
				res = MyPlugin.server.hasPermission(strName, perm);
			}
		}
		
		
		//System.out.println(String.format("BukkitBridge -- Evaluating if '%s' has permission '%s': %s", strName, perm, "" + res));
		// -----------------------------------------------
		return res;
		
	}
}
