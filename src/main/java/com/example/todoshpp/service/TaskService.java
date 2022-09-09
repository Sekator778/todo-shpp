package com.example.todoshpp.service;

import com.example.todoshpp.model.TaskEntity;
import com.example.todoshpp.model.attribut.Status;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

public interface TaskService {
    List<TaskEntity> findAll();

    TaskEntity newTaskEntity(TaskEntity newTaskEntity);

    TaskEntity findOne(Integer id);

    TaskEntity saveOrUpdate(TaskEntity newTaskEntity, Integer id);

    TaskEntity patch(Map<String, String> update, Integer id);

    void deleteTaskEntity(Integer id);
}
