package com.MoviesWebsite.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MovieEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	


	private boolean adult;
	private String original_language;
	private String original_title;
	private String release_date;
	private String title;
	private float vote_average;
	private int vote_count;
	private String overview;
	private String popularity;
	private String poster_path;
	private String backdrop_path;
	
	
	public MovieEntity() {}


	public boolean isAdult() {
		return adult;
	}


	public void setAdult(boolean adult) {
		this.adult = adult;
	}


	public String getOriginal_language() {
		return original_language;
	}


	public void setOriginal_language(String original_language) {
		this.original_language = original_language;
	}


	public String getOriginal_title() {
		return original_title;
	}


	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}


	public String getRelease_date() {
		return release_date;
	}


	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public float getVote_average() {
		return vote_average;
	}


	public void setVote_average(float vote_average) {
		this.vote_average = vote_average;
	}


	public int getVote_count() {
		return vote_count;
	}


	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}


	public String getOverview() {
		return overview;
	}


	public void setOverview(String overview) {
		this.overview = overview;
	}


	public String getPopularity() {
		return popularity;
	}


	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}


	public String getPoster_path() {
		return poster_path;
	}


	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}


	public String getBackdrop_path() {
		return backdrop_path;
	}


	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}


	public MovieEntity(boolean adult, String original_language, String original_title, String release_date,
			String title, float vote_average, int vote_count, String overview, String popularity, String poster_path,
			String backdrop_path) {
		super();
		this.adult = adult;
		this.original_language = original_language;
		this.original_title = original_title;
		this.release_date = release_date;
		this.title = title;
		this.vote_average = vote_average;
		this.vote_count = vote_count;
		this.overview = overview;
		this.popularity = popularity;
		this.poster_path = poster_path;
		this.backdrop_path = backdrop_path;
	}
	
	@Override
	public String toString() {
		return "MovieEntity [id=" + id + ", adult=" + adult + ", original_language=" + original_language
				+ ", original_title=" + original_title + ", release_date=" + release_date + ", title=" + title
				+ ", vote_average=" + vote_average + ", vote_count=" + vote_count + ", overview=" + overview
				+ ", popularity=" + popularity + ", poster_path=" + poster_path + ", backdrop_path=" + backdrop_path
				+ "]";
	}
	
	
	
	
}
