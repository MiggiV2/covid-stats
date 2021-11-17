package de.mymiggi.covid.api.entitys;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import de.mymiggi.covid.api.entitys.rki.bundesland.RKIBundesLand;

public class BundesLand extends RKIBundesLand
{
	private LocalDate date;

	public BundesLand()
	{
		setDate();
	}

	public BundesLand(RKIBundesLand bundesLand)
	{
		setAktualisierung(bundesLand.getAktualisierung());
		setCases7_bl(bundesLand.getCases7_bl());
		setCases7_bl_per_100k(bundesLand.getCases7_bl_per_100k());
		setDeath(bundesLand.getDeath());
		setDeath7_bl(getCases7_bl());
		setFallzahl(bundesLand.getFallzahl());
		setLAN_ew_GEN(bundesLand.getBundesLand());
		setDate();
	}

	public LocalDate getDate()
	{
		return date;
	}

	public void setDate(LocalDate date)
	{
		this.date = date;
	}

	private void setDate()
	{
		Instant instant = Instant.ofEpochMilli(getAktualisierung());
		this.date = LocalDate.ofInstant(instant, ZoneId.of("Europe/Paris"));
	}
}
