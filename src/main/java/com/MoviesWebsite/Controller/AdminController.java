package com.MoviesWebsite.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MoviesWebsite.Entities.Users;
import com.MoviesWebsite.Services.UsersServices;


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
}
