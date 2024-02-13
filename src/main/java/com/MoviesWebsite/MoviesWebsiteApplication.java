package com.MoviesWebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.MoviesWebsite.DAO.MovieRepository;
import com.MoviesWebsite.Entities.MovieEntity;

@SpringBootApplication
//@ComponentScan("com.MoviesWebsite.MoviesWebsite.controller")
public class MoviesWebsiteApplication {

	public static void main(String[] args) {
//		System.out.println("Hello Vedant");
//		ApplicationContext context=SpringApplication.run(MoviesWebsiteApplication.class, args);
//		MovieRepository movieRepos= context.getBean(MovieRepository.class);
//		
//		MovieEntity me = new MovieEntity();
//		me.setTitle("test");
//		me.setOverview("test");
//		me.setOriginal_title("test");
//		me.setAdult(false);
//		
//		 MovieEntity savedEntity= movieRepos.save(me);
//		
//		 System.out.println(savedEntity);
		
		SpringApplication.run(MoviesWebsiteApplication.class, args);
		
	}

}
