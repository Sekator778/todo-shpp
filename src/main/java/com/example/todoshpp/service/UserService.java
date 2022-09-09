package com.example.todoshpp.service;

import com.example.todoshpp.model.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {

    Iterable<UserEntity> findAll();

    ResponseEntity<?> save(UserEntity userEntity);

    Optional<UserEntity> findById(int id);
}
