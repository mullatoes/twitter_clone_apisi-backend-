package com.kyeiiih.twitterclone.repository;

import com.kyeiiih.twitterclone.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
