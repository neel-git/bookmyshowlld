package com.example.bookmyshow;

import com.example.bookmyshow.controllers.MovieController;
import com.example.bookmyshow.controllers.UserController;
import com.example.bookmyshow.dtos.*;
import com.example.bookmyshow.models.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class BookmyshowApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BookmyshowApplication.class, args);
    }

    @Autowired
    private UserController userController;
    @Autowired
    private MovieController movieController;
    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDto requestDto = new SignUpRequestDto();
        requestDto.setEmail("nivedac@gmail.com");
        requestDto.setName("Nivedita");
        requestDto.setPassword("1234xyz");

        SignUpResponseDto responseDto = userController.signUpUser(requestDto);

        SignInRequestDto signInRequestDto = new SignInRequestDto();
        signInRequestDto.setEmail("neelac@gmail.com");
        signInRequestDto.setPassword("1234abc");

        SignInResponseDto signInResponseDto = userController.signInUser(signInRequestDto);

        AddMovieRequestDto addMovieRequestDto = new AddMovieRequestDto();
        addMovieRequestDto.setName("Avengers");
        addMovieRequestDto.setFeatures(List.of(Feature.TWO_D,Feature.THREE_D));

        AddMovieResponseDto addMovieResponseDto = movieController.addMovie(addMovieRequestDto);
    }
}
