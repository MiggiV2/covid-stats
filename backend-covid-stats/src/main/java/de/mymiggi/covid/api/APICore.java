package de.mymiggi.covid.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.mymiggi.covid.api.actions.GetBundesLandStatsAction;
import de.mymiggi.covid.api.actions.GetLandKreisStatsAction;
import de.mymiggi.covid.api.manager.BundesLandManager;
import de.mymiggi.covid.api.manager.LandKreisManager;
import de.mymiggi.covid.api.saver.StartSaveThreadAction;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@Path("/covid-stats-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@QuarkusMain
public class APICore
{
	public static final BundesLandManager LAND_MANAGER = new BundesLandManager();
	public static final LandKreisManager KREIS_MANAGER = new LandKreisManager();

	public static void main(String... args)
	{
		if (!StartSaveThreadAction.isRunning())
		{
			StartSaveThreadAction.run();
		}
		Quarkus.run(args);
	}

	@GET
	@Path("get-bundesland-stats")
	public Response getLaenderStats(@QueryParam("id") String land, @QueryParam("range") int rangeInDays)
	{
		return new GetBundesLandStatsAction().run(land, rangeInDays);
	}

	@GET
	@Path("get-landkreis-stats")
	public Response getKreisStats(@QueryParam("id") String land, @QueryParam("range") int rangeInDays)
	{
		return new GetLandKreisStatsAction().run(land, rangeInDays);
	}

	@GET
	@Path("landkreise")
	public Response getLandKreise()
	{
		return Response.ok(KREIS_MANAGER.getLandKreise()).build();
	}
}
