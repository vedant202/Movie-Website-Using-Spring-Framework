package com.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieResults {
	private List<Movie> results = new ArrayList<Movie>();

	@Override
	public String toString() {
		return "MovieResults [results=" + results + "]";
	}

	public MovieResults(List<Movie> results) {
		super();
		this.results = results;
	}

	public List<Movie> getResults() {
		return results;
	}

	public void setResults(List<Movie> results) {
		this.results = results;
	}
	
	
}
