package com.example.bookmyshow.exceptions;

public class MovieAlreadyExistException extends Exception{
    public MovieAlreadyExistException(String message) {
        super(message);
    }
}
