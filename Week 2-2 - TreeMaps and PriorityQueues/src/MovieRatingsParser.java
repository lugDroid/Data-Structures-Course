/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsParser {

	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {
		TreeMap<String, PriorityQueue<Integer>> ratingsTree = new TreeMap<>();
		
		if (allUsersRatings == null || allUsersRatings.size() == 0)
			return ratingsTree;
		
		for (UserMovieRating movieRating : allUsersRatings) {
			if (movieRating != null && movieRating.getMovie() != null && movieRating.getMovie() != "" && movieRating.getUserRating() >= 0) {
				String movieTitle = movieRating.getMovie().toLowerCase();
				
				// check if movie is already in the treemap
				if (ratingsTree.containsKey(movieTitle)) {
					ratingsTree.get(movieTitle).add(movieRating.getUserRating());
				} else {
					PriorityQueue<Integer> ratings = new PriorityQueue<>();
					ratings.add(movieRating.getUserRating());
					ratingsTree.put(movieTitle, ratings);
				}	
			}
		}
		
		return ratingsTree;
	}
	
	public static void main(String[] args) {
		ArrayList<UserMovieRating> movieList = new ArrayList<>();
		
		movieList.add(new UserMovieRating("Movie 1", 5));
		movieList.add(new UserMovieRating("Movie 2", 7));
		movieList.add(new UserMovieRating("Movie 3", 4));
		movieList.add(new UserMovieRating("Movie 4", 0));
		movieList.add(new UserMovieRating("Movie 5", 9));
		movieList.add(new UserMovieRating("Movie 1", 5));
		movieList.add(new UserMovieRating("movie 1", 5));
		movieList.add(new UserMovieRating("Movie 1", 4));
		movieList.add(new UserMovieRating("movie 4", 3));
		
		TreeMap<String, PriorityQueue<Integer>> movies = parseMovieRatings(movieList);
		
		for (Map.Entry<String, PriorityQueue<Integer>> entry : movies.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}
