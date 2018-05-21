package com.checkit.backend.sso.model.persistent;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Created by Gleb Dovzhenko on 22.04.2018.
 */

@Entity
@Table(name = "application_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String email;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = UserRole.class)
    @Enumerated(EnumType.STRING)
    private List<UserRole> role;

    //@OneToOne(targetEntity = UserData.class)
    @Embedded
    private UserData userData;
}
