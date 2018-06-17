package com.checkit.backend.idea.model.persistent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Gleb Dovzhenko on 16.06.2018.
 */
@Entity
@Table(name = "PUBLIC_IDEAS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicIdea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PUBLIC_IDEA_ID")
    private Long id;
    @OneToOne
    @JoinColumn(name = "USER_IDEA_ID", nullable = false)
    private UserIdea userIdea;
    @Column(name = "LIKE_COUNT")
    private int likeCount;
    @Column(name = "CATEGORY")
    @ElementCollection(targetClass = Category.class)
    @Enumerated(EnumType.STRING)
    private Set<Category> category;
}
