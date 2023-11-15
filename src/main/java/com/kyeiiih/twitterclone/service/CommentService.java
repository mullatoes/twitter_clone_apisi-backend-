package com.kyeiiih.twitterclone.service;

import com.kyeiiih.twitterclone.models.Comment;
import com.kyeiiih.twitterclone.models.Tweet;
import com.kyeiiih.twitterclone.repository.CommentRepository;
import com.kyeiiih.twitterclone.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TweetRepository tweetRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, TweetRepository tweetRepository) {
        this.commentRepository = commentRepository;
        this.tweetRepository = tweetRepository;
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment id " + commentId + " does not exist"));
    }

    public List<Comment> getTweetComments(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new IllegalArgumentException("Tweet id " + tweetId + " does not exist"));

        return tweet.getComments();
    }
}
