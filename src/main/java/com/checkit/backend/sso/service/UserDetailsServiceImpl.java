package com.checkit.backend.sso.service;

import com.checkit.backend.common.exeption.BadRequestException;
import com.checkit.backend.sso.model.persistent.UserData;
import com.checkit.backend.sso.model.dto.request.SignUpUserRequest;
import com.checkit.backend.sso.model.persistent.ApplicationUser;
import com.checkit.backend.sso.model.persistent.UserRole;
import com.checkit.backend.sso.repository.ApplicationUserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Gleb Dovzhenko on 22.04.2018.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository,
                                  BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) {
        Optional<ApplicationUser> probablyUserAccount = applicationUserRepository.findByEmail(username);
        if (!probablyUserAccount.isPresent()) {
            throw new UsernameNotFoundException("User with email " + username + " not found");
        }

        ApplicationUser applicationUser = probablyUserAccount.get();

        return User.withUsername(username)
                .password(applicationUser.getPassword())
                .authorities(applicationUser.getRole())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    @Transactional
    public ApplicationUser registerUser(@Valid SignUpUserRequest signUpUserRequest) {

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
