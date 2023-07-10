package com.example.homework_89.repository;

import com.example.homework_89.entity.CourseEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

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

    @Query("from CourseEntity as s where s.id=:id")
    CourseEntity getById(@Param("id") Integer id);
    @Query("from CourseEntity ")
    List<CourseEntity> getAll();

    @Transactional
    @Modifying
    @Query("update CourseEntity as s set s.price=:price, s.duration=:duration where s.id=:id")
    int update(@Param("price") Double price, @Param("duration") Integer duration,@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("delete from CourseEntity as s where s.id=:id")
    int delete(@Param("id") Integer id);

    @Query("from CourseEntity as s where s.name=:name")
    List<CourseEntity> getCourseEntitiesByName(@Param("name") String name);

    @Query("from CourseEntity as s where s.price=:price")
    List<CourseEntity> getCourseEntitiesByPrice(@Param("price") Double price);
    @Query("from CourseEntity as s where s.duration=:d")
    List<CourseEntity> getCourseEntitiesByDuration(@Param("d") Integer duration);

    @Query("from CourseEntity as s where s.price between :p1 and :p2")
    List<CourseEntity> getCourseEntityByPrice(@Param("p1") Double p1, @Param("p2") Double p2);

    @Query("from CourseEntity as s where s.createdDate between :t1 and :t2")
    List<CourseEntity> getCourseEntityByCreatedDate(@Param("t1") LocalDateTime t1,@Param("t2") LocalDateTime t2 );




}
