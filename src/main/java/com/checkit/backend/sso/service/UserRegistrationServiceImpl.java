package com.checkit.backend.sso.service;

import com.checkit.backend.common.exeption.BadRequestException;
import com.checkit.backend.sso.model.dto.request.SignUpUserRequest;
import com.checkit.backend.sso.model.persistent.ApplicationUser;
import com.checkit.backend.sso.model.persistent.UserData;
import com.checkit.backend.sso.model.persistent.UserRole;
import com.checkit.backend.sso.repository.ApplicationUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gleb Dovzhenko on 10.05.2018.
 */
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserRegistrationServiceImpl(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    @Override
    public ApplicationUser registerUser(SignUpUserRequest signUpUserRequest) throws BadRequestException {
        String userEmail = signUpUserRequest.getEmail();

        if(applicationUserRepository.findByEmail(userEmail).isPresent())
            throw new BadRequestException("Email already exists");

        List<UserRole> role = new ArrayList<>();
        role.add(UserRole.ROLE_USER);

        ApplicationUser applicationUser = ApplicationUser.builder()
                .email(userEmail)
                .password(bCryptPasswordEncoder.encode(signUpUserRequest.getPassword()))
                .role(role)
                .build();

        String userProfileUrl = applicationUserRepository.save(applicationUser).getId().toString();

        applicationUser.setUserData(UserData.builder()
                .profileURL(userProfileUrl)
                .firstName(signUpUserRequest.getFirstName())
                .lastName(signUpUserRequest.getLastName()).build());

        return applicationUser;
    }
}
