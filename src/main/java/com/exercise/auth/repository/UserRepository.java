package com.exercise.auth.repository;

import com.exercise.auth.entity.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User,Long> {

    @Query(value = "select * from AUTH_USER where id = ?1", nativeQuery = true)
    Optional<User> findByid(String id);
    
}
