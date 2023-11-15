package com.kyeiiih.twitterclone.controller;

import com.kyeiiih.twitterclone.models.Comment;
import com.kyeiiih.twitterclone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @GetMapping("/{commentId}")
    public Comment getComment(@PathVariable Long commentId) {
        return commentService.getComment(commentId);
    }

    @GetMapping("/tweet/{tweetId}")
    public List<Comment> getTweetComments(@PathVariable Long tweetId) {
        return commentService.getTweetComments(tweetId);
    }

}
