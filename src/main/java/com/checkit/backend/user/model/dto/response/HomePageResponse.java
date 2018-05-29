package com.checkit.backend.user.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Gleb Dovzhenko on 20.05.2018.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomePageResponse {

    private String profileURL;
    private String firstName;
    private String secondName;
    private String phone;
    private String websiteURL;
    private String country;
    private String city;
    private String profilePhotoURL;
    private String about;
}
