package com.checkit.backend.sso.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by Gleb Dovzhenko on 02.05.2018.
 */
@Getter
@AllArgsConstructor
public class OnUserAuthenticatedResponse {

    private String username;
    private Collection<? extends GrantedAuthority> userRoles;
    private String accessToken;
    private String refreshToken;
}
