package com.openclassrooms.mddapi.repository;


import com.openclassrooms.mddapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Build UserRepository
 * extends CrudRepository from tools Jpa
 * Use method findById
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findById(Long id);
}
