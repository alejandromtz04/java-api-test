package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.User;
import com.example.demo.repositories.IUserRepository;

@Service
public class UserService {
    
    @Autowired
    protected IUserRepository userRepository;

    public ResponseEntity<String> createUser(User user) {
        try {
            User newUser = new User();

            newUser.setName(user.getName());
            newUser.setLastName(user.getLastName());
            newUser.setAge(user.getAge());
            newUser.setUserName(user.getUserName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            newUser.setIsActivated(true);

            userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("User: " + user.getName() + ", created successfully");

        } catch (Error error) {
            System.out.println(error);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error of createUser method in UserService.java", error);
        }
    }
}
