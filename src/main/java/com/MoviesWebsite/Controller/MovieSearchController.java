package com.MoviesWebsite.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.MoviesWebsite.Entities.MovieEntity;
import com.MoviesWebsite.Services.MovieService;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/v1/movie")
@RestController
public class MovieSearchController {
	
	@Autowired
	MovieService movie;
	
	
	@GetMapping
	@ResponseBody
	public String getMovie(@RequestParam int id) {
		
		System.out.println("Request Param id :-"+id);
		
		MovieEntity m= movie.getMovie(id);
		System.out.println(m);
		Gson gson = new Gson();
		
		
		
		return gson.toJson(m);
	}
	
	@PostMapping("/search")
	@ResponseBody
	public String getMovieTitle(@RequestParam String title) {
		
		System.out.println("Request Param title :- " +title);
		
//		List<MovieEntity> m= movie.getMoviesByTitle(title);
		List<MovieEntity> m =movie.getMoviesByTitle(title.trim());
		System.out.println("movie entity "+m);
		Gson gson = new Gson();
		
		
		
		return gson.toJson(m);
	}
	
	@PostMapping("/sort/popularity")
	@ResponseBody
	public String getMovieByPopularity(@RequestParam(defaultValue = "0") int page, HttpSession session) {
		System.out.println("getMovieByPopularity page "+page);
		Pageable p = PageRequest.of(page, 10);
		Gson gson = new Gson();
		
		 List<MovieEntity> moviesByPop= movie.gettingMoviesSortByPopularity(p);
		 session.setAttribute("movieSize", moviesByPop.size());
		 
		 return gson.toJson(moviesByPop);
	}
	
}
