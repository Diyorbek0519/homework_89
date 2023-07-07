package com.example.homework_89.repository;

import com.example.homework_89.entity.StudentCourseMarkEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentCourseMarkRepocitory extends CrudRepository<StudentCourseMarkEntity,Integer>,
        PagingAndSortingRepository<StudentCourseMarkEntity,Integer> {

}
