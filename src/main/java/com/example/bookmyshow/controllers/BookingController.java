package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.BookMovieRequestDto;
import com.example.bookmyshow.dtos.BookMovieResponseDto;
import com.example.bookmyshow.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDto bookMovieTicket(BookMovieRequestDto bookMovieRequestDto) {
        return null;
    }
}
