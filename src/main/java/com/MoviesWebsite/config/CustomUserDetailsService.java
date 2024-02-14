package com.MoviesWebsite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.MoviesWebsite.DAO.UsersRepository;
import com.MoviesWebsite.Entities.Users;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UsersRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Users user=userRepo.findByEmail(email);
		
		if(user==null) {
			System.out.println("In CustomUserDetailsService, User not found");
			throw new UsernameNotFoundException("Username not found exception");
		}
		
		return new CustomUsers(user);
	}
	
}
