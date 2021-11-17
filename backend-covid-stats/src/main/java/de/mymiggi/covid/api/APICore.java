package de.mymiggi.covid.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.mymiggi.covid.api.entitys.Landkreis;
import de.mymiggi.covid.api.entitys.Status;

@Path("/covid-stats-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class APICore
{
	private Landkreis example;
	public APICore()
	{
		List<Status> stats = new ArrayList<Status>();
		stats.add(new Status(LocalDate.now().plusDays(-7), 89.00));
		stats.add(new Status(LocalDate.now().plusDays(-6), 111.00));
		stats.add(new Status(LocalDate.now().plusDays(-5), 121.00));
		stats.add(new Status(LocalDate.now().plusDays(-4), 131.88));
		stats.add(new Status(LocalDate.now().plusDays(-3), 128.20));
		stats.add(new Status(LocalDate.now().plusDays(-2), 151.09));
		stats.add(new Status(LocalDate.now().plusDays(-1), 191.10));
		example = new Landkreis(
			"Paf",
			System.currentTimeMillis() - 1000 * 1000,
			System.currentTimeMillis(),
			stats);
	}

	@GET
	public Response get()
	{
		return Response.ok(example).build();
	}
}
