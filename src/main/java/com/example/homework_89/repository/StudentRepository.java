package com.example.homework_89.repository;

import com.example.homework_89.entity.StudentEntity;
import com.example.homework_89.enums.Gender;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


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


}
