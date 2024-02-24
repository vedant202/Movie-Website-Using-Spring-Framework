package com.MoviesWebsite.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoviesWebsite.DAO.MovieLikesRepository;
import com.MoviesWebsite.DAO.MovieRepository;
import com.MoviesWebsite.DAO.UsersRepository;
import com.MoviesWebsite.Entities.MovieEntity;
import com.MoviesWebsite.Entities.MovieLikes;
import com.MoviesWebsite.Entities.Users;
import com.google.gson.Gson;

@Service
public class MovieLikeServiceImpl implements MovieLikesService {

	@Autowired
	MovieLikesRepository movieLikesRepo;
	
	@Autowired
	MovieRepository movieRepo;
	
	@Autowired
	UsersRepository usersRepo;
	
	public List<MovieLikes> getAllMovieLikes() {
		List<MovieLikes> likes = movieLikesRepo.findAll();
		
		
		return likes;
	}
	
	@Override
	public int addMovieLike(Users user,int movieId) {
		// TODO Auto-generated method stub
		int counts=0;
		Optional<MovieEntity> movieEntity = movieRepo.findById(movieId);
		 movieEntity.ifPresentOrElse((movie)->{
			 System.out.println("Movie is present "+movie);
			 Optional<MovieLikes> movieLikesEntity= movieLikesRepo.findByMovieId(movie);
			 
			 movieLikesEntity.ifPresentOrElse((likesItem)->{
				 System.out.println("MovieLikes is present "+likesItem);
				 
				  Optional<MovieLikes> findByUserIdandMovieId= movieLikesRepo.findByUsersIdAndMovieId(user, movie);
				  findByUserIdandMovieId.ifPresentOrElse((i)->{
					  System.out.println("findByUserIdandMovieId is present "+i);
					  if(i.getUsers_id().contains(user)) {
						  int count = i.getCount();
						  System.out.println("findByUserIdandMovieId count is present "+count);
						  count = count-1;
						  System.out.println("decrementing count by 1");
						  List<Users> u = i.getUsers_id();
						  u.remove(user);
						  System.out.println("Removing users from users "+user);
						  
						  
						  i.setCount(count);
						  System.out.println("Saving updated count to db which is "+count);
						  i.setUsers_id(u);
						  System.out.println("Saving updated users to db which is "+u);
						  movieLikesRepo.save(i);
					  }
					  
				  }, ()->{
					  System.out.println("findByUserIdandMovieId is absent");
						 int count = likesItem.getCount();
						 List<Users> users = likesItem.getUsers_id();
						 
						 count = count+1;
						 users.add(user);
						 likesItem.setCount(count);
						 likesItem.setUsers_id(users);
						 try {
							 System.out.println("Movie like entity is going to save  "+likesItem);
							 movieLikesRepo.save(likesItem);
						 }catch(Exception e) {
							 e.printStackTrace();
						 }
					  

				  });
				 
				 
			 }, ()->{
				 System.out.println("MovieLikes is not present");
				 MovieLikes mlEntity = new MovieLikes();
				 mlEntity.setCount(1);
				 mlEntity.setMovieId(movie);
				 List<Users> list_users = new ArrayList<Users>();
				 list_users.add(user);
				 mlEntity.setUsers_id(list_users);
				 try {
					 System.out.println("Movie like entity is going to save  "+mlEntity);
					 movieLikesRepo.save(mlEntity);
				 }catch(Exception e) {
					 e.printStackTrace();
				 }
				 
			 });
			 
			 
		 }, ()->{
			 System.out.println("Movie not fount in MovieLikesServiceImpl");
		 });
		 if(movieEntity.isPresent()) {
			 counts = movieLikesRepo.findByUsersIdAndMovieId(user,movieEntity.get()).isPresent()?
					 movieLikesRepo.findByUsersIdAndMovieId(user,movieEntity.get()).get().getCount():0;
		 }else {
			 counts = 0;
		 }
		 return counts;
		 
	}

}
