package com.checkit.backend.idea.controller;

import com.checkit.backend.common.model.dto.response.CheckItGenericResponse;
import com.checkit.backend.common.model.dto.response.CheckItResponseType;
import com.checkit.backend.idea.model.dto.request.IdeaCreationRequest;
import com.checkit.backend.idea.model.dto.response.IdeaCatalogResponse;
import com.checkit.backend.idea.model.dto.response.IdeaUserDraftResponse;
import com.checkit.backend.idea.model.persistent.Idea;
import com.checkit.backend.idea.service.IdeaCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Gleb Dovzhenko on 21.05.2018.
 */
@RestController
@RequestMapping("/api/ideas")
public class IdeaController {

    @Autowired
    private IdeaCatalogService ideaCatalogService;

    @GetMapping("/{idea_category}")
    ResponseEntity<?> ideaCatalogHandler(@RequestParam String idea_category) {
        List<Idea> ideas = ideaCatalogService.findIdeasByCategory(idea_category);
        return ResponseEntity.ok().body(new IdeaCatalogResponse(ideas));
    }

    @GetMapping("/draft")
    ResponseEntity<?> ideaUserDraftHandler() {
        List<Idea> ideas = ideaCatalogService.findIdeasFromUserProfile();
        return ResponseEntity.ok().body(new IdeaUserDraftResponse(ideas));
    }

    @PostMapping("/create")
    ResponseEntity<?> ideaCreationHandler(@RequestBody IdeaCreationRequest ideaCreationRequest) {
        Idea storedIdea = ideaCatalogService.saveIdea(ideaCreationRequest);
        return ResponseEntity.ok().body(new CheckItGenericResponse(HttpStatus.CREATED.value(),
                CheckItResponseType.SUCCESS,
                "Idea '"+storedIdea.getTitle()+"' created successfully"));
    }
}
