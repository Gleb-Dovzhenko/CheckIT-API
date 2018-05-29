package com.checkit.backend.sso.service;

import com.checkit.backend.sso.model.dto.request.SignUpUserRequest;
import com.checkit.backend.sso.model.persistent.ApplicationUser;

/**
 * Created by Gleb Dovzhenko on 10.05.2018.
 */
public interface UserRegistrationService {
    ApplicationUser registerUser(SignUpUserRequest signUpUserRequest);
}
