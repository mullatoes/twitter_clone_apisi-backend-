package com.kyeiiih.twitterclone.controller;

import com.kyeiiih.twitterclone.dto.UserRegistrationDTO;
import com.kyeiiih.twitterclone.models.Tweet;
import com.kyeiiih.twitterclone.models.User;
import com.kyeiiih.twitterclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
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
}
