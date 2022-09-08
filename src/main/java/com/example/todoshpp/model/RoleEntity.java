package com.example.todoshpp.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "j_role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public static RoleEntity of(String name) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.name = name;
        return roleEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleEntity roleEntity = (RoleEntity) o;
        return id == roleEntity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}