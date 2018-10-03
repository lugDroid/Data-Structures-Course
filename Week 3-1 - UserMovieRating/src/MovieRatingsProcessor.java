/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;


public class MovieRatingsProcessor {

	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
		List<String> movieList = new ArrayList<String>();
		
		if (movieRatings == null || movieRatings.size() == 0)
			return movieList;
		
		
		for (Map.Entry<String, PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
			movieList.add(entry.getKey());
		}
		
		return movieList;
	}

	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		List<String> movieList = new ArrayList<String>();
		
		if (movieRatings == null || movieRatings.size() == 0)
			return movieList;
		
		for (Map.Entry<String, PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
			if (entry.getValue().remove() > rating)
				movieList.add(entry.getKey());
		}
		
		return movieList;
	}
	
	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		TreeMap<String, Integer> moviesRemoved = new TreeMap<>();
		ArrayList<String> allRatingsRemoved = new ArrayList<>();
		
		if (movieRatings == null || movieRatings.size() == 0)
			return moviesRemoved;
		
		for (Map.Entry<String, PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
			String title = entry.getKey();
			PriorityQueue<Integer> ratings = entry.getValue();
			
			while (ratings.size() != 0 && ratings.peek() < rating) {
				ratings.remove();
				
				if (moviesRemoved.containsKey(title)) {
					int count = moviesRemoved.get(title);
					moviesRemoved.put(title, count + 1);
				} else {
					moviesRemoved.put(title, 1);
				}
			}
			
			if (ratings == null || ratings.size() == 0)
				allRatingsRemoved.add(title);
		}
		
		// delete movies from tree if ratings empty
		for (String title : allRatingsRemoved)
			movieRatings.remove(title);
		
		return moviesRemoved;
		
	}
	
	public static void main(String[] args) {
		ArrayList<UserMovieRating> movieList = new ArrayList<>();
		
		movieList.add(new UserMovieRating("Movie 5", 8));
		movieList.add(new UserMovieRating("Movie 1", 5));
		movieList.add(new UserMovieRating("Movie 2", 7));
		movieList.add(new UserMovieRating("Movie 3", 4));
		movieList.add(new UserMovieRating("Movie 4", 1));
		movieList.add(new UserMovieRating("Movie 1", 5));
		movieList.add(new UserMovieRating("movie 1", 5));
		movieList.add(new UserMovieRating("Movie 1", 4));
		movieList.add(new UserMovieRating("movie 4", 3));
		
		TreeMap<String, PriorityQueue<Integer>> movies = MovieRatingsParser.parseMovieRatings(movieList);
		
		for (Map.Entry<String, PriorityQueue<Integer>> entry : movies.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		
		TreeMap<String, Integer> moviesRemoved = removeAllRatingsBelow(movies, 9);
		
		for (Map.Entry<String, Integer> entry : moviesRemoved.entrySet()) {
			System.out.println(entry.getKey() + " has been removed " + entry.getValue() + " times");
		}
	}
}
