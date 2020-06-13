package com.luizlealbraga.crawlerapi.WS;

import org.json.JSONObject;

import com.luizlealbraga.crawlerapi.Control.CrawlerCrontoller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("crawler")
public class crawlerApi {
	
	@GET
	@Path("craw")
    @Produces(MediaType.APPLICATION_JSON)
    public String crawGithub(String inputJson) {
		JSONObject json = new JSONObject(inputJson);
		return new CrawlerCrontoller().crawGithub(json);
	}
	
	
	
}


    
