package com.example.bookmyshow.dtos;

import com.example.bookmyshow.models.Feature;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddMovieRequestDto {
    private String name;
    private List<Feature> features;
}
