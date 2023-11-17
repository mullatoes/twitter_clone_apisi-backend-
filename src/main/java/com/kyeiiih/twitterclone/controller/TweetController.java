package com.kyeiiih.twitterclone.controller;

import com.kyeiiih.twitterclone.dto.TweetDTO;
import com.kyeiiih.twitterclone.models.Tweet;
import com.kyeiiih.twitterclone.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    private final TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping
    public ResponseEntity<?> createTweet(@RequestBody TweetDTO tweetDTO) {
        try {
            Tweet tweet = tweetService.createTweet(tweetDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(tweet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{tweetId}")
    public Tweet getTweet(@PathVariable Long tweetId) {
        return tweetService.getTweet(tweetId);
    }

    @GetMapping("/user/{userId}")
    public List<Tweet> getUserTweets(@PathVariable Long userId) {
        return tweetService.getUserTweets(userId);
    }
}
