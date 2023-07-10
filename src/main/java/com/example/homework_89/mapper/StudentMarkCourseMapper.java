package com.example.homework_89.mapper;

import com.example.homework_89.dto.CourseDTO;
import com.example.homework_89.dto.StudentDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class StudentMarkCourseMapper {
    private Integer id;
    private Integer studentId;
    private String studentName;
    private String studentSurname;
    private Integer courseId;
    private String courseName;
    private Double mark;
    private LocalDateTime createdDate;
}
