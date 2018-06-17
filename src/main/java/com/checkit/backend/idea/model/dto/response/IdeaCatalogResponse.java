package com.checkit.backend.idea.model.dto.response;

import com.checkit.backend.idea.model.persistent.PublicIdea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Gleb Dovzhenko on 21.05.2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdeaCatalogResponse {
    private List<PublicIdea> foundedUserIdeas;
}
