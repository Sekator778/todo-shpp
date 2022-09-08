package com.example.todoshpp.model;


import javax.persistence.*;
import java.time.LocalDate;

/**
 * модель данных
 */
@Entity
@Table(name = "items")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * identity task
     */
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
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * constructor for JPA
     */
    public Task() {
    }


    public Task(String description, LocalDate date, String done) {
        this.description = description;
        this.date = date;
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        if (id != task.id) {
            return false;
        }
        if (description != null ? !description.equals(task.description) : task.description != null) {
            return false;
        }
        if (date != null ? !date.equals(task.date) : task.date != null) {
            return false;
        }
        return done != null ? done.equals(task.done) : task.done == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (done != null ? done.hashCode() : 0);
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

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", description='" + description + '\'' + ", date=" + date + ", done='" + done + '\'' + '}';
    }
}

