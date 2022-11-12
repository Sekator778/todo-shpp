package com.example.todoshpp.model.inheritance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Created by user on 12 лис, 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Developer extends User {
    private String repository;

    @Builder
    public Developer(Long id, String name, String city, String phone, String repository) {
        super(id, name, city, phone);
        this.repository = repository;
    }
}
