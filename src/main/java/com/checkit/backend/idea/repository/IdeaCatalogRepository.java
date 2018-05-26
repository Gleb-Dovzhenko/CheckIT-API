package com.checkit.backend.idea.repository;

import com.checkit.backend.idea.model.persistent.Idea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Gleb Dovzhenko on 21.05.2018.
 */
public interface IdeaCatalogRepository extends JpaRepository<Idea, Long> {
    List<Idea> findByCategory(String category);
}
