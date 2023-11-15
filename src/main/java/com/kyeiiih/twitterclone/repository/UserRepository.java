package com.kyeiiih.twitterclone.repository;

import com.kyeiiih.twitterclone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String query);
}
