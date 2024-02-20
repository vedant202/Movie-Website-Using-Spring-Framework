package com.MoviesWebsite.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.MoviesWebsite.Entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	
	public Users findByEmail(String email); 
	
	@Query(value="select * from Users", nativeQuery = true)
	List<Users> findAllWithoutPass();
	

}
