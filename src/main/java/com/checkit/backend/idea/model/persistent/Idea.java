package com.checkit.backend.idea.model.persistent;

import com.checkit.backend.sso.model.persistent.ApplicationUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Gleb Dovzhenko on 21.05.2018.
 */
@Entity
@Table(name = "IDEAS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id"})
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEA_ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "AUTHOR")
    private String author;
    @NotNull
    @Column(name = "DATE")
    private Date date;
    @Column(name = "CATEGORY")
    private String category;
    @ManyToOne
    @JoinColumn(name = "APPLICATION_USER_ID", nullable = false)
    @JsonBackReference
    private ApplicationUser applicationUser;
}
