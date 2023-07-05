package com.example.homework_89.dto;

import com.example.homework_89.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private Integer level;
    private Integer age;
    private Gender gender;
    private LocalDateTime createdDate;
}
