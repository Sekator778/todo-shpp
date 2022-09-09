package com.example.todoshpp.service;

import com.example.todoshpp.model.RoleEntity;
import com.example.todoshpp.repository.RoleRepository;
import com.example.todoshpp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

/**
 *
 */
@Service
public class RoleServiceJPA implements RoleService {
    private final RoleRepository repository;

    private final Logger log = LoggerFactory.getLogger(RoleServiceJPA.class);
    @Autowired
    private Validator validator;

    public RoleServiceJPA(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<RoleEntity> findAll() {
        log.info("used method find all RoleService");
        return repository.findAll();
    }
    @Override
    public RoleEntity findIdRole(String roleName) {
        return null;
    }
}
