package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieRepository {
    public List<Movie> movies;

    @PostConstruct
    public void init() {
        movies = new ArrayList<>();

        for (int i = 1; i <= 10; i++)
            movies.add(new Movie("Movie" + i, "Summary" + i, i));
    }

    public List<Movie> findAll() {
        return movies;
    }

    public List<Movie> searchMovies(String text) {
        return movies.stream()
                .filter(m -> m.getTitle().contains(text) || m.getSummary().contains(text))
                .collect(Collectors.toList());
    }
}
