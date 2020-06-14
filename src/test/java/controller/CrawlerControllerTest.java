package controller;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.Test;

import com.luizlealbraga.crawlerapi.Control.CrawlerCrontoller;

public class CrawlerControllerTest {
	CrawlerCrontoller crawlerCrontoller = new CrawlerCrontoller();

	@Test
	public void crawGithubTest() throws IOException, InterruptedException {
		JSONObject json = new JSONObject("{\r\n"
				+ "    \"url\": \"https://github.com/bradtraversy/vanillawebprojects/tree/master/breakout-game\"\r\n"
				+ "}");
		JSONObject resultJson = this.crawlerCrontoller.crawGithub(json);
		JSONObject expectedJson = new JSONObject("{\r\n" + "    \"sizeFormat\": \"Bytes\",\r\n" + "    \"data\": {\r\n"
				+ "        \"txt\": {\r\n" + "            \"size\": 338,\r\n" + "            \"lines\": 12\r\n"
				+ "        },\r\n" + "        \"css\": {\r\n" + "            \"size\": 937,\r\n"
				+ "            \"lines\": 70\r\n" + "        },\r\n" + "        \"md\": {\r\n"
				+ "            \"size\": 406,\r\n" + "            \"lines\": 13\r\n" + "        },\r\n"
				+ "        \"js\": {\r\n" + "            \"size\": 5191.68,\r\n" + "            \"lines\": 240\r\n"
				+ "        },\r\n" + "        \"html\": {\r\n" + "            \"size\": 844,\r\n"
				+ "            \"lines\": 27\r\n" + "        }\r\n" + "    }\r\n" + "}");
		assertEquals(expectedJson.toString(), resultJson.toString());
	}

}
