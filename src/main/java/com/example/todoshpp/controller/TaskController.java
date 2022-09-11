package com.example.todoshpp.controller;

import com.example.todoshpp.model.TaskEntity;
import com.example.todoshpp.model.attribut.Status;
import com.example.todoshpp.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final Logger log = LoggerFactory.getLogger(TaskController.class);
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @Operation(summary = "controller for work with all tasks")
    @ApiResponses(value = {@ApiResponse(description = "All good done")})
    // Find
    @GetMapping()
    public ResponseEntity<Iterable<TaskEntity>> findAll() {
        log.info("method get All from controller used");
        return ResponseEntity.ok(service.findAll());
    }

    // Find by id or not found response
    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> findOne(@PathVariable @Min(1) Integer id) {
        log.info("task findOne used");
        Optional<TaskEntity> task = service.findOne(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Save
    @Operation(summary = "controller for create and save to repository task")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> newTaskEntity(@Validated @RequestBody TaskEntity newTaskEntity) {
        log.info("controller init method newTaskEntity with data -  {}", newTaskEntity);
        return service.save(newTaskEntity);
    }


    // Save or update
    @PutMapping("/{id}")
    TaskEntity saveOrUpdate(@RequestBody TaskEntity newTaskEntity, @PathVariable Integer id) {
        log.info("saveOrUpdate started");
        return service.saveOrUpdate(newTaskEntity, id);
    }

    // update status only
    @PatchMapping("/{id}")
    TaskEntity patch(@Validated @RequestBody Status update, @PathVariable Integer id) {
        log.info("patch started");
        return service.patch(update, id);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void deleteTaskEntity(@PathVariable Integer id) {
        log.info("task with id - {} was delete", id);
        service.deleteTaskEntity(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.info("method handleValidationExceptions");
        return errors;
    }
}