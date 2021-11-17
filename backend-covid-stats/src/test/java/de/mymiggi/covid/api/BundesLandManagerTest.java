package de.mymiggi.covid.api;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.mymiggi.covid.api.entitys.BundesLaender;
import de.mymiggi.covid.api.entitys.BundesLand;
import de.mymiggi.covid.api.entitys.LandKreis;

class BundesLandManagerTest
{

	@Test
	void test()
	{
		List<BundesLand> list = APICore.LAND_MANAGER.getBundesLandStats(BundesLaender.BAYERN, 30);
		System.out.println("list:" + list.size());
		List<LandKreis> list2 = APICore.KREIS_MANAGER.getLandKreisStats("Pfaffenhofen a.d. Ilm", 30);
		System.out.println("list2: " + list2.size());
	}
}
