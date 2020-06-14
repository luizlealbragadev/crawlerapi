package com.luizlealbraga.crawlerapi.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

public class URLReader {

	public String getUrlHtml(String inputUrl) throws IOException, InterruptedException {
		System.out.println(inputUrl);
		String outputUrl = null;
		URL urlObj = new URL(this.formatUrl(inputUrl));
		HttpsURLConnection con = (HttpsURLConnection) urlObj.openConnection();

		con.setDoOutput(true); // we want the response
		con.setRequestProperty("Cookie", "myCookie=test123");
		con.setConnectTimeout(30000000);
		con.connect();
		if (con.getResponseCode() == 429) {
			do {
				System.out.println("Waiting to retry after:"+(Integer.parseInt(con.getHeaderField("Retry-After"))+10) + "Seconds");
				TimeUnit.SECONDS.sleep(Integer.parseInt(con.getHeaderField("Retry-After"))+10);
				con.disconnect();
				urlObj = new URL(this.formatUrl(inputUrl));
				con = (HttpsURLConnection) urlObj.openConnection();
				con.connect();
			} while (con.getResponseCode() == 429);
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		StringBuilder response = new StringBuilder();
		String inputLine;

		String newLine = System.getProperty("line.separator");
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine + newLine);
		}

		in.close();
		con.disconnect();
		outputUrl = response.toString();
		return outputUrl;
	}

	private String formatUrl(String inputUrl) {
		String outputUrl = inputUrl;
		if (!inputUrl.contains("www.")) {
			if (!inputUrl.contains("http://") && !inputUrl.contains("https://")) {
				outputUrl = "https://www." + inputUrl;
			} else if (inputUrl.contains("https://") || inputUrl.contains("https://")) {
				if (inputUrl.contains("http://")) {
					outputUrl = "https://www." + inputUrl.replace("http://", "");
				} else if (inputUrl.contains("https://")) {
					outputUrl = "https://www." + inputUrl.replace("https://", "");
				}
			}
		} else if (inputUrl.contains("www.")) {
			if (!inputUrl.contains("http://") && !inputUrl.contains("https://")) {
				outputUrl = "https://" + inputUrl;
			}
		}

		return outputUrl;
	}
}