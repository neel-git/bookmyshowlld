package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.SeatNotAvailable;
import com.example.bookmyshow.exceptions.ShowNotFoundException;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.exceptions.UserNotSignedInException;
import com.example.bookmyshow.models.*;
import com.example.bookmyshow.repositories.ShowRepository;
import com.example.bookmyshow.repositories.ShowSeatRepository;
import com.example.bookmyshow.repositories.UserRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final PriceCalculatorService priceCalculatorService;
    private final UserService userService;

    public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, PriceCalculatorService priceCalculatorService, UserService userService) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService = priceCalculatorService;
        this.userService = userService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, Long showId, List<Long> seatIds) throws UserNotFoundException, ShowNotFoundException, SeatNotAvailable, UserNotSignedInException {
        // Check Status
        // Take a lock
        // If it is not available, throw error
        // If Yes, mark the seat status to BLOCKED
        // Release the lock
        // Move to the payments page
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("Invalid User");
        }

        User user = userOptional.get();
        if(!user.isSignedIn()) {
            throw new UserNotSignedInException("Please login to proceed");
        }

        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()) {
            throw new ShowNotFoundException("Invalid Show");
        }
        Show show = showOptional.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(seatIds);

        // Check status of showSeat
        // If it is not available, throw error
        for(ShowSeat showSeat: showSeats) {
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new SeatNotAvailable("Seat is not available");
            }
        }

        // If yes, mark seat status to Blocked
        for (ShowSeat showSeat : showSeats) {
            // Change status to BLOCKED
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeatRepository.save(showSeat);
        }

        Booking booking = new Booking();
        booking.setBookedBy(null);
        booking.setShowSeats(null);
        booking.setBookedAt(null);
        booking.setAmount(priceCalculatorService.calculatePrice(showSeats,show));

        // Call the payment service after calculating the price
        // booking.setPayments(); can use third party


        return null;
    }
}
