package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepo;

    public MovieServiceImpl(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public List<Movie> listAll() {
        if (movieRepo.movies.isEmpty())
            throw new IllegalArgumentException("The list of movies is empty!");

        return movieRepo.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        if (text.isEmpty())
            throw new IllegalArgumentException("The text is empty!");

        if (movieRepo.movies.isEmpty())
            throw new IllegalArgumentException("The list of movies is empty!");

        return movieRepo.searchMovies(text);
    }
}
