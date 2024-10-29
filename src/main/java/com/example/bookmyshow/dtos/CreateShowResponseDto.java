package com.example.bookmyshow.dtos;

import com.example.bookmyshow.models.Show;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShowResponseDto {
    private Show show;
    private ResponseStatus responseStatus;
}
