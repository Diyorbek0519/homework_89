package com.example.homework_89.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentCourseMarkFilterDTO {
    private Integer studentId;
    private Integer courseId;
    private Double markFrom;
    private Double markTo;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;
    private String studentName;
    private String courseName;
}
