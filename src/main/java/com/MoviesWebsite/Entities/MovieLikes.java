package com.MoviesWebsite.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class MovieLikes {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id")
	private MovieEntity movieId;
	
	private int count;
	
	@ManyToMany
	@JoinTable(
	    name = "like_users",
	    joinColumns = @JoinColumn(name = "like_id"),
	    inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<Users> usersId= new ArrayList();;
	
	public MovieLikes() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "MovieLikes [id=" + id + ", movieId=" + movieId + ", count=" + count + ", users_id=" + usersId + "]";
	}

	public MovieEntity getMovieId() {
		return movieId;
	}

	public void setMovieId(MovieEntity movieId1) {
		movieId = movieId1;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Users> getUsers_id() {
		return usersId;
	}

	public void setUsers_id(List<Users> users_id) {
		this.usersId = users_id;
	}

	
	
	
	
	
}
