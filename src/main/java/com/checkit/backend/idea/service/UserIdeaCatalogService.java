package com.checkit.backend.idea.service;

import com.checkit.backend.idea.model.dto.request.IdeaCreationRequest;
import com.checkit.backend.idea.model.persistent.Status;
import com.checkit.backend.idea.model.persistent.UserIdea;
import com.checkit.backend.idea.repository.UserIdeaCatalogRepository;
import com.checkit.backend.sso.model.persistent.ApplicationUser;
import com.checkit.backend.sso.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Gleb Dovzhenko on 21.05.2018.
 */
@Service
public class UserIdeaCatalogService {

    @Autowired
    private UserIdeaCatalogRepository userIdeaCatalogRepository;
    @Autowired
    private ApplicationUserRepository applicationUserRepository;



    @Transactional
    public List<UserIdea> findIdeasFromUserProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        ApplicationUser user = applicationUserRepository.findByEmail(email).get();
        return user.getUserUserIdeas();
    }

    @Transactional
    public UserIdea saveIdea(IdeaCreationRequest ideaCreationRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        ApplicationUser user = applicationUserRepository.findByEmail(email).get();
        UserIdea userIdea = UserIdea.builder().applicationUser(user)
                .title(ideaCreationRequest.getTitle())
                .author(user.getUserData().getFirstName()+" "+user.getUserData().getLastName())
                .date(new Date())
                .status(Status.STATUS_DRAFT)
                .description(ideaCreationRequest.getDescription())
                .images(ideaCreationRequest.getImages())
                .build();
        return userIdeaCatalogRepository.save(userIdea);
    }
}
