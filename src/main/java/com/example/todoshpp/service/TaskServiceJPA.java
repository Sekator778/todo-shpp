package com.example.todoshpp.service;

import com.example.todoshpp.model.TaskEntity;
import com.example.todoshpp.model.attribut.Status;
import com.example.todoshpp.model.attribut.StatusAttributeConverter;
import com.example.todoshpp.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
public class TaskServiceJPA implements TaskService {
    private final TaskRepository repository;

    private final Logger log = LoggerFactory.getLogger(TaskServiceJPA.class);

    @Autowired
    public TaskServiceJPA(TaskRepository repository) {
        this.repository = repository;
    }

    // Find
    public List<TaskEntity> findAll() {
        log.info("task findAll used");
        return repository.findAll();
    }

    // Find by id

    public Optional<TaskEntity> findOne(@PathVariable @Min(1) Integer id) {
        log.info("task findOne used");
        return repository.findById(id);
    }

    // Save
    public ResponseEntity<TaskEntity> save(@Valid @RequestBody TaskEntity newTaskEntity) {
        newTaskEntity.setModify(LocalDateTime.now());
        TaskEntity save = repository.save(newTaskEntity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        log.info("new TaskEntity was created and saved ");
        return ResponseEntity.created(location).build();
    }

    // Save or update
    public TaskEntity saveOrUpdate(@RequestBody TaskEntity newTaskEntity, @PathVariable Integer id) {
        log.info("saveOrUpdate started");
        return repository.findById(id)
                .map(x -> {
                    x.setDescription(newTaskEntity.getDescription());
                    x.setStatus(newTaskEntity.getStatus());
                    x.setDone(newTaskEntity.getDone());
                    x.setModify(LocalDateTime.now());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newTaskEntity.setId(id);
                    return repository.save(newTaskEntity);
                });
    }

    // update status only
    @Override
    public TaskEntity patch(Status newStatus, Integer id) {
        TaskEntity result = null;
        StatusAttributeConverter converter = new StatusAttributeConverter();
        Integer idNewStatus = converter.convertToDatabaseColumn(newStatus);
        log.info("patch started into service");
        Optional<TaskEntity> byId = repository.findById(id);
        if (byId.isPresent()) {
            result = byId.get();
        }
        assert result != null;
        Status status1 = result.getStatus();
        Integer idOldStatus = converter.convertToDatabaseColumn(status1);
        if (newStatus.equals(Status.CANCELLED)) {
            log.info("cansel tasks");
            result.setStatus(Status.CANCELLED);
            result.setModify(LocalDateTime.now());
        } else if (idNewStatus <= idOldStatus || idOldStatus == 4) {
            log.info("wrong status or task cancelled");
        } else {
            log.info("status was update");
            result.setStatus(newStatus);
            result.setModify(LocalDateTime.now());
        }
        repository.save(result);
        log.info("patch used successful");
        return result;
    }

    public void deleteTaskEntity(@PathVariable Integer id) {
        log.info("task with id - {} was delete", id);
        repository.deleteById(id);
    }
}