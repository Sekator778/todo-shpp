package com.example.todoshpp.service;

import com.example.todoshpp.model.RoleEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface RoleService {

    Iterable<RoleEntity> findAll();
    RoleEntity findIdRole(String roleName);
}
