package PluginBukkitBridge;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.bukkit.plugin.Plugin;

class FakeFuture<T> extends FakeTask implements Future<T>
{

	private final Callable<T> m_callable;
	private T m_value;
	private Exception m_exception = null;

	FakeFuture(final Callable<T> callable, final Plugin plugin, final int id)
	{
		super(plugin, null, id, -1l);
		this.m_callable = callable;
	}

	public synchronized boolean cancel(final boolean mayInterruptIfRunning)
	{
		if (getPeriod() != -1l)
		{
			return false;
		}
		setPeriod(-2l);
		return true;
	}

	public boolean isCancelled()
	{
		return getPeriod() == -2l;
	}

	public boolean isDone()
	{
		final long period = this.getPeriod();
		return period != -1l && period != -3l;
	}

	public T get() throws CancellationException, InterruptedException, ExecutionException
	{
		try
		{
			return get(0, TimeUnit.MILLISECONDS);
		}
		catch (final TimeoutException e)
		{
			throw new Error(e);
		}
	}

	public synchronized T get(long timeout, final TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException
	{
		timeout = unit.toMillis(timeout);
		long period = this.getPeriod();
		long timestamp = timeout > 0 ? System.currentTimeMillis() : 0l;
		while (true)
		{
			if (period == -1l || period == -3l)
			{
				this.wait(timeout);
				period = this.getPeriod();
				if (period == -1l || period == -3l)
				{
					if (timeout == 0l)
					{
						continue;
					}
					timeout += timestamp - (timestamp = System.currentTimeMillis());
					if (timeout > 0)
					{
						continue;
					}
					throw new TimeoutException();
				}
			}
			if (period == -2l)
			{
				throw new CancellationException();
			}
			if (period == -4l)
			{
				if (m_exception == null)
				{
					return m_value;
				}
				throw new ExecutionException(m_exception);
			}
			throw new IllegalStateException("Expected " + -1l + " to " + -4l + ", got " + period);
		}
	}

	@Override
	public void run()
	{
		synchronized (this)
		{
			if (getPeriod() == -2l)
			{
				return;
			}
			setPeriod(-3l);
		}
		try
		{
			m_value = m_callable.call();
		}
		catch (final Exception e)
		{
			m_exception = e;
		}
		finally
		{
			synchronized (this)
			{
				setPeriod(-4l);
				this.notifyAll();
			}
		}
	}

	synchronized boolean cancel0()
	{
		if (getPeriod() != -1l)
		{
			return false;
		}
		setPeriod(-2l);
		notifyAll();
		return true;
	}
}
