package PluginBukkitBridge;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class FakeConsoleCommandSender implements ConsoleCommandSender
{
	public static void FakeDebug(String msg)
	{
		System.out.println("FakeConsoleCommandSender Proxy: " + msg);
	}

	@Override
	public String getName()
	{
		
		return "BridgeConsole";
	}

	@Override
	public Server getServer()
	{
		return MyPlugin.fakeServer;
	}

	@Override
	public void sendMessage(String arg0)
	{
		System.out.println("[BridgeConsole] " + ChatColor.stripColor(arg0));
	}

	@Override
	public void sendMessage(String[] arg0)
	{
		for(String piece : arg0) sendMessage(piece);
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
		return true;
	}

	@Override
	public boolean hasPermission(Permission arg0)
	{
		return true;
	}

	@Override
	public boolean isPermissionSet(String arg0)
	{
		return true;
	}

	@Override
	public boolean isPermissionSet(Permission arg0)
	{
		return true;
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
		return true;
	}

	@Override
	public void setOp(boolean arg0)
	{
		FakeDebug("setOp");
	}

	@Override
	public void abandonConversation(Conversation arg0)
	{
		FakeDebug("abandonConversation");
	}

	@Override
	public void abandonConversation(Conversation arg0, ConversationAbandonedEvent arg1)
	{
		FakeDebug("abandonConversation");
	}

	@Override
	public void acceptConversationInput(String arg0)
	{
		FakeDebug("acceptConversationInput");
	}

	@Override
	public boolean beginConversation(Conversation arg0)
	{
		FakeDebug("beginConversation");
		return false;
	}

	@Override
	public boolean isConversing()
	{
		FakeDebug("isConversing");
		return false;
	}

	@Override
	public void sendRawMessage(String arg0)
	{
		FakeDebug("sendRawMessage");
	}

}
