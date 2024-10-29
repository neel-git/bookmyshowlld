package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.UserAlreadyExistException;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.exceptions.WrongPasswordException;
import com.example.bookmyshow.models.User;
import com.example.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(String name,String email, String password) throws UserAlreadyExistException {
        Optional<User> optionalUser= userRepository.findByEmail(email);

        User user = null;
        if(optionalUser.isEmpty()) {
            // User is not register so register
            user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistException("User already exits.. Please signIn");
        }

        return user;
    }

    public String signIn(String email,String password) throws UserNotFoundException, WrongPasswordException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User doesn't exist");
        }
        User user = userOptional.get();
        if(!user.getPassword().equals(password)) {
            throw new WrongPasswordException("Wrong Password");
        }
        user.setSignedIn(true);
        userRepository.save(user);
        return user.getName();
    }
}
