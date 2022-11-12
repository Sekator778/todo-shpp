package com.example.todoshpp.repository;

import com.example.todoshpp.model.inheritance.User;
import org.springframework.data.repository.CrudRepository;

public interface UserFatherRepository extends CrudRepository<User, Long> {
}