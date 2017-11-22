package PluginBukkitBridge;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

class FakeTask implements BukkitTask, Runnable
{

	private volatile FakeTask next = null;
	private volatile long period;
	private long nextRun;
	private final Runnable task;
	private final Plugin plugin;
	private final int id;

	FakeTask()
	{
		this(null, null, -1, -1);
	}

	FakeTask(final Runnable task)
	{
		this(null, task, -1, -1);
	}

	FakeTask(final Plugin plugin, final Runnable task, final int id, final long period)
	{
		this.plugin = plugin;
		this.task = task;
		this.id = id;
		this.period = period;
	}

	public final int getTaskId()
	{
		return id;
	}

	public final Plugin getOwner()
	{
		return plugin;
	}

	public boolean isSync()
	{
		return true;
	}

	public void run()
	{
		task.run();
	}

	long getPeriod()
	{
		return period;
	}

	void setPeriod(long period)
	{
		this.period = period;
	}

	long getNextRun()
	{
		return nextRun;
	}

	void setNextRun(long nextRun)
	{
		this.nextRun = nextRun;
	}

	FakeTask getNext()
	{
		return next;
	}

	void setNext(FakeTask next)
	{
		this.next = next;
	}

	Class<? extends Runnable> getTaskClass()
	{
		return task.getClass();
	}

	public void cancel()
	{
		Bukkit.getScheduler().cancelTask(id);
	}

	boolean cancel0()
	{
		setPeriod(-2l);
		return true;
	}

    @Override
    public boolean isCancelled() {
        MyPlugin.fixme();
        return false;
    }
}
