package de.mymiggi.covid.api.saver;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class StartSaveThreadAction
{
	private static boolean running;

	public static void run()
	{
		Thread thread = new Thread()
		{
			@Override
			public void run()
			{
				while (running)
				{
					new SaveRequestAction().run();
					try
					{
						Thread.sleep(timeTillFive());
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
						running = false;
					}
				}
			}
		};
		running = true;
		thread.start();
	}

	public static void stop()
	{
		running = false;
	}

	public static boolean isRunning()
	{
		return running;
	}

	private static long timeTillFive()
	{
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime morning = LocalDateTime.of(today.getYear(), today.getMonthValue(), today.getDayOfMonth(), 5, 0);
		if (today.getHour() >= 5)
		{
			morning = morning.plusDays(1);
		}
		long morningStamp = morning.toInstant(ZoneOffset.ofHours(0)).toEpochMilli();
		return morningStamp - System.currentTimeMillis();
	}
}
