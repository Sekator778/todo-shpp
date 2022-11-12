package com.example.todoshpp.repository;

import com.example.todoshpp.model.inheritance.Manager;
import com.example.todoshpp.model.inheritance.User;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager, Long> {
}