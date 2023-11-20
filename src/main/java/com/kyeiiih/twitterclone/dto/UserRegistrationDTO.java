package com.kyeiiih.twitterclone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {
    private String username;
    private String password;
    private String displayName;
    private String location;
    private String bio;
    private String profilePictureUrl;
    private String website;
}
