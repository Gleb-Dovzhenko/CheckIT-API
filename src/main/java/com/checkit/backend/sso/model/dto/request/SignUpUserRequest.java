package com.checkit.backend.sso.model.dto.request;

import com.checkit.backend.sso.validation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by Gleb Dovzhenko on 30.04.2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpUserRequest {

    @ValidEmail
    @NotEmpty
    @NotNull(message = "Profile email must not be null")
    private String email;
    @Length(min = 8, max = 32, message = "Wrong password length")
    @NotEmpty(message = "Password must not be empty")
    private String password;
    @NotEmpty(message = "First name must not be empty")
    private String firstName;
    @NotEmpty(message = "Second name must not be empty")
    private String lastName;
}
