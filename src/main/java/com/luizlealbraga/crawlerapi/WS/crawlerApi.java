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
	
	@POST
	@Path("craw")
    @Produces(MediaType.APPLICATION_JSON)
    public String crawGithub(String inputJson) {
		JSONObject json = new JSONObject(inputJson);
		try {
			return new CrawlerCrontoller().crawGithub(json).toString();
		} catch (IOException e) {
			return "{ \"error\": \"The url you have requested is't valid.\"}";
		} catch (InterruptedException e) {
			return "{ \"error\": \"The crawler was temporary blocked by github.\"}";
		}
	}
	
	
	
}


    
