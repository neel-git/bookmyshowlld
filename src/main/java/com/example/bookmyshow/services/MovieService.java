package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.MovieAlreadyExistException;
import com.example.bookmyshow.models.Feature;
import com.example.bookmyshow.models.Movie;
import com.example.bookmyshow.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private MovieRepository movieRepository;
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(String name, List<Feature> features) throws MovieAlreadyExistException {
        Optional<Movie> movieOptional= movieRepository.findByName(name);

        Movie movie = null;
        if(movieOptional.isEmpty()) {
            movie = new Movie();
            movie.setName(name);
            movie.setFeatures(features);
            movieRepository.save(movie);
        } else {
            throw new MovieAlreadyExistException("This movie already exists");
        }

        return movie;
    }
}
