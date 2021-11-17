package de.mymiggi.covid.api.entitys.rki.landkreis;

public class Attributes
{
	private RKILandKreis attributes = new RKILandKreis();

	public RKILandKreis getResponse()
	{
		return attributes;
	}

	public void setResponse(RKILandKreis response)
	{
		this.attributes = response;
	}
}
