//package com.example.todoshpp.service;
//
//import com.example.todoshpp.model.PersonEntity;
//import com.example.todoshpp.repository.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import javax.validation.Validator;
//import java.net.URI;
//import java.util.Optional;
//import java.util.Set;
//
///**
// *
// */
//@Service
//public class UserServiceJPA implements UserService {
//    private final UserRepository repository;
//
//    private final Logger log = LoggerFactory.getLogger(UserServiceJPA.class);
//    @Autowired
//    private Validator validator;
//
//    @Autowired
//    public UserServiceJPA(UserRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public Iterable<PersonEntity> findAll() {
//        return repository.findAll();
//    }
//
//    @Override
//    public ResponseEntity<?> save(PersonEntity personEntity) {
//        Set<ConstraintViolation<PersonEntity>> violations = validator.validate(personEntity);
//        if (!violations.isEmpty()) {
//            StringBuilder stringBuilder = new StringBuilder();
//            for (ConstraintViolation<PersonEntity> constraintViolation : violations) {
//                stringBuilder.append(constraintViolation.getMessage());
//            }
//            log.info("the model was not inserted into the database because it did not pass validation");
//            throw new ConstraintViolationException("Error occurred: " + stringBuilder, violations);
//        }
//        log.info("model save to DB with id {}", personEntity.getId());
//        PersonEntity result = repository.save(personEntity);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(result.getId())
//                .toUri();
//        log.info("success created model");
//        return ResponseEntity.created(location)
//                .build();
//    }
//
//    @Override
//    public Optional<PersonEntity> findById(int id) {
//        log.info("find by id method UserService");
//        return repository.findById(id);
//    }
//}
