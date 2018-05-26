package com.checkit.backend.idea.service;

import com.checkit.backend.idea.model.dto.request.IdeaCreationRequest;
import com.checkit.backend.idea.model.persistent.Idea;
import com.checkit.backend.idea.repository.IdeaCatalogRepository;
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
public class IdeaCatalogService {

    @Autowired
    private IdeaCatalogRepository ideaCatalogRepository;
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    public List<Idea> findIdeasByCategory(String category) {
        return ideaCatalogRepository.findByCategory(category);
    }

    @Transactional
    public List<Idea> findIdeasFromUserProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        ApplicationUser user = applicationUserRepository.findByEmail(email).get();
        return user.getUserIdeas();
    }

    @Transactional
    public Idea saveIdea(IdeaCreationRequest ideaCreationRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        ApplicationUser user = applicationUserRepository.findByEmail(email).get();
        Idea idea = Idea.builder().applicationUser(user)
                .title(ideaCreationRequest.getTitle())
                .author(user.getUserData().getFirstName()+" "+user.getUserData().getLastName())
                .date(new Date())
                .category(ideaCreationRequest.getCategory())
                .build();
        return ideaCatalogRepository.save(idea);
    }
}
