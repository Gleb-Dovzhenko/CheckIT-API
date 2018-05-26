package com.checkit.backend.idea.model.dto.response;

import com.checkit.backend.idea.model.persistent.Idea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Gleb Dovzhenko on 23.05.2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdeaUserDraftResponse {
    private List<Idea> userDraftIdeas;
}
