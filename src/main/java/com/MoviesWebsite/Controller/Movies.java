package com.MoviesWebsite.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Movie.Movie;
import com.Movie.MovieResults;
import com.MoviesWebsite.Services.ExternalMovieService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class Movies {
	@Autowired
	ExternalMovieService movie;
	
	@GetMapping("/")
	public String hello(Model model) {
		
		
		System.out.println("Hello Get");
		
		
		return "Index";
	}
	
	@GetMapping("/about")
	public String About() {
		
		
		System.out.println("Hello about");
		
		
		return "About";
	}
	
	@GetMapping("/movies")
	@ResponseBody
	public String getAllMovies() {
		String moviesData=movie.getAllMoviesData();
		
		
		Gson gson = new Gson();
		
		MovieResults movieListjson  =  gson.fromJson(moviesData,MovieResults.class);
		System.out.println(movieListjson.getResults().size());
		
		return gson.toJson(movieListjson);
	}
}
