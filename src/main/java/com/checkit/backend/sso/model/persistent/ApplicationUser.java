package com.checkit.backend.sso.model.persistent;

import com.checkit.backend.idea.model.persistent.Idea;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Created by Gleb Dovzhenko on 22.04.2018.
 */

@Entity
@Table(name = "APPLICATION_USER")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPLICATION_USER_ID")
    private Long id;

    @NotEmpty
    @Column(unique = true, nullable = false, name = "EMAIL")
    private String email;

    @NotEmpty
    @Column(unique = true, nullable = false, name = "PASSWORD")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = UserRole.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLES")
    @JsonManagedReference
    private List<UserRole> role;

    @Embedded
    private UserData userData;

    @OneToMany(mappedBy = "applicationUser")
    private List<Idea> userIdeas;
}
