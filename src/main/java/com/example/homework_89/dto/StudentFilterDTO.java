package com.example.homework_89.dto;
import com.example.homework_89.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentFilterDTO {
    private Integer id;
    private String name;
    private String surname;
    private Integer level;
    private Integer age;
    private Gender Gender;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;
}
