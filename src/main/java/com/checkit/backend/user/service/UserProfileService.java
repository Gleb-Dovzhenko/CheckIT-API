package com.checkit.backend.user.service;

import com.checkit.backend.sso.model.persistent.UserData;
import com.checkit.backend.sso.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by Gleb Dovzhenko on 20.05.2018.
 */
@Service
public class UserProfileService {

    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public UserProfileService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public UserData loadUserData() {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return applicationUserRepository.findById(id).get().getUserData();
    }
}
