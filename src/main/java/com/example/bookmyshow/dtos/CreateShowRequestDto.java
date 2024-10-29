package com.example.bookmyshow.dtos;

import com.example.bookmyshow.models.Feature;
import com.example.bookmyshow.models.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreateShowRequestDto {
    private Movie movie;
    private Date startTime;
    private Date endTime;
    List<Feature> features;
}
