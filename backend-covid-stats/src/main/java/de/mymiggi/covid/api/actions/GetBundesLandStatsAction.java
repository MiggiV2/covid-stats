package de.mymiggi.covid.api.actions;

import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.mymiggi.covid.api.APICore;
import de.mymiggi.covid.api.actions.helper.GetEnumMapAction;
import de.mymiggi.covid.api.entitys.BundesLaender;

public class GetBundesLandStatsAction
{
	public Response run(String land, int rangeInDays)
	{
		Map<String, BundesLaender> enumMap = new GetEnumMapAction().run();
		if (!enumMap.containsKey(land.toUpperCase()))
		{
			return Response.status(Status.BAD_REQUEST).entity("Cant find your bundesland!").build();
		}
		if (rangeInDays < 4)
		{
			return Response.status(Status.BAD_REQUEST).entity("Min. range is 4! -> range=4").build();
		}
		BundesLaender bundesLand = enumMap.get(land.toUpperCase());
		return Response.ok(APICore.LAND_MANAGER.getBundesLandStats(bundesLand, rangeInDays)).build();
	}
}
