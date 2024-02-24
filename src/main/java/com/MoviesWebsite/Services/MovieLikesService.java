package com.MoviesWebsite.Services;

import com.MoviesWebsite.Entities.MovieLikes;
import com.MoviesWebsite.Entities.Users;

public interface MovieLikesService {
	int addMovieLike(Users user,int movieId);
}
