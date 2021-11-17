package de.mymiggi.covid.api.manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.mymiggi.covid.api.UniversalHibernateClient;
import de.mymiggi.covid.api.entitys.LandKreis;
import de.mymiggi.covid.api.entitys.rki.landkreis.RKILandKreis;

public class LandKreisManager
{
	private static final UniversalHibernateClient client = new UniversalHibernateClient();
	private List<LandKreis> landKreise = new ArrayList<LandKreis>();
	private Map<String, List<LandKreis>> cache = new HashMap<String, List<LandKreis>>();
	private List<String> landKreisNames = new ArrayList<String>();

	public LandKreisManager()
	{
		List<RKILandKreis> list = client.getList(RKILandKreis.class);
		list.forEach(land -> {
			LandKreis current = new LandKreis(land);
			landKreise.add(current);
			addToCacheMap(current);
		});
	}

	/**
	 * List contains LandKreis-Stats
	 * 
	 * @param landKreis
	 * @param rangeInDays;
	 * @return
	 */
	public List<LandKreis> getLandKreisStats(String landKreis, int rangeInDays)
	{
		if (!cache.containsKey(landKreis.toLowerCase()))
		{
			return new ArrayList<LandKreis>();
		}
		LocalDate today = LocalDate.now();
		LocalDate startDate = LocalDate.now().minusDays(rangeInDays);
		List<LandKreis> responseList = new ArrayList<LandKreis>();
		for (LandKreis temp : cache.get(landKreis.toLowerCase()))
		{
			boolean isInRange = temp.getDate().isAfter(startDate) && temp.getDate().isBefore(today) || temp.getDate().isEqual(startDate) || temp.getDate().isEqual(today);
			if (isInRange)
			{
				responseList.add(temp);
			}
		}
		return sortList(responseList);
	}

	public List<String> getLandKreise()
	{
		return landKreisNames;
	}

	private List<LandKreis> sortList(List<LandKreis> entrys)
	{
		List<LandKreis> response = new ArrayList<LandKreis>();
		Collections.sort(entrys, (l1, l2) -> l1.getDate().compareTo(l2.getDate()));
		entrys.stream()
			.forEach(current -> response.add(current));
		return response;
	}

	private void addToCacheMap(LandKreis current)
	{
		if (cache.containsKey(current.getGEN().toLowerCase()))
		{
			cache.get(current.getGEN().toLowerCase()).add(current);
		}
		else
		{
			List<LandKreis> listToAdd = new ArrayList<LandKreis>();
			listToAdd.add(current);
			cache.put(current.getGEN().toLowerCase(), listToAdd);
			landKreisNames.add(current.getGEN());
		}
	}
}
