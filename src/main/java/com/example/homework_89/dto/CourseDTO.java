package com.example.homework_89.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CourseDTO {
    private Integer id;
    private String name;
    private Double price;
    private Integer duration;
    private LocalDateTime createdDate;

    public CourseDTO() {
    }

    public CourseDTO(String name) {
        this.name = name;
    }
}
