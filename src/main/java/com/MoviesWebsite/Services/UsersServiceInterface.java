package com.MoviesWebsite.Services;

import java.sql.SQLException;
import java.util.List;

import com.MoviesWebsite.Entities.Users;

public interface UsersServiceInterface  {
	Users createUser(Users user) throws SQLException;
	List<Users> getUsersWithoutPassword();
}
