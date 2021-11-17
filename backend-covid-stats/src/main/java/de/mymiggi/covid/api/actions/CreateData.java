package de.mymiggi.covid.api.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jboss.logging.Logger;

import com.google.gson.Gson;

import de.mymiggi.covid.api.UniversalHibernateClient;
import de.mymiggi.covid.api.entitys.rki.bundesland.RKIBundesLand;
import de.mymiggi.covid.api.entitys.rki.bundesland.RKIResponseWrappedLand;
import de.mymiggi.covid.api.entitys.rki.landkreis.RKILandKreis;
import de.mymiggi.covid.api.entitys.rki.landkreis.RKIResponseWrappedKreis;

public class CreateData
{
	private static final UniversalHibernateClient client = new UniversalHibernateClient();
	private static final Logger LOG = Logger.getLogger(CreateData.class.getSimpleName());
	private static final String root = System.getProperty("user.dir");
	private static final Gson gson = new Gson();

	public void run() throws IOException
	{
		File rootFolder = new File(root + "/2021");
		if (!rootFolder.isDirectory())
		{
			throw new IOException("Folder not found! -> " + rootFolder.getAbsolutePath());
		}
		File[] monthDirs = rootFolder.listFiles();
		List<RKILandKreis> landLreise = new ArrayList<RKILandKreis>();
		List<RKIBundesLand> bundesLaender = new ArrayList<RKIBundesLand>();
		for (File month : monthDirs)
		{
			LOG.info("Building month " + month.getName());
			File[] days = month.listFiles();
			for (File dayFile : days)
			{
				if (dayFile.getName().toLowerCase().contains("landkreise"))
				{
					buildLandkreis(landLreise, dayFile);
				}
				else
				{
					buildBundesLand(bundesLaender, dayFile);
				}
				System.out.print(".");
			}
			System.out.println(System.lineSeparator());
			LOG.info("Status landLreise:" + landLreise.size() + " bundesLaender:" + bundesLaender.size());
		}
		client.saveList(landLreise);
		client.saveList(bundesLaender);
		LOG.info("Done!");
	}

	private void buildLandkreis(List<RKILandKreis> landkreise, File dayFile)
	{
		String data = read(dayFile);
		RKIResponseWrappedKreis responseWrapped = gson.fromJson(data, RKIResponseWrappedKreis.class);
		responseWrapped.getFeaturesList().stream()
			.filter(landkreis -> !landkreis.getResponse().getLast_update().isBlank())
			.forEach(landkreis -> {
				landkreise.add(landkreis.getResponse());
			});
	}

	private void buildBundesLand(List<RKIBundesLand> bundesLaender, File dayFile)
	{
		String data = read(dayFile);
		RKIResponseWrappedLand responseWrapped = gson.fromJson(data, RKIResponseWrappedLand.class);
		responseWrapped.getFeaturesList().forEach(bundesland -> {
			bundesLaender.add(bundesland.getResponse());
		});
	}

	private String read(File file)
	{
		String json = "";
		try
		{
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine())
			{
				String data = myReader.nextLine();
				json += data;
			}
			myReader.close();
		}
		catch (FileNotFoundException e)
		{
			LOG.error("An error occurred!");
		}
		return json;
	}
}
