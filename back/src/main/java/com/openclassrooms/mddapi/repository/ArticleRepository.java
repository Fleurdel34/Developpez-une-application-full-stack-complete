package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Build ArticleRepository
 * extends CrudRepository from tools Jpa
 * Create method findAll, findById
 * @Params Long id
 */

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAll();
    Optional<Article> findById(Long id);
}
