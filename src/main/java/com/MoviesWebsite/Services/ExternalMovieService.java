package com.MoviesWebsite.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.Movie.MovieResults;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Value;



@Service
public class ExternalMovieService {
	
	@Value("${api.key}")
    private String apiKey;
	
	@Value("${api.value}")
	private String apiValue;
	
	public String getAllMoviesData() {
		WebClient webC = WebClient.create();
		
		String api_url = apiValue;
		
		String response = webC.get().uri(api_url).header("Authorization", apiKey).retrieve()
		.bodyToMono(String.class).block();
		
		Gson gson = new Gson();
		
		MovieResults movieListjson  =  gson.fromJson(response,MovieResults.class);
		
		System.out.println("movieListjson in service  "+movieListjson);
		return response;
		
		
	}
}
