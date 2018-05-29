package com.checkit.backend.sso.service;

import com.checkit.backend.sso.model.persistent.ApplicationUser;
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

}
