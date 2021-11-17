package de.mymiggi.covid.api.entitys;

import java.time.LocalDate;

public class Status
{
	private LocalDate date;
	private Double inzidenz;

	public Status(LocalDate date, Double inzidenz)
	{
		this.date = date;
		this.inzidenz = inzidenz;
	}

	public LocalDate getDate()
	{
		return date;
	}

	public void setDate(LocalDate date)
	{
		this.date = date;
	}

	public Double getInzidenz()
	{
		return inzidenz;
	}

	public void setInzidenz(Double inzidenz)
	{
		this.inzidenz = inzidenz;
	}
}
