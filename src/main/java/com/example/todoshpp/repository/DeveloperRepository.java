package com.example.todoshpp.repository;

import com.example.todoshpp.model.inheritance.Developer;
import org.springframework.data.repository.CrudRepository;

public interface DeveloperRepository extends CrudRepository<Developer, Long> {

    Developer findFirstByRepository(String repository);
}