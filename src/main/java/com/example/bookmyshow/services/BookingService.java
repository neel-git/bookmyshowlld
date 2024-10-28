package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.ShowNotFoundException;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.models.Show;
import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.models.User;
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

    public BookingService(UserRepository userRepository, ShowRepository showRepository,ShowSeatRepository showSeatRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, Long showId, List<Long> seatIds) throws UserNotFoundException, ShowNotFoundException {
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

        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()) {
            throw new ShowNotFoundException("Invalid Show");
        }
        Show show = showOptional.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(seatIds);


        return null;
    }
}
