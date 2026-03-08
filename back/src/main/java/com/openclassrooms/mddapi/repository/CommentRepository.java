package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Build CommentRepository
 * extends CrudRepository from tools Jpa
 */


public interface CommentRepository extends JpaRepository<Comment, Long> {
}
