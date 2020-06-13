package controller;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

import com.luizlealbraga.crawlerapi.Control.CrawlerCrontoller;

public class CrawlerControllerTest {
	CrawlerCrontoller crawlerCrontoller = new CrawlerCrontoller();

	@Test
	public void crawGithubTest() {
		JSONObject json = new JSONObject("{\r\n"
				+ "    \"url\": \"https://github.com/bradtraversy/vanillawebprojects/tree/master/breakout-game\"\r\n"
				+ "}");
		JSONObject resultJson = new JSONObject(this.crawlerCrontoller.crawGithub(json));
		JSONObject expectedJson = new JSONObject("{\r\n" + "    \"txt\": {\r\n"
				+ "        \"size\": \"338.0 Bytes\",\r\n" + "        \"lines\": \"12 Lines\",\r\n"
				+ "        \"occurrences\": \"1 Occurrences\"\r\n" + "    },\r\n" + "    \"css\": {\r\n"
				+ "        \"size\": \"937.0 Bytes\",\r\n" + "        \"lines\": \"70 Lines\",\r\n"
				+ "        \"occurrences\": \"1 Occurrences\"\r\n" + "    },\r\n" + "    \"md\": {\r\n"
				+ "        \"size\": \"406.0 Bytes\",\r\n" + "        \"lines\": \"13 Lines\",\r\n"
				+ "        \"occurrences\": \"1 Occurrences\"\r\n" + "    },\r\n" + "    \"js\": {\r\n"
				+ "        \"size\": \"5191.68 Bytes\",\r\n" + "        \"lines\": \"240 Lines\",\r\n"
				+ "        \"occurrences\": \"1 Occurrences\"\r\n" + "    },\r\n" + "    \"html\": {\r\n"
				+ "        \"size\": \"844.0 Bytes\",\r\n" + "        \"lines\": \"27 Lines\",\r\n"
				+ "        \"occurrences\": \"1 Occurrences\"\r\n" + "    }\r\n" + "}");
		assertEquals(expectedJson.toString(), resultJson.toString());
	}

}
