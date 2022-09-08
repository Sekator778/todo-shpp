package com.example.todoshpp.service;

import com.example.todoshpp.model.UserEntity;
import com.example.todoshpp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.net.URI;
import java.util.Set;

/**
 *
 */
@Service
public class UserServiceJPA implements UserService {
    private final UserRepository repository;

    private final Logger LOG = LoggerFactory.getLogger(UserServiceJPA.class);
    @Autowired
    private Validator validator;

    @Autowired
    public UserServiceJPA(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<UserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<?> save(UserEntity userEntity) {
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        if (!violations.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (ConstraintViolation<UserEntity> constraintViolation : violations) {
                stringBuilder.append(constraintViolation.getMessage());
            }
            LOG.info("the model was not inserted into the database because it did not pass validation");
            throw new ConstraintViolationException("Error occurred: " + stringBuilder, violations);
        }
        LOG.info("model save to DB with id {}", userEntity.getId());
        UserEntity result = repository.save(userEntity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId())
                .toUri();
        LOG.info("success created book");
        return ResponseEntity.created(location)
                .build();
    }
}
