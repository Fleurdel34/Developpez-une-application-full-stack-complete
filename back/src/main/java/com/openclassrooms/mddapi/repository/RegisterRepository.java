package com.openclassrooms.mddapi.repository;
import  com.openclassrooms.mddapi.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Build RegisterRepository
 * extends CrudRepository from tools Jpa
 * Create method findByEmail
 * @Params string email
 */

@Repository
public interface RegisterRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
