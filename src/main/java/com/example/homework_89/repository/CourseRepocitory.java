package com.example.homework_89.repository;

import com.example.homework_89.entity.CourseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CourseRepocitory extends CrudRepository<CourseEntity,Integer>,
        PagingAndSortingRepository<CourseEntity,Integer> {
    List<CourseEntity> getByName(String name);
    List<CourseEntity> getByPrice(Double price);
    List<CourseEntity> getByDuration(Integer duration);
    List<CourseEntity> getByPriceBetween(Double pr1, Double pr2);
    List<CourseEntity> getByCreatedDateBetween(LocalDateTime t1, LocalDateTime t2);
    List<CourseEntity> findByPrice(Pageable pageable,Double price);
    List<CourseEntity> findByPriceBetween(Pageable pageable, Double price1, Double price2);


}
