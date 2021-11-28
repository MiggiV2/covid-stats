package de.mymiggi.covid.api.saver;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SendRequestAction
{
	private OkHttpClient client = new OkHttpClient();
	private static Logger logger = LoggerFactory.getLogger(SendRequestAction.class.getSimpleName());

	public String run(String url) throws IOException
	{
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		logger.info(String.format("Request status: %s -> host: %s", response.code(),request.url().host()));
		return response.body().string();
	}
}
