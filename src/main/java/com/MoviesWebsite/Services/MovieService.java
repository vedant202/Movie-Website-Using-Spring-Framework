package com.MoviesWebsite.Services;

import java.util.List;

import com.Movie.Movie;
import com.MoviesWebsite.Entities.MovieEntity;

public interface MovieService {
	MovieEntity getMovie(int id);
	
	List<MovieEntity> gettingMovies();
	
	MovieEntity addMovie(MovieEntity movie);
	
	Iterable<MovieEntity> addMovies(Iterable<MovieEntity> movies);
	
	void getAndSaveMoviesFromAPI();
	
	List<MovieEntity> getMoviesByTitle(String title);
}
