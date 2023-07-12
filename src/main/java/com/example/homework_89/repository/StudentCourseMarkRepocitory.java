package com.example.homework_89.repository;

import com.example.homework_89.entity.StudentCourseMarkEntity;
import com.example.homework_89.entity.StudentEntity;
import com.example.homework_89.mapper.StudentCourseMarkMapperI;
import com.example.homework_89.service.StudentCourseMarkService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

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
    @Transactional
    @Modifying
    @Query("update StudentCourseMarkEntity as s set s.mark=:mark where s.id=:id")
    int update(@Param("mark") Double mark, @Param("id") Integer id);

    @Query(value = "select s.id,s.student_id, s.course_id, s.mark, s.created_date from mark as s where s.id=:id", nativeQuery = true)
    StudentCourseMarkMapperI getStudentCourseMarkById(@Param("id") Integer id);


}
