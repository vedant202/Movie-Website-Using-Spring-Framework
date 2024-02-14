package com.MoviesWebsite.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MoviesWebsite.Entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	
	public Users findByEmail(String email); 

}
