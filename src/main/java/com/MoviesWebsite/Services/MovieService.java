package com.MoviesWebsite.Services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.Movie.Movie;
import com.MoviesWebsite.Entities.MovieEntity;

public interface MovieService {
	MovieEntity getMovie(int id);
	
	List<MovieEntity> gettingMovies();
	
	List<MovieEntity> gettingMovies(Pageable p);
	
	List<MovieEntity> gettingMoviesSortByPopularity();
	
	List<MovieEntity> gettingMoviesSortByPopularity(Pageable p);
	
	MovieEntity addMovie(MovieEntity movie);
	
	Iterable<MovieEntity> addMovies(Iterable<MovieEntity> movies);
	
	void getAndSaveMoviesFromAPI();
	
	List<MovieEntity> getMoviesByTitle(String title);
}
