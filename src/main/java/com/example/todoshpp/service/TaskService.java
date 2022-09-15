package com.example.todoshpp.service;

import com.example.todoshpp.model.TaskEntity;
import com.example.todoshpp.model.attribut.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TaskService {
    List<TaskEntity> findAll();

    ResponseEntity<?> save(TaskEntity newTaskEntity);

    Optional<TaskEntity> findOne(Integer id);

    TaskEntity saveOrUpdate(TaskEntity newTaskEntity, Integer id);
    TaskEntity patch(Status newStatus, Integer id);

    void deleteTaskEntity(Integer id);
}
