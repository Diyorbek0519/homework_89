package com.example.homework_89.repository;

import com.example.homework_89.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepocitory extends CrudRepository<CourseEntity,Integer> {
    List<CourseEntity> getByName(String name);
    List<CourseEntity> getByPrice(Double price);
    List<CourseEntity> getByDuration(Integer duration);

}
