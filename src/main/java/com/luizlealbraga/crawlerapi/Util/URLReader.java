package com.luizlealbraga.crawlerapi.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

public class URLReader {

	public String getUrlHtml(String inputUrl) throws IOException, InterruptedException {
		String outputUrl = null;

		// Now we need to get the URL and format it if some user have passed it
		// incomplete and create a URL object.
		URL urlObj = new URL(this.formatUrl(inputUrl));

		// Here we open a new connection based on the URL object
		HttpsURLConnection con = (HttpsURLConnection) urlObj.openConnection();
		con.setDoOutput(true); // we want the response
		con.setRequestProperty("Cookie", "myCookie=test123");
		con.setConnectTimeout(0);
		con.connect();

		// So after a amount of connections GITHUB starts to block our IP, so we need to
		// check if the response is 429 or not.
		if (con.getResponseCode() == 429) {
			// If it's 429 we need to loop and wait the time the github tell us to wait.
			do {
				// Here we can get by the Header the field "Retry-After" the amount seconds we
				// need to wait, at the first time is 60 seconds but it can be increased to 1
				// hour and after the block pass we need to open another connection and try
				// again. It's good to say too that when we build thread system it get worse,
				// imagine 10 threads making a connection at the same time when the first get
				// blocked the other 9 will try to connect with a block enabled and it will
				// generate a longer block so i've decided to don't use thread, but i've a
				// version of it.
				TimeUnit.SECONDS.sleep(Integer.parseInt(con.getHeaderField("Retry-After")) + 2);
				con.disconnect();
				urlObj = new URL(this.formatUrl(inputUrl));
				con = (HttpsURLConnection) urlObj.openConnection();
				con.connect();
			} while (con.getResponseCode() == 429);
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		StringBuilder response = new StringBuilder();
		String inputLine;

		//Here we build up the return HTML
		String newLine = System.getProperty("line.separator");
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine + newLine);
		}
		in.close();
		con.disconnect();
		outputUrl = response.toString();

		//Then we return it
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