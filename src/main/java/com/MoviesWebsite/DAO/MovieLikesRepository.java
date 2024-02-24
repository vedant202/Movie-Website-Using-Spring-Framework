package com.MoviesWebsite.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MoviesWebsite.Entities.MovieEntity;
import com.MoviesWebsite.Entities.MovieLikes;
import com.MoviesWebsite.Entities.Users;

public interface MovieLikesRepository extends JpaRepository<MovieLikes, Integer> {
	Optional<MovieLikes> findByMovieId(MovieEntity movieEntity);
	Optional<MovieLikes> findByUsersIdAndMovieId(Users userId, MovieEntity movieId);
}
