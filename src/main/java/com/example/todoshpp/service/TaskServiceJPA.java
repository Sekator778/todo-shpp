package com.example.todoshpp.service;

import com.example.todoshpp.model.TaskEntity;
import com.example.todoshpp.model.attribut.Status;
import com.example.todoshpp.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

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

    // Save
    public TaskEntity newTaskEntity(@Valid @RequestBody TaskEntity newTaskEntity) {
        log.info("new TaskEntity was created and saved ");
        return repository.save(newTaskEntity);
    }

    // Find
    public TaskEntity findOne(@PathVariable @Min(1) Integer id) {
        log.info("task findOne used");
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.valueOf(id)));
    }

    // Save or update
    public TaskEntity saveOrUpdate(@RequestBody TaskEntity newTaskEntity, @PathVariable Integer id) {
        log.info("saveOrUpdate started");
        return repository.findById(id)
                .map(x -> {
                    x.setDescription(newTaskEntity.getDescription());
                    x.setStatus(newTaskEntity.getStatus());
                    x.setDone(newTaskEntity.getDone());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newTaskEntity.setId(id);
                    return repository.save(newTaskEntity);
                });
    }

    // update status only
    public TaskEntity patch(@RequestBody Map<String, String> update, @PathVariable Integer id) {
        log.info("patch started");
        return repository.findById(id)
                .map(x -> {
// TODO  fix status logic
                    String status = update.get("status");
                    if (!StringUtils.isEmpty(status)) {
                        x.setStatus(Status.PLANNED);
                        // better create a custom method to update a value = :newValue where id = :id
                        return repository.save(x);
                    } else {
                        throw new NotFoundException(String.valueOf(update.keySet()));
                    }
                })
                .orElseThrow(() -> new NotFoundException(String.valueOf(id)));
    }

    public void deleteTaskEntity(@PathVariable Integer id) {
        log.info("task with id - {} was delete", id);
        repository.deleteById(id);
    }
}