package com.example.homework_89.service;

import com.example.homework_89.dto.StudentDTO;
import com.example.homework_89.entity.StudentEntity;
import com.example.homework_89.enums.Gender;
import com.example.homework_89.exception.AppBadRequestException;
import com.example.homework_89.exception.ItemNotFoundException;
import com.example.homework_89.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public StudentDTO add(StudentDTO dto) {
        check(dto); // validate inputs

        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setGender(dto.getGender());
        entity.setAge(dto.getAge());
        entity.setLevel(dto.getLevel());
        entity.setCreatedDate(LocalDateTime.now());
        studentRepository.save(entity);
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        return dto;
    }

   /* public List<StudentDTO> addAll(List<StudentDTO> list) {
        for (StudentDTO dto : list) {
            StudentEntity entity = new StudentEntity();
            entity.setName(dto.getName());
            entity.setSurname(dto.getSurname());
            entity.setAge(dto.getAge());
            entity.setCreatedDate(LocalDateTime.now());
            studentRepository.save(entity);
            dto.setId(entity.getId());
        }
        return list;
    }*/

    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentDTO dto =toDTO(entity);
            dtoList.add(dto);
        });
        return dtoList;
    }

    public StudentDTO getById(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw  new ItemNotFoundException("Student not found");
        }
        StudentEntity entity = optional.get();
        return toDTO(entity);

//        Optional<StudentEntity> optional = studentRepository.findById(id);
//        return optional.map(entity -> toDTO(entity)).orElseThrow(() -> {
//            throw new ItemNotFoundException("Student not found");
//        });

    }


    public Boolean delete(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw  new ItemNotFoundException("Student not found");
        }
        studentRepository.deleteById(id);
        return true;
    }

    public Boolean update(Integer id, StudentDTO student) {
        check(student);
        if(studentRepository.existsById(id)){
           Optional<StudentEntity> optional =studentRepository.findById(id);
           if(optional.isEmpty()){
               throw new ItemNotFoundException("Not found");
           }
           StudentEntity studentEntity =optional.get();
           studentEntity.setName(student.getName());
           studentEntity.setSurname(student.getSurname());
           studentRepository.save(studentEntity);
            return true;
        }
        return false;
    }
    public StudentDTO toDTO(StudentEntity entity){
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setGender(entity.getGender());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public StudentEntity toEntity(StudentDTO studentDTO){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(studentDTO.getId());
        studentEntity.setName(studentDTO.getName());
        studentEntity.setSurname(studentDTO.getSurname());
        studentEntity.setAge(studentDTO.getAge());
        studentDTO.setLevel(studentDTO.getLevel());
        studentEntity.setGender(studentDTO.getGender());
        studentEntity.setCreatedDate(studentDTO.getCreatedDate());
        return studentEntity;
    }



    private void check(StudentDTO student) {
        if (student.getName() == null || student.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }
        if (student.getSurname() == null || student.getSurname().isBlank()) {
            throw new AppBadRequestException("Surname qani?");
        }
    }

    public List<StudentDTO> getByName(String name) {
        List<StudentEntity> studentList =studentRepository.getByName(name);
        if(studentList.isEmpty()){
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList =new LinkedList<>();
        studentList.forEach(st->{
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }
    public List<StudentDTO> getBySurname(String surname) {
        List<StudentEntity> studentList =studentRepository.getBySurname(surname);
        if(studentList.isEmpty()){
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList =new LinkedList<>();
        studentList.forEach(st->{
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }
    public List<StudentDTO> getByLevel(Integer level) {
        List<StudentEntity> studentList =studentRepository.getByLevel(level);
        if(studentList.isEmpty()){
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList =new LinkedList<>();
        studentList.forEach(st->{
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }
    public List<StudentDTO> getByAge(Integer age) {
        List<StudentEntity> studentList =studentRepository.getByAge(age);
        if(studentList.isEmpty()){
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList =new LinkedList<>();
        studentList.forEach(st->{
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }
    public List<StudentDTO> getByGender(Gender gender) {
        List<StudentEntity> studentList =studentRepository.getByGender(gender);
        if(studentList.isEmpty()){
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList =new LinkedList<>();
        studentList.forEach(st->{
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }

    public List<StudentDTO> getStudentEntityByCreatedDateBetween(LocalDate date) {
        List<StudentEntity> studentList=studentRepository.getStudentEntityByCreatedDateBetween(date.atStartOfDay(),date.plusDays(1).atStartOfDay());
        if(studentList.isEmpty()){
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList =new LinkedList<>();
        studentList.forEach(st->{
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }


    public List<StudentEntity> getStudentsBetweenDates(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.plusDays(1).atStartOfDay().minusNanos(1);
        return studentRepository.findByCreatedDateBetween(startOfDay, endOfDay);
    }
}
