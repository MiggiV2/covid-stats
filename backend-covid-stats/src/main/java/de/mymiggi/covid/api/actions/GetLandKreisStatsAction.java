package de.mymiggi.covid.api.actions;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.mymiggi.covid.api.APICore;
import de.mymiggi.covid.api.entitys.LandKreis;

public class GetLandKreisStatsAction
{
	public Response run(String landKreis, int rangeInDays)
	{
		if (rangeInDays < 4)
		{
			return Response.status(Status.BAD_REQUEST).entity("Min. range is 4! -> range=4").build();
		}
		List<LandKreis> response = APICore.KREIS_MANAGER.getLandKreisStats(landKreis, rangeInDays);
		return response.isEmpty()
			? Response.noContent().entity(response).build()
			: Response.ok(response).build();
	}
}
