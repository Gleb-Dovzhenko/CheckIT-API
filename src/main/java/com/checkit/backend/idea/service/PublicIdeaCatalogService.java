package com.checkit.backend.idea.service;

import com.checkit.backend.idea.model.dto.request.IdeaLikeRequest;
import com.checkit.backend.idea.model.dto.request.IdeaPublishRequest;
import com.checkit.backend.idea.model.persistent.Category;
import com.checkit.backend.idea.model.persistent.PublicIdea;
import com.checkit.backend.idea.model.persistent.Status;
import com.checkit.backend.idea.model.persistent.UserIdea;
import com.checkit.backend.idea.repository.PublicIdeaCatalogRepository;
import com.checkit.backend.idea.repository.UserIdeaCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Gleb Dovzhenko on 16.06.2018.
 */
@Service
public class PublicIdeaCatalogService {

    @Autowired
    private PublicIdeaCatalogRepository publicIdeaCatalogRepository;
    @Autowired
    private UserIdeaCatalogRepository userIdeaCatalogRepository;

    @Transactional
    public PublicIdea createPublicIdea(IdeaPublishRequest publishIdeaRequest) {
        UserIdea uploadedIdea = userIdeaCatalogRepository.getOne(publishIdeaRequest.getIdeaId());
        uploadedIdea.setStatus(Status.STATUS_PUBLISHING);
        PublicIdea publishedIdea = PublicIdea.builder()
                .userIdea(uploadedIdea)
                .category(publishIdeaRequest.getCategories())
                .build();
       return publicIdeaCatalogRepository.save(publishedIdea);
    }

    public List<PublicIdea> findIdeasByCategory(String category) {
        return publicIdeaCatalogRepository.findByCategory(Category.valueOf(category.toUpperCase()));
    }

    public List<PublicIdea> getPublicIdeas() {
        return publicIdeaCatalogRepository.findAll();
    }

    public PublicIdea addLikeToIdea(IdeaLikeRequest ideaLikeRequest) {
        PublicIdea publishedIdea = publicIdeaCatalogRepository.getOne(ideaLikeRequest.getIdeaId());
        int likeCount = publishedIdea.getLikeCount() + 1;
        publishedIdea.setLikeCount(likeCount);
        return publicIdeaCatalogRepository.save(publishedIdea);
    }
}
