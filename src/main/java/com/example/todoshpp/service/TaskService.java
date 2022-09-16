package com.example.todoshpp.service;

import com.example.todoshpp.model.TaskDTO;
import com.example.todoshpp.model.TaskEntity;
import com.example.todoshpp.model.attribut.Status;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<TaskEntity> findAll();

    ResponseEntity<?> save(TaskDTO taskDTO);

    Optional<TaskEntity> findOne(Integer id);

    TaskEntity saveOrUpdate(TaskEntity newTaskEntity, Integer id);

    TaskEntity patch(Status newStatus, Integer id);

    void deleteTaskEntity(Integer id);
}
