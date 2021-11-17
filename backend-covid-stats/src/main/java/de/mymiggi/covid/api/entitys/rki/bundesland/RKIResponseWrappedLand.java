package de.mymiggi.covid.api.entitys.rki.bundesland;

import java.util.ArrayList;
import java.util.List;

public class RKIResponseWrappedLand
{
	private List<Attributes> features = new ArrayList<Attributes>();

	public List<Attributes> getFeaturesList()
	{
		return features;
	}

	public void setFeaturesList(List<Attributes> features)
	{
		this.features = features;
	}
}
