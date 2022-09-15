package com.example.todoshpp.model;

import com.example.todoshpp.model.attribut.Status;
import com.example.todoshpp.model.attribut.StatusAttributeConverter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * data model
 */
@Slf4j
@Entity
@Table(name = "tasks")
public class TaskEntity {
    /**
     * identity task
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    /**
     * description current task
     */
    @Column(name = "description")
    @NotNull
    @NotBlank(message = "{description.text}")
    @Size(min = 3, max = 60, message = "{desc-length.text}")
    private String description;

    /**
     * date created task
     */
    @Column(name = "created")
    private LocalDateTime date;

    private LocalDateTime modify;

    /**
     * done - if выполнена, undone - else нет
     */
    @Column(name = "done")
    private String done;
    /**
     * stage task
     */
    @Convert(converter = StatusAttributeConverter.class)
    private Status status;

    /**
     * constructor for JPA
     */
    public TaskEntity() {
        log.info("task entity create constructor");
    }

    public TaskEntity(int id, String description, Status status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    @PrePersist
    void createdAt() {
        this.date = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", modify=" + modify +
                ", done='" + done + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskEntity task = (TaskEntity) o;

        if (id != task.id) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        if (date != null ? !date.equals(task.date) : task.date != null) return false;
        if (modify != null ? !modify.equals(task.modify) : task.modify != null) return false;
        if (done != null ? !done.equals(task.done) : task.done != null) return false;
        return status == task.status;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (modify != null ? modify.hashCode() : 0);
        result = 31 * result + (done != null ? done.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getModify() {
        return modify;
    }

    public void setModify(LocalDateTime modify) {
        this.modify = modify;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

