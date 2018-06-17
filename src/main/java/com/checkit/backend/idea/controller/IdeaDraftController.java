package com.checkit.backend.idea.controller;

import com.checkit.backend.common.model.dto.response.CheckItGenericResponse;
import com.checkit.backend.common.model.dto.response.CheckItResponseType;
import com.checkit.backend.idea.model.dto.request.IdeaCreationRequest;
import com.checkit.backend.idea.model.dto.response.IdeaUserDraftResponse;
import com.checkit.backend.idea.model.persistent.UserIdea;
import com.checkit.backend.idea.service.UserIdeaCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Gleb Dovzhenko on 21.05.2018.
 */
@RestController
@RequestMapping("/api/ideas/draft")
public class IdeaDraftController {

    @Autowired
    private UserIdeaCatalogService userIdeaCatalogService;

    @GetMapping
    ResponseEntity<?> ideaUserDraftHandler() {
        List<UserIdea> userIdeas = userIdeaCatalogService.findIdeasFromUserProfile();
        return ResponseEntity.ok().body(new IdeaUserDraftResponse(userIdeas));
    }

    @PostMapping("/create")
    ResponseEntity<?> ideaCreationHandler(@RequestBody IdeaCreationRequest ideaCreationRequest) {
        UserIdea storedUserIdea = userIdeaCatalogService.saveIdea(ideaCreationRequest);
        return ResponseEntity.ok().body(new CheckItGenericResponse(HttpStatus.CREATED.value(),
                CheckItResponseType.SUCCESS,
                "UserIdea '"+ storedUserIdea.getTitle()+"' created successfully"));
    }
}
