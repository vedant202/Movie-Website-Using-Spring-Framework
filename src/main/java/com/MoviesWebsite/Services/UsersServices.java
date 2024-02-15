package com.MoviesWebsite.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.MoviesWebsite.DAO.UsersRepository;
import com.MoviesWebsite.Entities.Users;

@Service
public class UsersServices implements UsersServiceInterface {

	@Autowired
	UsersRepository userRepo;
	
	@Override
	public Users createUser(Users u) {
		// TODO Auto-generated method stub
		System.out.println("User details :- "+u);
		
		Users u1 = new Users();
		u1.setFname(u.getFname());
		u1.setLname(u.getLname());
		u1.setEmail(u.getEmail());
		u1.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
		u1.setRole("ROLE_USER");
		
		
		 Users savedUser= userRepo.save(u1);
		 
		 
		 
		 System.out.println("User Created successfull");
		return savedUser;
	}
	
}
