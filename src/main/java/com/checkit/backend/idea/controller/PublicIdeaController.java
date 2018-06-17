package com.checkit.backend.idea.controller;

import com.checkit.backend.common.model.dto.response.CheckItGenericResponse;
import com.checkit.backend.common.model.dto.response.CheckItResponseType;
import com.checkit.backend.idea.model.dto.request.IdeaLikeRequest;
import com.checkit.backend.idea.model.dto.request.IdeaPublishRequest;
import com.checkit.backend.idea.model.dto.response.IdeaCatalogResponse;
import com.checkit.backend.idea.model.persistent.PublicIdea;
import com.checkit.backend.idea.service.PublicIdeaCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Gleb Dovzhenko on 17.06.2018.
 */
@RestController
@RequestMapping("/api/ideas")
public class PublicIdeaController {

    @Autowired
    private PublicIdeaCatalogService publicIdeaCatalogService;

    @GetMapping
    ResponseEntity<?> ideaCatalogHandler() {
        List<PublicIdea> publicIdeas = publicIdeaCatalogService.getPublicIdeas();
        return ResponseEntity.ok().body(new IdeaCatalogResponse(publicIdeas));
    }

    @GetMapping("/category/{ideaCategory}")
    ResponseEntity<?> ideaCatalogCategoryHandler(@PathVariable String ideaCategory) {
        List<PublicIdea> publicIdeas = publicIdeaCatalogService.findIdeasByCategory(ideaCategory);
        return ResponseEntity.ok().body(new IdeaCatalogResponse(publicIdeas));
    }

    @PostMapping("/publish")
    ResponseEntity<?> ideaPublishHandler(@RequestBody IdeaPublishRequest publishRequest) {
        PublicIdea publishedIdea = publicIdeaCatalogService.createPublicIdea(publishRequest);
        return ResponseEntity.ok().body(
                new CheckItGenericResponse(HttpStatus.CREATED.value(),
                        CheckItResponseType.SUCCESS,
                        "UserIdea '"+ publishedIdea.getUserIdea().getTitle()+"' published successfully"));
    }

    @PostMapping("/like")
    ResponseEntity<?> ideaLikeHandler(@RequestBody IdeaLikeRequest ideaLikeRequest) {
        PublicIdea publishedIdea = publicIdeaCatalogService.addLikeToIdea(ideaLikeRequest);
        return ResponseEntity.ok().body(
                new CheckItGenericResponse(HttpStatus.OK.value(),
                        CheckItResponseType.SUCCESS,
                        "Your like was added to idea '" + publishedIdea.getUserIdea().getTitle()
                                + "' successfully. Actual likes count is: " + publishedIdea.getLikeCount()));
    }
}