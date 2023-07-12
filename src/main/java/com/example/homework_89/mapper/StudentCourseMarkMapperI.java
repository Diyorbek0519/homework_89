package com.example.homework_89.mapper;

import com.example.homework_89.entity.CourseEntity;
import com.example.homework_89.entity.StudentEntity;

import java.time.LocalDateTime;

public interface StudentCourseMarkMapperI {
    Integer getId();

     StudentEntity getStudentId();
     CourseEntity getCourseId();
     Double getMark();
     LocalDateTime getCreatedDate();
}
