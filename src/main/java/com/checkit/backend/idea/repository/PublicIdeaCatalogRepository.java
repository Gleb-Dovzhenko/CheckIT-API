package com.checkit.backend.idea.repository;

import com.checkit.backend.idea.model.persistent.Category;
import com.checkit.backend.idea.model.persistent.PublicIdea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Gleb Dovzhenko on 16.06.2018.
 */
public interface PublicIdeaCatalogRepository extends JpaRepository<PublicIdea, Long> {
    List<PublicIdea> findByCategory(Category category);
}
