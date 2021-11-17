package de.mymiggi.covid.api.entitys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import de.mymiggi.covid.api.entitys.rki.landkreis.RKILandKreis;

public class LandKreis extends RKILandKreis
{
	private LocalDate date;

	public LandKreis(RKILandKreis landKreis)
	{
		setBL(landKreis.getBL());
		setCases(landKreis.getCases());
		setCases7_bl(landKreis.getCases7_bl());
		setCases7_bl_per_100k(landKreis.getCases7_per_100k());
		setCases7_lk(landKreis.getCases7_lk());
		setCases7_per_100k(landKreis.getCases7_per_100k());
		setCases_per_population(landKreis.getCases_per_population());
		setCounty(landKreis.getCounty());
		setDeath7_bl(landKreis.getDeath7_bl());
		setDeath7_lk(landKreis.getDeath7_lk());
		setDeath_rate(landKreis.getDeath_rate());
		setDeaths(landKreis.getDeaths());
		setEWZ(landKreis.getEWZ());
		setEWZ_BL(landKreis.getEWZ_BL());
		setGEN(landKreis.getGEN());
		setLast_update(landKreis.getLast_update());
		setDate();

	}

	private void setDate()
	{
		if (!getLast_update().isBlank())
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy', 00:00 Uhr'");
			this.date = LocalDate.parse(getLast_update(), formatter);
		}
	}

	public LocalDate getDate()
	{
		return date;
	}

	public void setDate(LocalDate date)
	{
		this.date = date;
	}
}
