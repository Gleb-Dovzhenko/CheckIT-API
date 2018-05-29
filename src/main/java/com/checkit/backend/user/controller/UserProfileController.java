package com.checkit.backend.user.controller;

import com.checkit.backend.sso.model.persistent.UserData;
import com.checkit.backend.user.model.dto.response.HomePageResponse;
import com.checkit.backend.user.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Gleb Dovzhenko on 14.05.2018.
 */
@RestController
@RequestMapping("/api/user")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/profile")
    ResponseEntity<?> userHomepageHandler() {
        UserData personalData = userProfileService.loadUserData();
        return ResponseEntity.status(HttpStatus.OK).body(HomePageResponse.builder()
                .profileURL(personalData.getProfileURL()).firstName(personalData.getFirstName())
                .secondName(personalData.getLastName())
                .country("Ukraine")
                .city("Odessa")
                .websiteURL("https://www.frontend.org")
                .build());
    }
}
