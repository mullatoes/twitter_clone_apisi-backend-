package com.kyeiiih.twitterclone.service;

import com.kyeiiih.twitterclone.models.Tweet;
import com.kyeiiih.twitterclone.models.User;
import com.kyeiiih.twitterclone.repository.TweetRepository;
import com.kyeiiih.twitterclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Autowired
    public TweetService(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public Tweet createTweet(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    public Tweet getTweet(Long tweetId) {
        return tweetRepository.findById(tweetId)
                .orElseThrow(() -> new IllegalArgumentException("Tweet id " + tweetId + " does not exist"));
    }

    public List<Tweet> getUserTweets(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User id " + userId + " does not exist"));

        return user.getTweets();
    }
}
