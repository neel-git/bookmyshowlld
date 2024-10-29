package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.AddMovieRequestDto;
import com.example.bookmyshow.dtos.AddMovieResponseDto;
import com.example.bookmyshow.dtos.ResponseStatus;
import com.example.bookmyshow.exceptions.MovieAlreadyExistException;
import com.example.bookmyshow.models.Movie;
import com.example.bookmyshow.services.MovieService;
import org.springframework.stereotype.Controller;

@Controller
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public AddMovieResponseDto addMovie(AddMovieRequestDto addMovieRequestDto){
        AddMovieResponseDto responseDto = new AddMovieResponseDto();
        try {
            Movie movie = movieService.addMovie(addMovieRequestDto.getName(), addMovieRequestDto.getFeatures());
            responseDto.setName(movie.getName());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (MovieAlreadyExistException e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }
}
