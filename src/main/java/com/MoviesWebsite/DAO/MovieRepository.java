package com.MoviesWebsite.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.MoviesWebsite.Entities.MovieEntity;

public interface MovieRepository extends CrudRepository<MovieEntity, Integer>{
	List<MovieEntity> findByTitleContaining(String title);
	
	List<MovieEntity> findAllByOrderByReleaseDateDesc();
	
	@Query(value = "select * from movie_entity order by vote_average desc",nativeQuery = true)
	List<MovieEntity> findByVoteCountDesc();
}
