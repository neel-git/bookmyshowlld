package com.example.bookmyshow.exceptions;

import com.example.bookmyshow.controllers.UserController;

public class UserNotSignedInException extends Exception{
    public UserNotSignedInException(String message) {
        super(message);
    }
}
