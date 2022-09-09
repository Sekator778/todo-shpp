package com.example.todoshpp.controller;

import com.example.todoshpp.model.TaskEntity;
import com.example.todoshpp.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TaskController {
    private final Logger log = LoggerFactory.getLogger(TaskController.class);
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @Operation(summary = "controller for work with all tasks")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All good done")})
    // Find
    @GetMapping("/tasks")
    List<TaskEntity> findAll() {
        return service.findAll();
    }

    // Save
    @Operation(summary = "controller for create and save to repository task")
    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    TaskEntity newTaskEntity(@Valid @RequestBody TaskEntity newTaskEntity) {
        return service.newTaskEntity(newTaskEntity);
    }

    // Find
    @GetMapping("/tasks/{id}")
    TaskEntity findOne(@PathVariable @Min(1) Integer id) {
        log.info("task findOne used");
        return service.findOne(id);
    }

    // Save or update
    @PutMapping("/tasks/{id}")
    TaskEntity saveOrUpdate(@RequestBody TaskEntity newTaskEntity, @PathVariable Integer id) {
        log.info("saveOrUpdate started");
        return service.saveOrUpdate(newTaskEntity, id);
    }

    // update status only
    @PatchMapping("/tasks/{id}")
    TaskEntity patch(@RequestBody Map<String, String> update, @PathVariable Integer id) {
        log.info("patch started");
        return service.patch(update, id);
    }

    @DeleteMapping("/tasks/{id}")
    void deleteTaskEntity(@PathVariable Integer id) {
        log.info("task with id - {} was delete", id);
        service.deleteTaskEntity(id);
    }
}