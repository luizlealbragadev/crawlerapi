package com.luizlealbraga.crawlerapi.Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import com.google.gson.GsonBuilder;
import com.luizlealbraga.crawlerapi.Model.CapturedData;
import com.luizlealbraga.crawlerapi.Model.CapturedDataDto;
import com.luizlealbraga.crawlerapi.Util.DataSizeManipulator;
import com.luizlealbraga.crawlerapi.Util.URLReader;

public class CrawlerCrontoller {
	HashMap<String, CapturedDataDto> map = new HashMap<String, CapturedDataDto>();
	DataSizeManipulator dataSizeManipulator = new DataSizeManipulator();

	public JSONObject crawGithub(JSONObject json) throws IOException, InterruptedException {
		// At this point from the json we can extract some important informations as the
		// URL and what format we should return.
		String inputUrl = json.optString("url");
		String responseSizeFormat = json.optString("responseSizeFormat");

		// We call the crawl function passing these two parameters
		this.runCrawl(inputUrl, responseSizeFormat);
		String mapJson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this.map);

		// Here we create a return JSONobject and start to build it with all the
		// information that we got.
		JSONObject returnJson = new JSONObject();
		returnJson.put("sizeFormat", new DataSizeManipulator().formatType(responseSizeFormat));
		returnJson.put("data", new JSONObject(mapJson));

		// Then return it to the API class for then return to the user.
		return returnJson;
	}

	public void runCrawl(String inputUrl, String responseSizeFormat) throws IOException, InterruptedException {
		// Here we use a class that i've created to manipulate the URL and return a
		// String with the content of the HTML page.
		URLReader urlReader = new URLReader();
		String initialUrlContent = urlReader.getUrlHtml(inputUrl);

		// With the page received we can now search on it with REGEX expression for a
		// part of the page where we can see the documents that we are looking for, in
		// this case it's a list of href
		Pattern pattern = Pattern.compile("(css-truncate css-truncate-target.*\\\")/.*?(?=\\\")");
		Matcher matcher = pattern.matcher(initialUrlContent);
		List<String> listMatches = new ArrayList<String>();
		while (matcher.find()) {
			listMatches.add(matcher.group(0));
		}

		// After manipulating the string to search it we can now see if we found any
		// href, if we did it means that we will need to navigate more deep down the
		// folders and files but if we don't find it means that we are already at the
		// file page and we can extract the data.
		if (listMatches.isEmpty()) {
			// So at this point we are at the data page and we need to call some functions
			// that i've made to get the data on the page and return as a CapturedData
			// object.
			CapturedData capturedData = this.returnCapturedData(initialUrlContent, inputUrl, responseSizeFormat);

			// Now we create a CapturedDataDto to receive the data of capturedData
			CapturedDataDto capturedDataDto;

			// If the map contains the extension name it means that extension already exists
			// at the map so we only need to get the object memory reference to add the
			// values otherwise we need to create a new object to the extension set to the
			// map and then set the values.
			if (map.containsKey(capturedData.getExtensionName())) {
				// Here we get the object memory reference
				capturedDataDto = map.get(capturedData.getExtensionName());
			} else {
				// Here we create a new object to set at the map
				capturedDataDto = new CapturedDataDto();
				map.put(capturedData.getExtensionName(), capturedDataDto);
			}
			capturedDataDto.sumTotalLines(capturedData.getTotalLines());
			capturedDataDto.sumTotalSize(capturedData.getTotalSize());
			capturedDataDto.sumTotalOccurrence(1);
		} else {
			// At this point we have a list of href so we split it to get only the href data
			// and build the new URL that we will use to call this method again creating a
			// Recursion. It's good to know that the end of recursion is if we don't find a
			// list of href
			for (String s : listMatches) {
				String subUrl;
				String[] help = s.split("href=\"");
				s = help[1];
				subUrl = "https://github.com" + s;
				this.runCrawl(subUrl, responseSizeFormat);
			}
		}

	}

	public CapturedData returnCapturedData(String initialUrlContent, String inputUrl, String responseSizeFormat) {
		Double totalSize = null;
		String extensionName = null;
		Integer totalLines = null;
		Pattern pattern = null;
		Matcher matcher = null;

		// Here we get the URL and split it to get the extension at the end of the URL.
		String[] helpList = inputUrl.split("\\.");
		extensionName = helpList[helpList.length - 1];

		// Here we start to search the Data on the page since lines to size.
		pattern = Pattern.compile("(\\d+\\s)lines\\s.*?(\\d+\\.?\\d*\\s)(Bytes|KB|MB|GB|TB|PB|EB|ZB|YB)",
				Pattern.DOTALL);
		matcher = pattern.matcher(initialUrlContent);
		if (matcher.find()) {
			// If we find it means that the file really has size and lines otherwise we will
			// not enter here.
			matcher.group(2).trim().split("lines");

			// Here we format the lines to a Integer.
			totalLines = Integer.parseInt(matcher.group(1).trim());

			// Here we call another class to manipulate the size and format it if it need.
			totalSize = this.dataSizeManipulator.calculateSize(matcher.group(2).trim(), matcher.group(3).trim(),
					responseSizeFormat);
		} else {
			// If we don't find it means that the file don't have lines only size, so we
			// will get only size searching with REGEX.
			totalLines = 0;
			pattern = Pattern.compile("(\\d+\\.?\\d*\\s)(Bytes|KB|MB|GB|TB|PB|EB|ZB|YB)");
			matcher = pattern.matcher(initialUrlContent);
			if (matcher.find()) {
				// Here we call another class to manipulate the size and format it if it need.
				totalSize = this.dataSizeManipulator.calculateSize(matcher.group(1).trim(), matcher.group(2).trim(),
						responseSizeFormat);
			} else {
				// If we enter here means that the file don't have nothing so we set everything
				// to 0 and return it.
				totalSize = 0.0;
			}
		}

		// And last but not least we return the object.
		return new CapturedData(totalSize, extensionName, totalLines);
	}

}
