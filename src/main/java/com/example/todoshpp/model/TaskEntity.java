package com.example.todoshpp.model;


import com.example.todoshpp.model.attribut.StatusAttributeConverter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * data model
 */
@Entity
@Table(name = "items")
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
    private String description;

    /**
     * date created task
     */
    @Column(name = "created")
    private LocalDate date;

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
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskEntity taskEntity = (TaskEntity) o;

        if (id != taskEntity.id) return false;
        if (description != null ? !description.equals(taskEntity.description) : taskEntity.description != null) return false;
        if (date != null ? !date.equals(taskEntity.date) : taskEntity.date != null) return false;
        if (done != null ? !done.equals(taskEntity.done) : taskEntity.done != null) return false;
        return status == taskEntity.status;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", done='" + done + '\'' +
                ", status=" + status +
                '}';
    }
}

