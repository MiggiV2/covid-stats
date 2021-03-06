package de.mymiggi.covid.api.manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.mymiggi.covid.api.UniversalHibernateClient;
import de.mymiggi.covid.api.actions.helper.GetEnumMapAction;
import de.mymiggi.covid.api.entitys.BundesLaender;
import de.mymiggi.covid.api.entitys.BundesLand;
import de.mymiggi.covid.api.entitys.rki.bundesland.RKIBundesLand;

public class BundesLandManager
{
	private static final UniversalHibernateClient client = new UniversalHibernateClient();
	private List<BundesLand> laenderList = new ArrayList<BundesLand>();
	private Map<BundesLaender, List<BundesLand>> cache = new HashMap<BundesLaender, List<BundesLand>>();
	private Map<String, BundesLaender> enumMap = new GetEnumMapAction().run();

	public BundesLandManager()
	{
		syncData();
	}

	/**
	 * List contains BundesLand-Stats
	 * 
	 * @param land
	 * @param rangeInDays;
	 * @return
	 */
	public List<BundesLand> getBundesLandStats(BundesLaender land, int rangeInDays)
	{
		LocalDate today = LocalDate.now();
		LocalDate startDate = LocalDate.now().minusDays(rangeInDays);
		List<BundesLand> responseList = new ArrayList<BundesLand>();
		for (BundesLand temp : cache.get(land))
		{
			boolean isInRange = temp.getDate().isAfter(startDate) && temp.getDate().isBefore(today) || temp.getDate().isEqual(startDate) || temp.getDate().isEqual(today);
			if (isInRange)
			{
				responseList.add(temp);
			}
		}
		// responseList.forEach(temp -> System.out.println(temp.getDate()));
		return sortList(responseList);
	}

	public void syncData()
	{
		List<RKIBundesLand> list = client.getList(RKIBundesLand.class);
		laenderList.clear();
		cache.clear();
		list.forEach(land -> {
			BundesLand current = new BundesLand(land);
			laenderList.add(current);
			addToCacheMap(current, enumMap.get(current.getBundesLand().toUpperCase()));
		});
	}

	public boolean isUpToDate()
	{
		LocalDate yesterday = LocalDate.now().minusDays(1);
		List<BundesLand> lastItems = new ArrayList<BundesLand>();
		cache.values().stream()
			.filter(list -> !list.isEmpty())
			.forEach(list -> lastItems.add(sortList(list).get(list.size() - 1)));
		for (BundesLand temp : lastItems)
		{
			if (temp.getDate().isAfter(yesterday))
			{
				return true;
			}
		}
		return false;
	}

	private List<BundesLand> sortList(List<BundesLand> entrys)
	{
		List<BundesLand> response = new ArrayList<BundesLand>();
		Collections.sort(entrys, (l1, l2) -> l1.getDate().compareTo(l2.getDate()));
		entrys.stream()
			.forEach(current -> response.add(current));
		return response;
	}

	private void addToCacheMap(BundesLand current, BundesLaender bundesLand)
	{
		if (cache.containsKey(bundesLand))
		{
			cache.get(bundesLand).add(current);
		}
		else
		{
			List<BundesLand> listToAdd = new ArrayList<BundesLand>();
			listToAdd.add(current);
			cache.put(bundesLand, listToAdd);
		}
	}
}
