package com.kyeiiih.twitterclone.repository;

import com.kyeiiih.twitterclone.models.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
