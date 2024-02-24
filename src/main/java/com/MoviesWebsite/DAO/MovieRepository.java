package com.MoviesWebsite.DAO;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.MoviesWebsite.Entities.MovieEntity;
import com.MoviesWebsite.Entities.MovieLikes;

public interface MovieRepository extends JpaRepository <MovieEntity, Integer>{
	List<MovieEntity> findByTitleContaining(String title);
	
	List<MovieEntity> findAllByOrderByReleaseDateDesc();
	
	List<MovieEntity> findAllByOrderByReleaseDateDesc(Pageable p);
	
	List<MovieEntity> findAllByOrderByPopularityDesc();
	
	List<MovieEntity> findAllByOrderByPopularityDesc(Pageable p);
	
	
	
	@Query(value = "select * from movie_entity order by vote_average desc",
			countQuery = "select count(*) from movie_entity order by vote_average desc"
			,nativeQuery = true)
	List<MovieEntity> findByVoteCountDesc(Pageable p);

}
