package movierecommendationsystem;

import java.util.Set;

public class Movie {

	String title;
	Set<String> genre;
	double averageRating;
	int totalRatings;

	public Movie(String title, Set<String> genres) {
		super();
		this.setTitle(title);
		this.genre = genres;
		this.averageRating = 0.0;
		this.totalRatings = 0;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void rateMovie(int rating) {
		if (rating >= 1 && rating <= 5) {
			totalRatings++;
			averageRating = ((averageRating * (totalRatings - 1)) + rating) / totalRatings;
		} else {
			System.out.println("Invalid rating. Please enter a rating between 1 and 5.");
		}
	}

	public double getAverageRating() {
		return averageRating;
	}

}
