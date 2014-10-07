package PluginBukkitBridge;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.lang.UnhandledException;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitWorker;

class FakeAsyncTask extends FakeTask
{

	private final LinkedList<BukkitWorker> m_wrkrs = new LinkedList<BukkitWorker>();
	private final Map<Integer, FakeTask> m_rnrs;

	FakeAsyncTask(final Map<Integer, FakeTask> runners, final Plugin plugin, final Runnable task, final int id, final long delay)
	{
		super(plugin, task, id, delay);
		this.m_rnrs = runners;
	}

	@Override
	public boolean isSync()
	{
		return false;
	}

	@Override
	public void run()
	{
		final Thread thread = Thread.currentThread();
		synchronized (m_wrkrs)
		{
			if (getPeriod() == -2)
			{
				return;
			}
			m_wrkrs.add(new BukkitWorker()
			{
				public Thread getThread()
				{
					return thread;
				}

				public int getTaskId()
				{
					return FakeAsyncTask.this.getTaskId();
				}

				public Plugin getOwner()
				{
					return FakeAsyncTask.this.getOwner();
				}
			});
		}
		Throwable thrown = null;
		try
		{
			super.run();
		}
		catch (final Throwable t)
		{
			thrown = t;
			throw new UnhandledException(String.format("Plugin %s generated an exception while executing task %s", getOwner().getDescription().getFullName(), getTaskId()), thrown);
		}
		finally
		{
			synchronized (m_wrkrs)
			{
				try
				{
					final Iterator<BukkitWorker> workers = this.m_wrkrs.iterator();
					boolean removed = false;
					while (workers.hasNext())
					{
						if (workers.next().getThread() == thread)
						{
							workers.remove();
							removed = true;
							break;
						}
					}
					if (!removed)
					{
						throw new IllegalStateException(String.format("Unable to remove worker %s on task %s for %s", thread.getName(), getTaskId(), getOwner().getDescription()
								.getFullName()), thrown);
					}
				}
				finally
				{
					if (getPeriod() < 0 && m_wrkrs.isEmpty())
					{
						m_rnrs.remove(getTaskId());
					}
				}
			}
		}
	}

	LinkedList<BukkitWorker> getWorkers()
	{
		return m_wrkrs;
	}

	boolean cancel0()
	{
		synchronized (m_wrkrs)
		{
			setPeriod(-2l);
			if (m_wrkrs.isEmpty())
			{
				m_rnrs.remove(getTaskId());
			}
		}
		return true;
	}
}
