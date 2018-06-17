package com.checkit.backend.idea.repository;

import com.checkit.backend.idea.model.persistent.UserIdea;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Gleb Dovzhenko on 21.05.2018.
 */
public interface UserIdeaCatalogRepository extends JpaRepository<UserIdea, Long> {
}
