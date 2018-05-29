package com.checkit.backend.sso.controller;

import com.checkit.backend.common.exeption.BadRequestException;
import com.checkit.backend.common.model.dto.response.CheckItGenericResponse;
import com.checkit.backend.common.model.dto.response.CheckItResponseType;
import com.checkit.backend.sso.model.persistent.ApplicationUser;
import com.checkit.backend.sso.model.dto.request.SignUpUserRequest;
import com.checkit.backend.sso.model.dto.response.SignUpUserResponse;
import com.checkit.backend.sso.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Gleb Dovzhenko on 22.04.2018.
 */
@RestController
@RequestMapping(value = "/api/signup")
public class SignUpController {

    private final UserRegistrationService userRegistrationService;

    @Autowired
    public SignUpController(UserRegistrationService userDetailsServiceImpl) {
        this.userRegistrationService = userDetailsServiceImpl;
    }

    @PostMapping
    public ResponseEntity<?> singUpHandler(@Valid @RequestBody SignUpUserRequest signUpUserRequest) {
        try {
            ApplicationUser registeredUser = userRegistrationService.registerUser(signUpUserRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SignUpUserResponse(registeredUser));
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new CheckItGenericResponse(HttpStatus.CONFLICT.value(),
                    CheckItResponseType.CLIENT_ERROR,
                    "Email already exists"));
        }
    }
}
