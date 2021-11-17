package de.mymiggi.covid.api.actions.helper;

import java.util.HashMap;
import java.util.Map;

import de.mymiggi.covid.api.entitys.BundesLaender;

public class GetEnumMapAction
{
	public Map<String, BundesLaender> run()
	{
		Map<String, BundesLaender> enumMap = new HashMap<String, BundesLaender>();
		for (BundesLaender temp : BundesLaender.values())
		{
			enumMap.put(temp.getName().toUpperCase(), temp);
		}
		return enumMap;
	}
}
