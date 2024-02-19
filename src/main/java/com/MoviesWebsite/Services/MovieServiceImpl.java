package com.MoviesWebsite.Services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Movie.Movie;
import com.Movie.MovieResults;
import com.MoviesWebsite.DAO.MovieRepository;
import com.MoviesWebsite.Entities.MovieEntity;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	private ExternalMovieService externalMovie;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public List<MovieEntity> getMoviesByTitle(String title) {
		List<MovieEntity> movies=movieRepository.findByTitleContaining(title);
//		MovieEntity movie = movieRepository.findByTitle(title);
		System.out.println("movie "+movies);
		
		return movies;
	}
	
	@Override
	public MovieEntity getMovie(int id) {
		// TODO Auto-generated method stub
		MovieEntity movie=null;
		try {
			movie = movieRepository.findById(id).get();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception in getMovie");
			e.printStackTrace();
		}
		return movie;
	}

	@Override
	public List<MovieEntity> gettingMovies() {
		// Getting all movies from database
		List<MovieEntity> allMovies =(List<MovieEntity>) movieRepository.findAllByOrderByReleaseDateDesc();
		System.out.println("getting all movies");
		System.out.println(allMovies);
		return allMovies;
	}
	
	@Override
	public List<MovieEntity> gettingMovies(Pageable p) {
		// Getting all movies from database
		List<MovieEntity> allMovies =(List<MovieEntity>) movieRepository.findAllByOrderByReleaseDateDesc(p);
		System.out.println("getting all movies");
		System.out.println(allMovies);
		return allMovies;
	}
	

	@Override
	public MovieEntity addMovie(MovieEntity movie) {
		// TODO Auto-generated method stub
		MovieEntity me=null;
		try {
			me=movieRepository.save(movie);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception in add movie");
			e.printStackTrace();
		}
		return me;
	}

	@Override
	public Iterable<MovieEntity> addMovies(Iterable<MovieEntity> movies) {
		// TODO Auto-generated method stub
		List<MovieEntity> movie_entities=null;
		try {
			movie_entities = (List<MovieEntity>)movieRepository.saveAll(movies);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception in add movies");
			
		}
		
		
		return movie_entities;
	}

	@Override
	public void getAndSaveMoviesFromAPI() {
		// TODO Auto-generated method stub
		MovieResults mResults=externalMovie.getAllMoviesData();
		List<MovieEntity> movie_entities = new ArrayList<MovieEntity>();
		for(Movie m: mResults.getResults()) {
			MovieEntity me = new MovieEntity();
			me.setTitle(m.getTitle());
			me.setOriginal_title(m.getOriginal_title());
			me.setOriginal_language(m.getOriginal_language());
			me.setAdult(m.isAdult());
			me.setBackdrop_path(m.getBackdrop_path());
			me.setOverview(m.getOverview());
			me.setPopularity(m.getPopularity());
			me.setPoster_path(m.getPoster_path());
			me.setRelease_date(m.getRelease_date());
			me.setVote_average(m.getVote_average());
			me.setVote_count(m.getVote_count());
			
			movie_entities.add(me);
			
		}
		
		try {
			System.out.println("Saving movies in database");
			Iterable<MovieEntity> results=movieRepository.saveAll(movie_entities);
			System.out.println(results);
			System.out.println("Saved Movies from API in datbase");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		
	}

	@Override
	public List<MovieEntity> gettingMoviesSortByPopularity() {
		// Getting all movies from database and soritng them by popularity
			List<MovieEntity> allMovies =(List<MovieEntity>) movieRepository.findAllByOrderByPopularityDesc();
			System.out.println("getting all movies and sorting them by popularity");
			System.out.println(allMovies);
			return allMovies;
	}

	@Override
	public List<MovieEntity> gettingMoviesSortByPopularity(Pageable p) {
		// Getting all movies from database and soritng them by popularity
			List<MovieEntity> allMovies =(List<MovieEntity>) movieRepository.findAllByOrderByPopularityDesc(p);
			System.out.println("getting all movies and sorting them by popularity");
			System.out.println(allMovies);
			return allMovies;
	}
	
	@Override
	public List<MovieEntity> gettingMoviesSortByVoteCount(Pageable p) {
		// Getting all movies from database and soritng them by popularity
			List<MovieEntity> allMovies =(List<MovieEntity>) movieRepository.findByVoteCountDesc(p);
			System.out.println("getting all movies and sorting them by popularity");
			System.out.println(allMovies);
			return allMovies;
	}
	
	

}
