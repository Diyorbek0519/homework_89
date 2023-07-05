package com.example.homework_89.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Double price;
    @Column(name = "duration",nullable = false)
    private Integer duration;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
