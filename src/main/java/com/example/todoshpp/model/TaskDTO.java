package com.example.todoshpp.model;

import com.example.todoshpp.model.attribut.Status;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaskDTO {
    @NotNull
    @NotBlank(message = "{description.text}")
    @Size(min = 3, max = 60, message = "{desc-length.text}")
    private String description;
    @NotNull
    private String done;
    @NotNull(message = "{description.text}")
    @Enumerated(EnumType.STRING)
    private Status status;

    public TaskDTO(String description, String done, Status status) {
        this.description = description;
        this.done = done;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
