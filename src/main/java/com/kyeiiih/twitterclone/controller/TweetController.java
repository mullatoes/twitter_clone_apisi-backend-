package com.kyeiiih.twitterclone.controller;

import com.kyeiiih.twitterclone.models.Tweet;
import com.kyeiiih.twitterclone.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Tweet createTweet(@RequestBody Tweet tweet) {
        return tweetService.createTweet(tweet);
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
