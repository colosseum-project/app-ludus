package org.colosseumproject.ludus.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.colosseumproject.ludus.exception.APIErrorException;

public class GenericAPI {

	String endpoint;

	public String postWithBody(String urlContext, String requestBody) {
		String responseBody = null;

		try {
			URL url;
			url = new URL(endpoint + urlContext);

			HttpURLConnection con;
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);

			// Send request body
			try (OutputStream os = con.getOutputStream()) {
				byte[] input = requestBody.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			// Digest response body
			try (BufferedReader br = new BufferedReader(
					new InputStreamReader(con.getInputStream(), "utf-8"))) {
				;
				StringBuilder responseStringBuilder = new StringBuilder();
				while ((responseBody = br.readLine()) != null) {
					responseStringBuilder.append(responseBody.trim());
				}
				responseBody = responseStringBuilder.toString();
			} catch (Exception e) {
				throw new APIErrorException("Error with Arena endpoint: " + e.getMessage());
			}
		} catch (Exception e) {
			throw new APIErrorException(e.getMessage());
		}

		return responseBody;
	}

}
