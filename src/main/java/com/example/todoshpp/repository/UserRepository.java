package com.example.todoshpp.repository;

import com.example.todoshpp.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 */

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String name);
}
