package com.example.homework_89.repository;

import com.example.homework_89.entity.StudentEntity;
import com.example.homework_89.enums.Gender;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<StudentEntity,Integer>,
        PagingAndSortingRepository<StudentEntity,Integer> {
    List<StudentEntity> getByName(String name);
    List<StudentEntity> getBySurname(String surname);
    List<StudentEntity> getByLevel(Integer level);
    List<StudentEntity> getByAge(Integer age);
    List<StudentEntity> getByGender(Gender gender);
    List<StudentEntity> getStudentEntityByCreatedDateBetween(LocalDateTime t1, LocalDateTime t2);
    List<StudentEntity> findByLevel(Pageable pageable, Integer level);
    List<StudentEntity> findByGender(Pageable pageable, Gender gender);

    @Query("from StudentEntity")
    List<StudentEntity> getAll();

    @Query("from StudentEntity as s where s.id=:id")
    StudentEntity getStudentById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update StudentEntity as s set s.name=:name, s.surname=:surname where s.id=:id")
    int updateNameAndSurname( @Param("id") Integer id,@Param("name") String name, @Param("surname") String surname);
    @Transactional
    @Modifying
    @Query("delete from StudentEntity as s where s.id=:id")
    int delete(@Param("id") Integer id);

    @Query("from StudentEntity as s where s.name=:name")
    List<StudentEntity> getStudentEntityByName(@Param("name") String name);

    @Query("from StudentEntity as s where s.surname=:surname")
    List<StudentEntity> getStudentEntityBySurname(@Param("surname") String name);

    @Query("from StudentEntity as s where s.level=:level")
    List<StudentEntity> getStudentEntityByLevel(@Param("level") Integer level);

    @Query("from StudentEntity as s where s.age=:age")
    List<StudentEntity> getStudentEntityByAge(@Param("age") Integer age);

    @Query("from StudentEntity as s where s.gender=:gender")
    List<StudentEntity> getStudentEntityByGender(@Param("gender") Gender gender);

    @Query("from StudentEntity as s where s.createdDate between :t1 and :t2")
    List<StudentEntity> getStudentEntityByCreatedDate(@Param("t1") LocalDateTime t1, @Param("t2") LocalDateTime t2);



}
