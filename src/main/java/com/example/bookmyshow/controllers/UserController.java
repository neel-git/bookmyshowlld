package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.*;
import com.example.bookmyshow.exceptions.UserAlreadyExistException;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.exceptions.WrongPasswordException;
import com.example.bookmyshow.models.User;
import com.example.bookmyshow.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpResponseDto signUpUser(SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto responseDto = new SignUpResponseDto();
        try {
            User user = userService.signUp(signUpRequestDto.getName(), signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (UserAlreadyExistException userAlreadyExistException){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    public SignInResponseDto signInUser(SignInRequestDto signInRequestDto) {
        SignInResponseDto responseDto = new SignInResponseDto();
        try {
            String userName = userService.signIn(signInRequestDto.getEmail(),signInRequestDto.getPassword());
            responseDto.setName(userName);
            responseDto.setSignedIn(true);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException | WrongPasswordException exception) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
