package com.luizlealbraga.crawlerapi.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLReader {

	public String getUrlHtml(String inputUrl){
		String outputUrl=null;
		try {
		URL urlObj = new URL(this.formatUrl(inputUrl));
        URLConnection con = urlObj.openConnection();

        con.setDoOutput(true); // we want the response 
        con.setRequestProperty("Cookie", "myCookie=test123");
        con.setConnectTimeout(30000000);
        con.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        String newLine = System.getProperty("line.separator");
        while ((inputLine = in.readLine()) != null)
        {
            response.append(inputLine + newLine);
        }

        in.close();

        outputUrl = response.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
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