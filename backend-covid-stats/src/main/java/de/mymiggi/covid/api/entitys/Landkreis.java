package de.mymiggi.covid.api.entitys;

import java.util.List;

public class Landkreis
{
	private String name;
	private long beging;
	private long end;
	private List<Status> stats;

	public Landkreis(String name, long beging, long end, List<Status> stats)
	{
		this.name = name;
		this.beging = beging;
		this.end = end;
		this.stats = stats;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public long getBeging()
	{
		return beging;
	}

	public void setBeging(long beging)
	{
		this.beging = beging;
	}

	public long getEnd()
	{
		return end;
	}

	public void setEnd(long end)
	{
		this.end = end;
	}

	public List<Status> getStats()
	{
		return stats;
	}

	public void setStats(List<Status> stats)
	{
		this.stats = stats;
	}
}
