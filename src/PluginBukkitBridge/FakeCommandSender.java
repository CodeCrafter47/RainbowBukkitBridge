package PluginBukkitBridge;

import java.util.Set;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import PluginReference.MC_Player;


public class FakeCommandSender extends FakePlayer
{
	public MC_Player m_player = null;
	
	public FakeCommandSender(MC_Player argPlr)
	{
		super(argPlr);
		m_player = argPlr;
	}
	public static void FakeDebug(String msg)
	{
		System.out.println("FakeCommandSender Proxy: " + msg);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0)
	{
		FakeDebug("addAttachment");
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, int arg1)
	{
		FakeDebug("addAttachment");
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2)
	{
		FakeDebug("addAttachment");
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2, int arg3)
	{
		FakeDebug("addAttachment");
		return null;
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions()
	{
		FakeDebug("getEffectivePermissions");
		return null;
	}

	@Override
	public boolean hasPermission(String arg0)
	{
		return PermissionHelper.hasPermission(m_player,  arg0);
	}

	@Override
	public boolean hasPermission(Permission arg0)
	{
		FakeDebug("hasPermission");
		return false;
	}

	@Override
	public boolean isPermissionSet(String arg0)
	{
		FakeDebug("isPermissionSet");
		return false;
	}

	@Override
	public boolean isPermissionSet(Permission arg0)
	{
		FakeDebug("isPermissionSet");
		return false;
	}

	@Override
	public void recalculatePermissions()
	{
		FakeDebug("recalculatePermissions");
	}

	@Override
	public void removeAttachment(PermissionAttachment arg0)
	{
		FakeDebug("removeAttachment");
	}

	@Override
	public boolean isOp()
	{
		return m_player.isOp();
	}

	@Override
	public void setOp(boolean arg0)
	{
		FakeDebug("setOp");
	}

	@Override
	public String getName()
	{
		return m_player.getName();
	}

	@Override
	public Server getServer()
	{
		return MyPlugin.fakeServer;
	}

	@Override
	public void sendMessage(String arg0)
	{
		m_player.sendMessage(arg0);
	}

	@Override
	public void sendMessage(String[] arg0)
	{
		FakeDebug("sendMessage");
		
	}

}
