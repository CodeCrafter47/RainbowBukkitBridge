package PluginBukkitBridge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

import org.apache.commons.lang.Validate;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;

public class FakeScheduler implements BukkitScheduler
{

	private final AtomicInteger m_ids = new AtomicInteger(1);
	private volatile FakeTask m_hed = new FakeTask();
	private final AtomicReference<FakeTask> m_tale = new AtomicReference<FakeTask>(m_hed);
	private final PriorityQueue<FakeTask> m_pndg = new PriorityQueue<FakeTask>(10, new Comparator<FakeTask>()
	{
		public int compare(final FakeTask o1, final FakeTask o2)
		{
			return (int) (o1.getNextRun() - o2.getNextRun());
		}
	});
	private final List<FakeTask> m_tmp = new ArrayList<FakeTask>();
	private final ConcurrentHashMap<Integer, FakeTask> runners = new ConcurrentHashMap<Integer, FakeTask>();
	private volatile int m_curTick = -1;
	private final Executor executor = Executors.newCachedThreadPool();
	private static final int RCT_TKS;


	/*
	 * public static void FakeDebug(String msg) {
	 * System.out.println("FakeScheduler Proxy: " + msg); }
	 */

	public int scheduleSyncDelayedTask(final Plugin plugin, final Runnable task)
	{
		// FakeDebug("scheduleSyncDelayedTask");
		return this.scheduleSyncDelayedTask(plugin, task, 0l);
	}

	public BukkitTask runTask(Plugin plugin, Runnable runnable)
	{
		// FakeDebug("runTask");
		return runTaskLater(plugin, runnable, 0l);
	}

	public int scheduleAsyncDelayedTask(final Plugin plugin, final Runnable task)
	{
		// FakeDebug("scheduleAsyncDelayedTask");
		return this.scheduleAsyncDelayedTask(plugin, task, 0l);
	}

	public BukkitTask runTaskAsynchronously(Plugin plugin, Runnable runnable)
	{
		// FakeDebug("runTaskAsynchronously");
		return runTaskLaterAsynchronously(plugin, runnable, 0l);
	}

	public int scheduleSyncDelayedTask(final Plugin plugin, final Runnable task, final long delay)
	{
		// FakeDebug("scheduleSyncDelayedTask");
		return this.scheduleSyncRepeatingTask(plugin, task, delay, -1l);
	}

	public BukkitTask runTaskLater(Plugin plugin, Runnable runnable, long delay)
	{
		// FakeDebug("runTaskLater");
		return runTaskTimer(plugin, runnable, delay, -1l);
	}

	public int scheduleAsyncDelayedTask(final Plugin plugin, final Runnable task, final long delay)
	{
		// FakeDebug("scheduleAsyncDelayedTask");
		return this.scheduleAsyncRepeatingTask(plugin, task, delay, -1l);
	}

	public BukkitTask runTaskLaterAsynchronously(Plugin plugin, Runnable runnable, long delay)
	{
		// FakeDebug("runTaskLaterAsynchronously");
		return runTaskTimerAsynchronously(plugin, runnable, delay, -1l);
	}

	public int scheduleSyncRepeatingTask(final Plugin plugin, final Runnable runnable, long delay, long period)
	{
		// FakeDebug("scheduleSyncRepeatingTask");
		return runTaskTimer(plugin, runnable, delay, period).getTaskId();
	}

	public BukkitTask runTaskTimer(Plugin plugin, Runnable runnable, long delay, long period)
	{
		// FakeDebug("runTaskTimer");
		validate(plugin, runnable);
		if (delay < 0l)
		{
			delay = 0;
		}
		if (period == 0l)
		{
			period = 1l;
		}
		else if (period < -1l)
		{
			period = -1l;
		}
		return handle(new FakeTask(plugin, runnable, nextId(), period), delay);
	}

	public int scheduleAsyncRepeatingTask(final Plugin plugin, final Runnable runnable, long delay, long period)
	{
		// FakeDebug("scheduleAsyncRepeatingTask");
		return runTaskTimerAsynchronously(plugin, runnable, delay, period).getTaskId();
	}

	public BukkitTask runTaskTimerAsynchronously(Plugin plugin, Runnable runnable, long delay, long period)
	{
		// FakeDebug("runTaskTimerAsynchronously");
		validate(plugin, runnable);
		if (delay < 0l)
		{
			delay = 0;
		}
		if (period == 0l)
		{
			period = 1l;
		}
		else if (period < -1l)
		{
			period = -1l;
		}
		return handle(new FakeAsyncTask(runners, plugin, runnable, nextId(), period), delay);
	}

	public <T> Future<T> callSyncMethod(final Plugin plugin, final Callable<T> task)
	{
		// FakeDebug("callSyncMethod");
		validate(plugin, task);
		final FakeFuture<T> future = new FakeFuture<T>(task, plugin, nextId());
		handle(future, 0l);
		return future;
	}

	public void cancelTask(final int taskId)
	{
		// FakeDebug("cancelTask");
		if (taskId <= 0)
		{
			return;
		}
		FakeTask task = runners.get(taskId);
		if (task != null)
		{
			task.cancel0();
		}
		task = new FakeTask(new Runnable()
		{
			public void run()
			{
				if (!check(FakeScheduler.this.m_tmp))
				{
					check(FakeScheduler.this.m_pndg);
				}
			}

			private boolean check(final Iterable<FakeTask> collection)
			{
				final Iterator<FakeTask> tasks = collection.iterator();
				while (tasks.hasNext())
				{
					final FakeTask task = tasks.next();
					if (task.getTaskId() == taskId)
					{
						task.cancel0();
						tasks.remove();
						if (task.isSync())
						{
							runners.remove(taskId);
						}
						return true;
					}
				}
				return false;
			}
		});
		handle(task, 0l);
		for (FakeTask taskPending = m_hed.getNext(); taskPending != null; taskPending = taskPending.getNext())
		{
			if (taskPending == task)
			{
				return;
			}
			if (taskPending.getTaskId() == taskId)
			{
				taskPending.cancel0();
			}
		}
	}

	public void cancelTasks(final Plugin plugin)
	{
		// FakeDebug("cancelTasks");
		Validate.notNull(plugin, "Cannot cancel tasks of null plugin");
		final FakeTask task = new FakeTask(new Runnable()
		{
			public void run()
			{
				check(FakeScheduler.this.m_pndg);
				check(FakeScheduler.this.m_tmp);
			}

			void check(final Iterable<FakeTask> collection)
			{
				final Iterator<FakeTask> tasks = collection.iterator();
				while (tasks.hasNext())
				{
					final FakeTask task = tasks.next();
					if (task.getOwner().equals(plugin))
					{
						task.cancel0();
						tasks.remove();
						if (task.isSync())
						{
							runners.remove(task.getTaskId());
						}
					}
				}
			}
		});
		handle(task, 0l);
		for (FakeTask taskPending = m_hed.getNext(); taskPending != null; taskPending = taskPending.getNext())
		{
			if (taskPending == task)
			{
				return;
			}
			if (taskPending.getTaskId() != -1 && taskPending.getOwner().equals(plugin))
			{
				taskPending.cancel0();
			}
		}
		for (FakeTask runner : runners.values())
		{
			if (runner.getOwner().equals(plugin))
			{
				runner.cancel0();
			}
		}
	}

	public void cancelAllTasks()
	{
		// FakeDebug("cancelAllTasks");
		final FakeTask task = new FakeTask(new Runnable()
		{
			public void run()
			{
				Iterator<FakeTask> it = FakeScheduler.this.runners.values().iterator();
				while (it.hasNext())
				{
					FakeTask task = it.next();
					task.cancel0();
					if (task.isSync())
					{
						it.remove();
					}
				}
				FakeScheduler.this.m_pndg.clear();
				FakeScheduler.this.m_tmp.clear();
			}
		});
		handle(task, 0l);
		for (FakeTask taskPending = m_hed.getNext(); taskPending != null; taskPending = taskPending.getNext())
		{
			if (taskPending == task)
			{
				break;
			}
			taskPending.cancel0();
		}
		for (FakeTask runner : runners.values())
		{
			runner.cancel0();
		}
	}

	public boolean isCurrentlyRunning(final int taskId)
	{
		// FakeDebug("isCurrentlyRunning");
		final FakeTask task = runners.get(taskId);
		if (task == null || task.isSync())
		{
			return false;
		}
		final FakeAsyncTask asyncTask = (FakeAsyncTask) task;
		synchronized (asyncTask.getWorkers())
		{
			return asyncTask.getWorkers().isEmpty();
		}
	}

	public boolean isQueued(final int taskId)
	{
		// FakeDebug("isQueued");
		if (taskId <= 0)
		{
			return false;
		}
		for (FakeTask task = m_hed.getNext(); task != null; task = task.getNext())
		{
			if (task.getTaskId() == taskId)
			{
				return task.getPeriod() >= -1l;
			}
		}
		FakeTask task = runners.get(taskId);
		return task != null && task.getPeriod() >= -1l;
	}

	public List<BukkitWorker> getActiveWorkers()
	{
		// FakeDebug("getActiveWorkers");
		final ArrayList<BukkitWorker> workers = new ArrayList<BukkitWorker>();
		for (final FakeTask taskObj : runners.values())
		{
			if (taskObj.isSync())
			{
				continue;
			}
			final FakeAsyncTask task = (FakeAsyncTask) taskObj;
			synchronized (task.getWorkers())
			{
				workers.addAll(task.getWorkers());
			}
		}
		return workers;
	}

	public List<BukkitTask> getPendingTasks()
	{
		// FakeDebug("getPendingTasks");
		final ArrayList<FakeTask> truePending = new ArrayList<FakeTask>();
		for (FakeTask task = m_hed.getNext(); task != null; task = task.getNext())
		{
			if (task.getTaskId() != -1)
			{
				truePending.add(task);
			}
		}

		final ArrayList<BukkitTask> pending = new ArrayList<BukkitTask>();
		for (FakeTask task : runners.values())
		{
			if (task.getPeriod() >= -1l)
			{
				pending.add(task);
			}
		}

		for (final FakeTask task : truePending)
		{
			if (task.getPeriod() >= -1l && !pending.contains(task))
			{
				pending.add(task);
			}
		}
		return pending;
	}

	public void mainThreadHeartbeat(final int currentTick)
	{
		// FakeDebug("mainThreadHeartbeat");
		this.m_curTick = currentTick;
		final List<FakeTask> temp = this.m_tmp;
		parsePending();
		while (isReady(currentTick))
		{
			final FakeTask task = m_pndg.remove();
			if (task.getPeriod() < -1l)
			{
				if (task.isSync())
				{
					runners.remove(task.getTaskId(), task);
				}
				parsePending();
				continue;
			}
			if (task.isSync())
			{
				try
				{
					task.run();
				}
				catch (final Throwable throwable)
				{
					task.getOwner()
							.getLogger()
							.log(Level.WARNING, String.format("Task #%s for %s generated an exception", task.getTaskId(), task.getOwner().getDescription().getFullName()),
									throwable);
				}
				parsePending();
			}
			else
			{
				executor.execute(task);
			}
			final long period = task.getPeriod(); // State consistency
			if (period > 0)
			{
				task.setNextRun(currentTick + period);
				temp.add(task);
			}
			else if (task.isSync())
			{
				runners.remove(task.getTaskId());
			}
		}
		m_pndg.addAll(temp);
		temp.clear();
	}

	private void addTask(final FakeTask task)
	{
		// FakeDebug("addTask");
		final AtomicReference<FakeTask> tail = this.m_tale;
		FakeTask tailTask = tail.get();
		while (!tail.compareAndSet(tailTask, task))
		{
			tailTask = tail.get();
		}
		tailTask.setNext(task);
	}

	private FakeTask handle(final FakeTask task, final long delay)
	{
		// FakeDebug("handle");
		task.setNextRun(m_curTick + delay);
		addTask(task);
		return task;
	}

	private static void validate(final Plugin plugin, final Object task)
	{
		// FakeDebug("validate");
		Validate.notNull(plugin, "Plugin cannot be null");
		Validate.notNull(task, "Task cannot be null");
		if (!plugin.isEnabled())
		{
			throw new IllegalPluginAccessException("Plugin attempted to register task while disabled");
		}
	}

	private int nextId()
	{
		// FakeDebug("nextId");
		return m_ids.incrementAndGet();
	}

	private void parsePending()
	{
		FakeTask head = this.m_hed;
		FakeTask task = head.getNext();
		FakeTask lastTask = head;
		for (; task != null; task = (lastTask = task).getNext())
		{
			if (task.getTaskId() == -1)
			{
				task.run();
			}
			else if (task.getPeriod() >= -1l)
			{
				m_pndg.add(task);
				runners.put(task.getTaskId(), task);
			}
		}
		for (task = head; task != lastTask; task = head)
		{
			head = task.getNext();
			task.setNext(null);
		}
		this.m_hed = lastTask;
	}

	private boolean isReady(final int currentTick)
	{
		return !m_pndg.isEmpty() && m_pndg.peek().getNextRun() <= currentTick;
	}

	@Override
	public String toString()
	{
		int debugTick = m_curTick;
		StringBuilder string = new StringBuilder("Recent tasks from ").append(debugTick - RCT_TKS).append('-').append(debugTick).append('{');
		return string.append('}').toString();
	}

	static
	{
		RCT_TKS = 30;
	}
	
}
