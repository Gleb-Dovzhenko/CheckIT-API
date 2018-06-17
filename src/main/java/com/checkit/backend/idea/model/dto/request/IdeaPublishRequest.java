package com.checkit.backend.idea.model.dto.request;

import com.checkit.backend.idea.model.persistent.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Gleb Dovzhenko on 17.06.2018.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IdeaPublishRequest {
    @NotNull
    @NotEmpty
    private Long ideaId;
    @NotNull
    @NotEmpty
    private Set<Category> categories;
}
