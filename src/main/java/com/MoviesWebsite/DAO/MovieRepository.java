package com.MoviesWebsite.DAO;

import org.springframework.data.repository.CrudRepository;

import com.MoviesWebsite.Entities.MovieEntity;

public interface MovieRepository extends CrudRepository<MovieEntity, Integer>{

}
