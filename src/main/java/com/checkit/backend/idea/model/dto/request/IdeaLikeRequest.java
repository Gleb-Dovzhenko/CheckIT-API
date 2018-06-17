package com.checkit.backend.idea.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by Gleb Dovzhenko on 17.06.2018.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IdeaLikeRequest {
    @NotEmpty
    @NotNull
    private Long ideaId;
}
