package com.tier2consulting.spring_boot_cucumber.controller;

import com.tier2consulting.spring_boot_cucumber.model.User;
import com.tier2consulting.spring_boot_cucumber.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        ResponseEntity<User> response;
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            response = new ResponseEntity<>(user, FOUND);
        } else {
            response = new ResponseEntity<>(NOT_FOUND);
        }

        return response;
    }
}
