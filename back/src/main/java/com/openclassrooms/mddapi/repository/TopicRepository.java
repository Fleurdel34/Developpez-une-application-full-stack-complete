package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Build TopicRepository
 * extends CrudRepository from tools Jpa
 * Use method findAll and findById
 */

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findAll();
    Optional<Topic> findById(Long id);
}
