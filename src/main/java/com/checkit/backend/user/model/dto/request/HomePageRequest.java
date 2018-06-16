package com.checkit.backend.user.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Gleb Dovzhenko on 20.05.2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageRequest {

    @NotEmpty
    private Long id;
}
