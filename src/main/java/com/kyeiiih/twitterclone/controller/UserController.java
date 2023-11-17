package com.kyeiiih.twitterclone.controller;

import com.kyeiiih.twitterclone.dto.UserLoginDTO;
import com.kyeiiih.twitterclone.dto.UserRegistrationDTO;
import com.kyeiiih.twitterclone.models.Tweet;
import com.kyeiiih.twitterclone.models.User;
import com.kyeiiih.twitterclone.repository.UserRepository;
import com.kyeiiih.twitterclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return userService.createUser(userRegistrationDTO);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/searchUser")
    public User searchUsers(@RequestParam String query) {
        return userService.searchUser(query);
    }

    @GetMapping("/{userId}/tweets")
    public List<Tweet> getUserTweets(@PathVariable Long userId) {
        return userService.getUserTweets(userId);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginUser(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByUsername(userLoginDTO.getUsername());

        if (user != null && user.getPassword().equals(userLoginDTO.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body("Authentication successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }
    }

}
