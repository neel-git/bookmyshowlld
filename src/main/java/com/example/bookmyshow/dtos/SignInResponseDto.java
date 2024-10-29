package com.example.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponseDto {
    private String name;
    private boolean isSignedIn;
    private ResponseStatus responseStatus;
}
