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
import java.util.Optional;

@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepocitory studentCourseMarkRepocitory;
    public StudentCourseMarkDTO add(StudentCourseMarkDTO dto){
        check(dto);
        StudentEntity student = new StudentEntity();
        student.setId(dto.getStudentId());
        CourseEntity course = new CourseEntity();
        course.setId(dto.getCourseId());

        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        entity.setStudent(student);
        entity.setCourse(course);
        entity.setMark(dto.getMark());
        entity.setCreatedDate(LocalDateTime.now());
        studentCourseMarkRepocitory.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public Boolean update(StudentCourseMarkDTO dto, Integer id){
        check(dto);
        Optional<StudentCourseMarkEntity> optional =studentCourseMarkRepocitory.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Not found");
        }
        StudentCourseMarkEntity entity =optional.get();
        entity.setMark(dto.getMark());
        studentCourseMarkRepocitory.save(entity);
        return true;
    }


    public boolean check(StudentCourseMarkDTO studentCourseMarkDTO){
        if(studentCourseMarkDTO.getMark()==null ){
            throw new ItemNotFoundException("Mark is null");
        }
        return true;
    }



}
