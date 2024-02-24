package com.MoviesWebsite.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.MoviesWebsite.Entities.MovieEntity;
import com.MoviesWebsite.Entities.Users;
import com.MoviesWebsite.Services.MovieLikeServiceImpl;
import com.MoviesWebsite.Services.MovieService;
import com.MoviesWebsite.Services.UsersServices;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/v1/movie")
@RestController
public class MovieSearchController {
	
	@Autowired
	MovieService movie;
	
	@Autowired
	UsersServices userService;
	
	@Autowired
	MovieLikeServiceImpl movieLikesServiceImpl;
	
	@GetMapping
	@ResponseBody
	public String getMovie(@RequestParam int id) {
		
		System.out.println("Request Param id :-"+id);
		
		MovieEntity m= movie.getMovie(id);
		System.out.println(m);
		Gson gson = new Gson();
		
		
		
		return gson.toJson(m);
	}
	
	
	
	@PostMapping("/Setlike")
	@ResponseBody
	public int likesController(@RequestBody Object req,Principal p) {
		System.out.println(req);
		Gson gson = new Gson();
		JsonObject jobj = gson.toJsonTree(req).getAsJsonObject();
		System.out.println("Coverting req object to json object "+jobj);
		System.out.println(p.getName());
		
		 Users user= userService.getUsersByEmail(p.getName());
		 
		 System.out.println("User is :- "+user);
		 int counts = movieLikesServiceImpl.addMovieLike(user, jobj.get("id").getAsInt());
		 
		return counts;
		
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
	
	@PostMapping("/sort/VoteCount")
	@ResponseBody
	public String getMovieByVoteCount(@RequestParam(defaultValue = "0") int page, HttpSession session) {
		System.out.println("getMovieByVoteCount page "+page);
		Pageable p = PageRequest.of(page, 10);
		Gson gson = new Gson();
		
		 List<MovieEntity> moviesByPop= movie.gettingMoviesSortByVoteCount(p);
		 session.setAttribute("movieSize", moviesByPop.size());
		 
		 return gson.toJson(moviesByPop);
	}
	
}
