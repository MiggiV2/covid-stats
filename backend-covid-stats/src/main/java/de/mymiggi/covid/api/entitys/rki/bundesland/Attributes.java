package de.mymiggi.covid.api.entitys.rki.bundesland;

public class Attributes
{
	private RKIBundesLand attributes = new RKIBundesLand();

	public RKIBundesLand getResponse()
	{
		return attributes;
	}

	public void setResponse(RKIBundesLand response)
	{
		this.attributes = response;
	}
}