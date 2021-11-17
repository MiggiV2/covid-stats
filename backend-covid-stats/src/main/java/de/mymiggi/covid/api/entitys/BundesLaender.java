package de.mymiggi.covid.api.entitys;

public enum BundesLaender
{
	NORDRHEIN_WESTFALEN
	{
		@Override
		public String getName()
		{
			return "Nordrhein-Westfalen";
		}
	},
	BAYERN
	{
		@Override
		public String getName()
		{
			return "Bayern";
		}
	},
	BADEN_WÜRTTEMBERG
	{
		@Override
		public String getName()
		{
			return "Baden-Württemberg";
		}
	},
	HESSEN
	{
		@Override
		public String getName()
		{
			return "Hessen";
		}
	},
	SACHSEN
	{
		@Override
		public String getName()
		{
			return "Sachsen";
		}
	},
	NIEDERSACHSEN
	{
		@Override
		public String getName()
		{
			return "Niedersachsen";
		}
	},
	BERLIN
	{
		@Override
		public String getName()
		{
			return "Berlin";
		}
	},
	RHEINLAND_PFALZ
	{
		@Override
		public String getName()
		{
			return "Rheinland-Pfalz";
		}
	},
	THÜRINGEN
	{
		@Override
		public String getName()
		{
			return "Thüringen";
		}
	},
	BRANDENBURG
	{
		@Override
		public String getName()
		{
			return "Brandenburg";
		}
	},
	SACHSEN_ANHALT
	{
		@Override
		public String getName()
		{
			return "Sachsen-Anhalt";
		}
	},
	HAMBURG
	{
		@Override
		public String getName()
		{
			return "Hamburg";
		}
	},
	SCHLESWIG_HOLSTEIN
	{
		@Override
		public String getName()
		{
			return "Schleswig-Holstein";
		}
	},
	MECKLENBURG_VORPOMMERN
	{
		@Override
		public String getName()
		{
			return "Mecklenburg-Vorpommern";
		}
	},
	SAARLAND
	{
		@Override
		public String getName()
		{
			return "Saarland";
		}
	},
	BREMEN
	{
		@Override
		public String getName()
		{
			return "Bremen";
		}
	};
	public abstract String getName();
}
