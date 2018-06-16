package com.checkit.backend.sso.model.persistent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created by Gleb Dovzhenko on 23.04.2018.
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {

    @NotEmpty(message="{validation.first_name.NotEmpty.message}")
    @Size(min=1, max=60, message="{validation.first_name.Size.message}")
    private String firstName;

    @NotEmpty(message="{validation.last_name.NotEmpty.message}")
    @Size(min=3, max=60, message="{validation.last_name.Size.message}")
    private String lastName;

    @Size(min=3, max=60, message="{validation.phone.Size.message}")
    private String phone;

    private String websiteURL;

    @NotEmpty(message="{validation.profile_url.NotEmpty.message}")
    @Size(min=1, max=90, message="{validation.profile_url.Size.message}")
    private String profileURL;

    private String country;

    private String city;

    private String profilePhotoURL;

    private String about;

}
