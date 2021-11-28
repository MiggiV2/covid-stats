package de.mymiggi.covid.api.saver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.google.gson.Gson;

import de.mymiggi.covid.api.APICore;
import de.mymiggi.covid.api.UniversalHibernateClient;
import de.mymiggi.covid.api.entitys.rki.bundesland.RKIBundesLand;
import de.mymiggi.covid.api.entitys.rki.bundesland.RKIResponseWrappedLand;
import de.mymiggi.covid.api.entitys.rki.landkreis.RKILandKreis;
import de.mymiggi.covid.api.entitys.rki.landkreis.RKIResponseWrappedKreis;

public class SaveRequestAction
{
	private static final Gson gson = new Gson();
	private static final UniversalHibernateClient client = new UniversalHibernateClient();
	private static final Logger LOG = Logger.getLogger(SaveRequestAction.class);

	public void run()
	{
		if (!APICore.KREIS_MANAGER.isUpToDate())
		{
			LOG.info("Updating landkreise...");
			updateLandKreise();
		}
		else
		{
			LOG.info("Landkreise are up to date!");
		}
		if (!APICore.LAND_MANAGER.isUpToDate())
		{
			LOG.info("Updating bundeslaender...");
			updateBundesLaender();
		}
		else
		{
			LOG.info("Bundeslaender are up to date!");
		}
	}

	private void updateLandKreise()
	{
		try
		{
			List<RKILandKreis> landKreise = getLandKreise();
			client.saveList(landKreise);
			APICore.KREIS_MANAGER.syncData();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void updateBundesLaender()
	{
		try
		{
			List<RKIBundesLand> bundesLaender = getBundesLaender();
			client.saveList(bundesLaender);
			APICore.LAND_MANAGER.syncData();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private List<RKILandKreis> getLandKreise() throws IOException
	{
		List<RKILandKreis> landKreise = new ArrayList<RKILandKreis>();
		String bundesLaenderJSON = new SendRequestAction().run(URLs.LANDKREISE.getURL());
		RKIResponseWrappedKreis bundesLaenderWrapped = gson.fromJson(bundesLaenderJSON, RKIResponseWrappedKreis.class);
		bundesLaenderWrapped.getFeaturesList()
			.stream()
			.filter(landkreis -> landkreis.getResponse().getLast_update() != null && !landkreis.getResponse().getLast_update().isBlank())
			.forEach(landkreis -> {
				landKreise.add(landkreis.getResponse());
			});
		return landKreise;
	}

	private List<RKIBundesLand> getBundesLaender() throws IOException
	{
		List<RKIBundesLand> bundesLaender = new ArrayList<RKIBundesLand>();
		String bundesLaenderJSON = new SendRequestAction().run(URLs.BUNDESLAENDER.getURL());
		RKIResponseWrappedLand bundesLaenderWrapped = gson.fromJson(bundesLaenderJSON, RKIResponseWrappedLand.class);
		bundesLaenderWrapped.getFeaturesList().forEach(bundesLand -> {
			bundesLaender.add(bundesLand.getResponse());
		});
		return bundesLaender;
	}
}
