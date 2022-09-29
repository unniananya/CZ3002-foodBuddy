package com.food_buddy.app.controller;

import com.food_buddy.app.exception.ResourceNotFoundException;
import com.food_buddy.app.model.User;
import com.food_buddy.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public User viewUserProfile(@PathVariable Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return user;
    }


//    @PutMapping("/{userId}/{height}")
//    public ResponseEntity<?> changeHeight(@PathVariable Long userId){
//
//    }
//    @GetMapping("/checkUsernameAvailability")
//    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
//        Boolean isAvailable = !userRepository.existsByUsername(username);
//        return new UserIdentityAvailability(isAvailable);
//    }
//
//    @GetMapping("/checkEmailAvailability")
//    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
//        Boolean isAvailable = !userRepository.existsByEmail(email);
//        return new UserIdentityAvailability(isAvailable);
//    }


}
