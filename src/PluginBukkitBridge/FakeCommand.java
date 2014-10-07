package PluginBukkitBridge;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class FakeCommand extends Command
{

	public static void FakeDebug(String msg)
	{
		System.out.println("FakeCommand Proxy: " + msg);
	}
	
	protected FakeCommand(String name)
	{
		super(name);
	}

	@Override
	public boolean execute(CommandSender arg0, String arg1, String[] arg2)
	{
		FakeDebug("execute");
		return false;
	}

}
