package com.example.homework_89.repository;

import com.example.homework_89.entity.StudentCourseMarkEntity;
import com.example.homework_89.entity.StudentEntity;
import com.example.homework_89.service.StudentCourseMarkService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentCourseMarkRepocitory extends CrudRepository<StudentCourseMarkEntity,Integer>,
        PagingAndSortingRepository<StudentCourseMarkEntity,Integer> {
    List<StudentCourseMarkEntity> getByStudentIdAndCreatedDateBetween(Integer id, LocalDateTime time1, LocalDateTime t2);
    List<StudentCourseMarkEntity> getByStudentIdOrderByCreatedDateDesc(Integer id);
    List<StudentCourseMarkEntity> getByStudentIdAndCourseIdOrderByCreatedDateDesc(Integer studentId, Integer courseId);
    List<StudentCourseMarkEntity> findTop3ByStudentIdOrderByMarkDesc(Integer id);
    StudentCourseMarkEntity findFirstByStudentIdOrderByCreatedDateAsc(Integer id);
    StudentCourseMarkEntity findFirstByCourseNameAndStudentIdOrderByCreatedDateAsc(String courseName,Integer id);
    StudentCourseMarkEntity findFirstByCourseNameAndStudentIdOrderByMarkDesc(String courseName, Integer id);
    Page<StudentCourseMarkEntity> findByStudentId(Integer id, Pageable pageable);
    Page<StudentCourseMarkEntity> findByCourseId(Integer id, Pageable pageable);

}
