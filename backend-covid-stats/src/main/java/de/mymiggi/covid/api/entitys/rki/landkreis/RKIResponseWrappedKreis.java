package de.mymiggi.covid.api.entitys.rki.landkreis;

import java.util.ArrayList;
import java.util.List;

public class RKIResponseWrappedKreis
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
