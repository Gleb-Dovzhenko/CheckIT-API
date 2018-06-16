package com.checkit.backend.idea.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Gleb Dovzhenko on 22.05.2018.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IdeaCreationRequest {
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    private List<String> category;
}
