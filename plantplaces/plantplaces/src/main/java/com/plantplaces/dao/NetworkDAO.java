package com.plantplaces.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class NetworkDAO {

	
	/**
	 * Return the data found at the given endpoint
	 * @param endpoint a URL or other location where we can find data.
	 * @return All of the data returned as one string.
	 * @throws Exception
	 */
	public String request(String endpoint) throws Exception  {
		StringBuilder sb = new StringBuilder();
		
		URL url = new URL(endpoint);
		
		// open a connection to this URL
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		try {
			// read in the bytes
			InputStream inputStream = urlConnection.getInputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			
			// read them as characters.
			InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			// read one line at a time.
			String inputLine = bufferedReader.readLine();
			while (inputLine != null) {
				// add this to our output
				sb.append(inputLine);
				// reading the next line
				inputLine = bufferedReader.readLine();
			}
		} finally {
			urlConnection.disconnect();
		}
		return sb.toString();
			
	}

}
