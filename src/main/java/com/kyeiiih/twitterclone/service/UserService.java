package com.kyeiiih.twitterclone.service;

import com.kyeiiih.twitterclone.dto.UserLoginDTO;
import com.kyeiiih.twitterclone.dto.UserRegistrationDTO;
import com.kyeiiih.twitterclone.models.User;
import com.kyeiiih.twitterclone.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserRegistrationDTO userRegistrationDTO) {

        User byUsername = userRepository
                .findByUsername(userRegistrationDTO.getUsername());

        if (byUsername != null) {
            throw new IllegalArgumentException("Username is already taken");
        }

        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setFollowers(Collections.emptyList());
        user.setDisplayName(userRegistrationDTO.getDisplayName());
        user.setLikedTweets(Collections.emptyList());
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }
    }

    public User searchUser(String query) {
        return userRepository.findByUsername(query);
    }


    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " does not exist"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User loginUser(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByUsername(userLoginDTO.getUsername());

        if (user != null && user.getPassword().equals(userLoginDTO.getPassword())) {
            return user;
        }

        return null;
    }

    public User updateUserDetails(Long userId, UserRegistrationDTO updatedUserDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        existingUser.setUsername(updatedUserDTO.getUsername());
        existingUser.setPassword(updatedUserDTO.getPassword());
        existingUser.setBio(updatedUserDTO.getBio());
        existingUser.setLocation(updatedUserDTO.getLocation());
        existingUser.setDisplayName(updatedUserDTO.getDisplayName());
        existingUser.setProfilePictureUrl(updatedUserDTO.getProfilePictureUrl());
        existingUser.setWebsite(updatedUserDTO.getWebsite());

        return userRepository.save(existingUser);
    }
}
