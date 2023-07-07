package com.example.homework_89.service;

import com.example.homework_89.dto.StudentCourseMarkDTO;
import com.example.homework_89.dto.StudentDTO;
import com.example.homework_89.entity.CourseEntity;
import com.example.homework_89.entity.StudentCourseMarkEntity;
import com.example.homework_89.entity.StudentEntity;
import com.example.homework_89.exception.ItemNotFoundException;
import com.example.homework_89.repository.StudentCourseMarkRepocitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepocitory studentCourseMarkRepocitory;
    public StudentCourseMarkDTO add(StudentCourseMarkDTO studentCourseMarkDTO){
        check(studentCourseMarkDTO);
        StudentCourseMarkEntity studentCourseMarkEntity =new StudentCourseMarkEntity();
        StudentCourseMarkEntity entity =toEntity(studentCourseMarkDTO);
        studentCourseMarkRepocitory.save(entity);
        studentCourseMarkDTO.setId(entity.getId());
        studentCourseMarkDTO.setCreatedDate(entity.getCreatedDate());
        return studentCourseMarkDTO;
    }
    public StudentCourseMarkEntity toEntity(StudentCourseMarkDTO studentCourseMarkDTO){
        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(studentCourseMarkDTO.getStudentId());
        entity.setStudentId(studentEntity);
        CourseEntity courseEntity=new CourseEntity();
        courseEntity.setId(studentCourseMarkDTO.getId());
        entity.setStudentId(studentEntity);
        entity.setCourseId(courseEntity);
        entity.setMark(studentCourseMarkDTO.getMark());
        entity.setCreatedDate(LocalDateTime.now());
        return entity;
    }
    public boolean check(StudentCourseMarkDTO studentCourseMarkDTO){
        if(studentCourseMarkDTO.getMark()==null ){
            throw new ItemNotFoundException("Mark or createdDate is null");
        }
        return true;
    }


}
