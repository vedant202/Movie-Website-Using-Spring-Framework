package com.MoviesWebsite.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MoviesWebsite.Entities.Users;
import com.MoviesWebsite.Services.UsersServices;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UsersServices usersServices;
	
	@GetMapping
	
	public String adminPage(Principal p, Model model) {
		String email = p.getName();
		System.out.println("Admin Page , Email :- "+email);
		Users u = usersServices.getUsersByEmail(email);
		System.out.println(u);
		
		model.addAttribute("userFName", u.getFname()+" "+u.getLname());
		return "admin";
	}
	
	@PostMapping("/users")
	@ResponseBody
	public String getUsers() {
		System.out.println("calling users ...............");
		 List<Users> userServ= usersServices.getUsersWithoutPassword();
		 
		 Gson gson = new Gson();
		 
		 
		 JsonArray listJsonObj = new JsonArray();
		 
		 List<Users> users = usersServices.getUsersWithoutPassword();
		 
		 for(Users u:users) {
			 JsonObject jObj = new JsonObject();
			 jObj.addProperty("id", u.getId());
			 jObj.addProperty("FirstName", u.getFname());
			 jObj.addProperty("LastName", u.getLname());
			 jObj.addProperty("Email", u.getEmail());
			 jObj.addProperty("Active", u.isActive());
			 jObj.addProperty("Deleted", u.isDeleted());
			 jObj.addProperty("Role", u.getRole());
			 
			 listJsonObj.add(jObj);
		 }
		 
		  JsonObject data= new JsonObject();
		  data.add("data", listJsonObj);
		 
		 return gson.toJson(data);
		 
	}
}
