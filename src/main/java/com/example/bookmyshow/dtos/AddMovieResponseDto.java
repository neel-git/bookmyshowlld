package com.example.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMovieResponseDto {
    private String name;
    private ResponseStatus responseStatus;
}
