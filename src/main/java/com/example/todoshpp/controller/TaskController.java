package com.example.todoshpp.controller;

import com.example.todoshpp.model.TaskDTO;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
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
    public ResponseEntity<?> newTaskEntity(@Validated @RequestBody TaskDTO taskDTO) {
        log.info("controller init method newTaskEntity with data -  {}", taskDTO);
        return service.save(taskDTO);
    }


    // Save or update
    @Operation(summary = "controller for save or update to repository task")
    @PutMapping("/{id}")
    TaskEntity saveOrUpdate(@RequestBody TaskEntity newTaskEntity, @PathVariable Integer id) {
        log.info("saveOrUpdate started");
        return service.saveOrUpdate(newTaskEntity, id);
    }

    // update status only
    @Operation(summary = "controller for modify task")
    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> patch(@Validated @RequestBody Status update, BindingResult result, @PathVariable Integer id) {
        log.info("patch started");
        if (result.hasErrors()) {
            log.error("method patch validate error");
            result.getAllErrors()
                    .forEach(objectError -> {
                        log.error("error name {}", ((FieldError) objectError).getField());
                        log.error("error message {}", objectError.getDefaultMessage());
                    });
            return ResponseEntity.badRequest().build();
        } else {
            log.info("patch success");
            return ResponseEntity.ok().body(service.patch(update, id));
        }
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "controller for delete task")
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