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
		String inputUrl = json.optString("url");
		String responseSizeFormat = json.optString("responseSizeFormat");

		this.runCraw(inputUrl, responseSizeFormat);
		String mapJson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
				.toJson(this.map);
		
		JSONObject returnJson = new JSONObject();
		returnJson.put("sizeFormat", new DataSizeManipulator().formatType(responseSizeFormat));
		returnJson.put("data", new JSONObject(mapJson));
		
		return returnJson;
	}

	public void runCraw(String inputUrl, String responseSizeFormat) throws IOException, InterruptedException {
		URLReader urlReader = new URLReader();
		String initialUrlContent = urlReader.getUrlHtml(inputUrl);
		Pattern pattern = Pattern.compile("(css-truncate css-truncate-target.*\\\")/.*?(?=\\\")");
		Matcher matcher = pattern.matcher(initialUrlContent);
		List<String> listMatches = new ArrayList<String>();
		while (matcher.find()) {
			listMatches.add(matcher.group(0));
		}

		if (listMatches.isEmpty()) {
			CapturedData capturedData = this.returnCapturedData(initialUrlContent, inputUrl, responseSizeFormat);
			CapturedDataDto capturedDataDto;
			if (map.containsKey(capturedData.getExtensionName())) {
				capturedDataDto = map.get(capturedData.getExtensionName());
			} else {
				capturedDataDto = new CapturedDataDto();
				map.put(capturedData.getExtensionName(), capturedDataDto);
			}
			capturedDataDto.sumTotalLines(capturedData.getTotalLines());
			capturedDataDto.sumTotalSize(capturedData.getTotalSize());
			capturedDataDto.sumTotalOccurrence(1);
		} else {
			for (String s : listMatches) {
				String subUrl;
				String[] help = s.split("href=\"");
				s = help[1];
				subUrl = "https://github.com" + s;
				this.runCraw(subUrl, responseSizeFormat);
			}
		}

	}
	
	public CapturedData returnCapturedData(String initialUrlContent, String inputUrl, String responseSizeFormat) {
		Double totalSize = null;
		String extensionName = null;
		Integer totalLines = null;
		Pattern pattern = null;
		Matcher matcher = null;
		String[] helpList = inputUrl.split("\\.");
		extensionName = helpList[helpList.length - 1];

		pattern = Pattern.compile("(\\d+\\s)lines\\s.*?(\\d+\\.?\\d*\\s)(Bytes|KB|MB|GB|TB|PB|EB|ZB|YB)",
				Pattern.DOTALL);
		matcher = pattern.matcher(initialUrlContent);
		if (matcher.find()) {
			matcher.group(2).trim().split("lines");
			totalLines = Integer.parseInt(matcher.group(1).trim());
			totalSize = this.dataSizeManipulator.calculateSize(matcher.group(2).trim(), matcher.group(3).trim(),
					responseSizeFormat);
		} else {
			totalLines = 0;
			pattern = Pattern.compile("(\\d+\\.?\\d*\\s)(Bytes|KB|MB|GB|TB|PB|EB|ZB|YB)");
			matcher = pattern.matcher(initialUrlContent);
			if (matcher.find()) {
				totalSize = this.dataSizeManipulator.calculateSize(matcher.group(1).trim(), matcher.group(2).trim(),
						responseSizeFormat);
			} else {
				totalSize = 0.0;
			}
		}

		return new CapturedData(totalSize, extensionName, totalLines);
	}

}
