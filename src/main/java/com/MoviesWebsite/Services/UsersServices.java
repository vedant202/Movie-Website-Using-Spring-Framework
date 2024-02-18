package com.MoviesWebsite.Services;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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
	public Users createUser(Users u) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("User details :- "+u);
		
		Users u1 = new Users();
		u1.setFname(u.getFname());
		u1.setLname(u.getLname());
		u1.setEmail(u.getEmail());
		u1.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
		u1.setRole("ROLE_USER");
		Users savedUser=null;
		try {
			savedUser= userRepo.save(u1);
		}
		catch(Exception e) {
			System.out.println("Throwingggggg new exception.............");
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}
		
		 
		 
		 
		 
		 System.out.println("User Created successfull");
		return savedUser;
	}
	
	
	public Users getUsersByEmail(String email) {
		Users u=null;
		if(email!=null) {
			if(!email.isEmpty()) {
				try {
					u = userRepo.findByEmail(email);
				}catch(Exception e) {
					System.out.println("Exception while fetching email");
					e.printStackTrace();
				}
				
			}
		}
		
		return u;
	}
}
