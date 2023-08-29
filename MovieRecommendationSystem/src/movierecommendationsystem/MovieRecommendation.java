package movierecommendationsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class MovieRecommendation {
	Map<String, User> users;
	List<Movie> movies;

	public MovieRecommendation() {
		users = new HashMap<>();
		movies = new ArrayList<>();
	}

	public void addUser(String userName) {

		users.put(userName, new User(userName));

		System.out.println("user added");
	}

	public <addMovie> void addMovie(String title, Set<String> genres) {
		movies.add(new Movie(title, genres));
		
		
	}

	public void watchMovie(String userName, String movieTitle) {
		User user = users.get(userName);
		Movie movie = findMovieByTitle(movieTitle);

		if (movies != null && movie != null) {
			user.watchMovie(movie);
		} else {
			System.out.println("movie not found");
		}
	}

	public List<Movie> getRecommendedMovies(String userName) {
		User user = users.get(userName);
		List<Movie> recommendedMovies = new ArrayList<>();

		if (user != null) {
			for (Movie movie : movies) {
				if (!user.watchedMovies.contains(movie)) {
					recommendedMovies.add(movie);
				}
			}
		}

		return recommendedMovies;
	}

	private Movie findMovieByTitle(String title) {
		for (Movie movie : movies) {
			if (movie.title.equalsIgnoreCase(title)) {
				return movie;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MovieRecommendation recommendationSystem = new MovieRecommendation();

		String userName = null;

		while (true) {
			System.out.println("*****************************");
			System.out.println("Options:");
			System.out.println("1. Add user");
			System.out.println("2. Add movie");
			System.out.println("3. Watch movie");
			System.out.println("4. Rate movies");
			System.out.println("5. Get recommended movies");
			System.out.println("6. Exit");
			System.out.println("******************************");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.print("Enter user name: ");
				userName = scanner.nextLine();
				recommendationSystem.addUser(userName);
				break;
			case 2:
				System.out.print("Enter movie title: ");
				String movieTitle = scanner.nextLine();
				System.out.print("Enter genres (comma-separated): ");
				String[] genreArray = scanner.nextLine().split(",");
				Set<String> genres = new HashSet<>(Arrays.asList(genreArray));
				recommendationSystem.addMovie(movieTitle, genres);
				break;
			case 3:

				System.out.print("Enter movie title: ");
				movieTitle = scanner.nextLine();
				recommendationSystem.watchMovie(userName, movieTitle);
				System.out.println("Enjoy Your Movie....");
				break;
			case 4:
				System.out.print("Enter movie title: ");
				movieTitle = scanner.nextLine();
				Movie ratedMovie = recommendationSystem.findMovieByTitle(movieTitle);
				if (ratedMovie != null) {
					System.out.print("Enter rating (1 to 5): ");
					int rating = scanner.nextInt();
					scanner.nextLine();
					ratedMovie.rateMovie(rating);
				} else {
					System.out.println("Movie not found.");
				}
				break;
			case 5:
				scanner.nextLine();
				List<Movie> recommendedMovies = recommendationSystem.getRecommendedMovies(userName);
				System.out.println("Recommended movies for " + userName + ":");
				for (Movie movie : recommendedMovies) {
					System.out.println(movie.title + " (Average Rating: " + movie.getAverageRating() + ")");
				}
				break;
			case 6:
				System.out.println("Thank You....");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
		}
	}
}