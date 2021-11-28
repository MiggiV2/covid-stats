package de.mymiggi.covid.api.saver;

public enum URLs
{
	BUNDESLAENDER
	{
		@Override
		public String getURL()
		{
			return "https://services7.arcgis.com/mOBPykOjAyBO2ZKk/arcgis/rest/services/Coronaf%C3%A4lle_in_den_Bundesl%C3%A4ndern/FeatureServer/0/query?where=1%3D1&outFields=Fallzahl,Aktualisierung,faelle_100000_EW,Death,cases7_bl_per_100k,cases7_bl,death7_bl,LAN_ew_GEN&returnGeometry=false&returnDistinctValues=true&outSR=4326&f=json";
		}
	},
	LANDKREISE
	{

		@Override
		public String getURL()
		{
			return "https://services7.arcgis.com/mOBPykOjAyBO2ZKk/arcgis/rest/services/RKI_Landkreisdaten/FeatureServer/0/query?where=1%3D1&outFields=EWZ,death_rate,cases,deaths,cases_per_population,BL,county,last_update,cases7_per_100k,EWZ_BL,death7_bl,cases7_lk,death7_lk,cases7_per_100k_txt,RS,cases7_bl_per_100k,cases7_bl,GEN&returnGeometry=false&returnDistinctValues=true&outSR=4326&f=json";
		}
	};

	abstract public String getURL();
}
