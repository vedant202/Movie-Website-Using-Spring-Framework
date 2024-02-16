package com.MoviesWebsite.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Movie.Movie;
import com.Movie.MovieResults;
import com.MoviesWebsite.Entities.MovieEntity;
import com.MoviesWebsite.Entities.Users;
import com.MoviesWebsite.Services.ExternalMovieService;
import com.MoviesWebsite.Services.MovieService;
import com.MoviesWebsite.Services.UsersServices;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
@RequestMapping("/v1/")
public class Movies {
	@Autowired
	MovieService movie;
	
	@Autowired
	UsersServices usersServices;
	
	
	@GetMapping("/")
//	@PreAuthorize("hasAuthority('ROLE_USER')")  this is only for user role and this is method level security
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public String hello(Principal p,Model model) {
		System.out.println("Hello Get");
		String email = p.getName();
		System.out.println("Index Page , Email :- "+email);
		Users u = usersServices.getUsersByEmail(email);
		System.out.println(u);
		
		model.addAttribute("userFName", u.getFname()+" "+u.getLname());
		return "Index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup() {
		
		return "signup";
	}
	
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute("user") Users users,Model model) {
		System.out.println(users);
		boolean error = false;
		try {
			 Users u= usersServices.createUser(users);
		}catch(Exception e) {
			error =true;
			System.out.println("catchingg sel expception ...................");
			model.addAttribute("error", "Error check email and password");
			
		}
		 System.out.println("CheckingUserNullValues "+users);
		 
			 
		 if(error) {
			 System.out.println("Returning signup page");
			 return "signup";
		 }
		 
		return "redirect:/login";
	}
	
//	@PreAuthorize is for method level security and the role give is admin role
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/about")
	public String About(Principal p,Model m) {
		
		
		System.out.println("Hello about");
		String email = p.getName();
		System.out.println("Index Page , Email :- "+email);
		Users u = usersServices.getUsersByEmail(email);
		m.addAttribute("userFName", u.getFname()+" "+u.getLname());
		
		return "About";
	}
	
	
	
	@GetMapping("/movies")
	@ResponseBody
	public String getAllMovies() {
//		String moviesData=movie.getAllMoviesData();
		
		
		Gson gson = new Gson();
		List<MovieEntity> movies = movie.gettingMovies();
		
		
//		return gson.toJson(movieListjson);
		return gson.toJson(movies);
	}
	
	@GetMapping("/getAndSaveMoviesFromAPI")
	@ResponseBody
	public String getAllMoviesfromExtAPI() {
//		String moviesData=movie.getAllMoviesData();
		
		
		Gson gson = new Gson();
		movie.getAndSaveMoviesFromAPI();
		
		List<MovieEntity> movies = movie.gettingMovies();
		
		
//		return gson.toJson(movieListjson);
		return gson.toJson(movies);
	}
}
