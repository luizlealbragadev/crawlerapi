package com.luizlealbraga.crawlerapi.WS;

import java.io.IOException;

import org.json.JSONObject;

import com.luizlealbraga.crawlerapi.Control.CrawlerCrontoller;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("crawler")
public class crawlerApi {
	
	//This method is responsible for the API End Point, here is where every thing starts and end.
	//It's a POST method and as you can see it's receiving a json as String.
	@POST
	@Path("craw")
    @Produces(MediaType.APPLICATION_JSON)
    public String crawGithub(String inputJson) throws InterruptedException {
		
		//Here we are converting the inputJson from String to JSONObject
		JSONObject json = new JSONObject(inputJson);
		try {
			//Here we start to crawl it sending the json to a controller class and wen it return we return to the client.
			return new CrawlerCrontoller().crawGithub(json).toString();
		} catch (IOException e) {
			return "{ \"error\": \"The url you have requested is't valid.\"}";
		}
	}
	
	
	
}


    
