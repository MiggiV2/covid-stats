package de.mymiggi.covid.api;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import de.mymiggi.covid.api.actions.CreateData;

class CreateDataTest
{

	@Test
	void test() throws IOException
	{
		new CreateData().run();
	}
}
