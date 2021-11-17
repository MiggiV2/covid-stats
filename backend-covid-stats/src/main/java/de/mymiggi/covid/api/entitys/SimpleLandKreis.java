package de.mymiggi.covid.api.entitys;

public class SimpleLandKreis
{
	private String gen;
	private String genSimple;
	private String BL;

	public SimpleLandKreis()
	{
	}

	public SimpleLandKreis(String gen, String genSimple, String bL)
	{
		this.gen = gen;
		this.genSimple = genSimple;
		this.BL = bL;
	}

	public String getGen()
	{
		return gen;
	}

	public void setGen(String gen)
	{
		this.gen = gen;
	}

	public String getGenSimple()
	{
		return genSimple;
	}

	public void setGenSimple(String genSimple)
	{
		this.genSimple = genSimple;
	}

	public String getBL()
	{
		return BL;
	}

	public void setBL(String bL)
	{
		this.BL = bL;
	}
}
