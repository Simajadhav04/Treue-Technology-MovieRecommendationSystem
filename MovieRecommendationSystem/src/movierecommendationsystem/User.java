package movierecommendationsystem;

import java.util.HashSet;
import java.util.Set;

class User {
    String name;
    Set<Movie> watchedMovies;

    public User(String name) {
        this.name = name;
        this.watchedMovies = new HashSet<>();
    }

    public void watchMovie(Movie movie) {
        watchedMovies.add(movie);
    }
}