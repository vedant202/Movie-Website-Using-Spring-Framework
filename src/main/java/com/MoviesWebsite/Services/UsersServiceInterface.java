package com.MoviesWebsite.Services;

import java.sql.SQLException;

import com.MoviesWebsite.Entities.Users;

public interface UsersServiceInterface  {
	Users createUser(Users user) throws SQLException;
}
