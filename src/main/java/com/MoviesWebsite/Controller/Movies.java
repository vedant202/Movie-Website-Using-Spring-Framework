package com.MoviesWebsite.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
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
import com.MoviesWebsite.Entities.MovieLikes;
import com.MoviesWebsite.Entities.Users;
import com.MoviesWebsite.Services.ExternalMovieService;
import com.MoviesWebsite.Services.MovieLikeServiceImpl;
import com.MoviesWebsite.Services.MovieService;
import com.MoviesWebsite.Services.UsersServices;
import com.MoviesWebsite.config.CustomUserDetailsService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/v1/")
public class Movies {
	@Autowired
	MovieService movie;
	
	@Autowired
	UsersServices usersServices;
	
	@Autowired
	MovieLikeServiceImpl likeService;
	
	
	@GetMapping("/")
//	@PreAuthorize("hasAnyRole('ROLE_USER')")  this is only for user role and this is method level security
//	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','OIDC_USER','OAUTH2_USER')")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER') or hasAnyAuthority('OIDC_USER','OAUTH2_USER')")
	public String hello(Principal p,Model model) {
		System.out.println("Hello Get");
		
		SecurityContext securityContext= SecurityContextHolder.getContext();
		 System.out.println("index securityContext.getAuthentication().getPrincipal() "+securityContext.getAuthentication().getPrincipal());
		 if(securityContext.getAuthentication().getPrincipal() instanceof DefaultOAuth2User) {
			 
			 System.out.println((DefaultOAuth2User) securityContext.getAuthentication().getPrincipal());
			 DefaultOAuth2User auth2User= (DefaultOAuth2User) securityContext.getAuthentication().getPrincipal();
			 model.addAttribute("userFName", auth2User.getAttribute("name")==null?auth2User.getAttribute("login"):auth2User.getAttribute("name"));
		 }else {
			 String email = p.getName();
				System.out.println("Index Page , Email :- "+email);
				Users u = usersServices.getUsersByEmail(email);
				System.out.println(u);
				
				model.addAttribute("userFName", u.getFname()+" "+u.getLname()); 
		 }
		
		
		
		
		 
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
	public String getAllMovies(@RequestParam(defaultValue = "0") int page,Principal p,HttpSession session) {
		System.out.println("getAllMovies page "+page);
		Pageable pageRequest=PageRequest.of(page, 10);
		
		Gson gson = new Gson();
		List<MovieEntity> movies = movie.gettingMovies(pageRequest);
		
		List<MovieLikes> allMoviesLikes=likeService.getAllMovieLikes();
		
		JsonArray jsonArray = new JsonArray();
		
		
		movies.stream().forEach((i)->{
			JsonObject jObject = new JsonObject();
			jObject.addProperty("id", i.getId());
			jObject.addProperty("title", i.getTitle());
			jObject.addProperty("adult", i.isAdult());
			jObject.addProperty("backdrop_path", i.getBackdrop_path());
			jObject.addProperty("original_language", i.getOriginal_language());
			jObject.addProperty("original_title", i.getOriginal_title());
			jObject.addProperty("overview", i.getOverview());
			jObject.addProperty("popularity", i.getPopularity());
			jObject.addProperty("poster_path", i.getPoster_path());
			jObject.addProperty("releaseDate", i.getRelease_date().toString());
			jObject.addProperty("vote_average", i.getPopularity());
			jObject.addProperty("vote_count", i.getVote_count());
			Optional<MovieLikes> like=allMoviesLikes.stream().filter(l->l.getMovieId().getId()== i.getId()).findFirst();
			like.ifPresentOrElse((pre)->{
				jObject.addProperty("likeCount", pre.getCount());
				Optional<Users> u = pre.getUsers_id().stream().filter(u1->u1.getEmail().equalsIgnoreCase(p.getName())).findFirst();
				u.ifPresentOrElse((check)->{
					jObject.addProperty("userLiked", true);
				}, ()->{
					jObject.addProperty("userLiked", false);
				});
				
			},()->{
				jObject.addProperty("likeCount", 0);
				jObject.addProperty("userLiked", false);
			});
			
			jsonArray.add(gson.fromJson(jObject.toString(), JsonElement.class));
			
		});
		
		session.setAttribute("movieSize", 20);
		
		
//		return gson.toJson(movies);
		return gson.toJson(jsonArray);
	}
	
	
	@GetMapping("/getAndSaveMoviesFromAPI")
	@ResponseBody
	public String getAllMoviesfromExtAPI() {
		
		
		Gson gson = new Gson();
		movie.getAndSaveMoviesFromAPI();
		
		List<MovieEntity> movies = movie.gettingMovies();
		
		
		return gson.toJson(movies);
	}
}
