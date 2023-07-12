package com.example.homework_89.service;

import com.example.homework_89.dto.StudentDTO;
import com.example.homework_89.dto.StudentFilterDTO;
import com.example.homework_89.entity.StudentEntity;
import com.example.homework_89.enums.Gender;
import com.example.homework_89.exception.AppBadRequestException;
import com.example.homework_89.exception.ItemNotFoundException;
import com.example.homework_89.repository.CustomStudentRepository;
import com.example.homework_89.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CustomStudentRepository customStudentRepository;

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
        Iterable<StudentEntity> iterable = studentRepository.getAll();
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentDTO dto = toDTO(entity);
            dtoList.add(dto);
        });
        return dtoList;
    }

    public StudentDTO getById(Integer id) {
        // Optional<StudentEntity> optional = studentRepository.findById(id);
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Student not found");
        }
        StudentEntity entity = optional.get();
        return toDTO(entity);

//        Optional<StudentEntity> optional = studentRepository.findById(id);
//        return optional.map(entity -> toDTO(entity)).orElseThrow(() -> {
//            throw new ItemNotFoundException("Student not found");
//        });

    }

    public StudentDTO getById2(Integer id) {
        StudentEntity entity = studentRepository.getStudentById(id);
        if(entity==null){
            throw new ItemNotFoundException("Student not found");
        }
        return toDTO(entity);
    }


    public Boolean delete(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Student not found");
        }
        studentRepository.deleteById(id);
        return true;
    }
    public Boolean delete2(Integer id) {
        int effectedRows=studentRepository.delete(id);
        return effectedRows!=0;
    }

    public Boolean update(Integer id, StudentDTO student) {
        check(student);
        if (studentRepository.existsById(id)) {
            Optional<StudentEntity> optional = studentRepository.findById(id);
            if (optional.isEmpty()) {
                throw new ItemNotFoundException("Not found");
            }
            StudentEntity studentEntity = optional.get();
            studentEntity.setName(student.getName());
            studentEntity.setSurname(student.getSurname());
            studentRepository.save(studentEntity);
            return true;
        }
        return false;
    }
    public Boolean update2(Integer id, StudentDTO student) {
        int effectedRows = studentRepository.updateNameAndSurname(id, student.getName(), student.getSurname());
        return effectedRows != 0;
    }

    public StudentDTO toDTO(StudentEntity entity) {
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

    public StudentEntity toEntity(StudentDTO studentDTO) {
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
        List<StudentEntity> studentList = studentRepository.getByName(name);
        if (studentList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        studentList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }
    public List<StudentDTO> getByName2(String name) {
        List<StudentEntity> studentList = studentRepository.getStudentEntityByName(name);
        if (studentList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        studentList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }



    public List<StudentDTO> getBySurname(String surname) {
        List<StudentEntity> studentList = studentRepository.getBySurname(surname);
        if (studentList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        studentList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }
    public List<StudentDTO> getBySurname2(String surname) {
        List<StudentEntity> studentList = studentRepository.getStudentEntityBySurname(surname);
        if (studentList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        studentList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }


    public List<StudentDTO> getByLevel(Integer level) {
        List<StudentEntity> studentList = studentRepository.getByLevel(level);
        if (studentList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        studentList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }
    public List<StudentDTO> getByLevel2(Integer level) {
        List<StudentEntity> studentList = studentRepository.getStudentEntityByLevel(level);
        if (studentList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        studentList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }


    public List<StudentDTO> getByAge(Integer age) {
        List<StudentEntity> studentList = studentRepository.getByAge(age);
        if (studentList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        studentList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }
    public List<StudentDTO> getByAge2(Integer age) {
        List<StudentEntity> studentList = studentRepository.getStudentEntityByAge(age);
        if (studentList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        studentList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }

    public List<StudentDTO> getByGender(Gender gender) {
        List<StudentEntity> studentList = studentRepository.getByGender(gender);
        if (studentList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        studentList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }
    public List<StudentDTO> getByGender2(Gender gender) {
        List<StudentEntity> studentList = studentRepository.getStudentEntityByGender(gender);
        if (studentList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        studentList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }

    public List<StudentDTO> getStudentEntityByGivenDate(LocalDate date) {
        LocalDateTime t1 = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime t2 = LocalDateTime.of(date, LocalTime.MAX);
        List<StudentEntity> studentList = studentRepository.getStudentEntityByCreatedDateBetween(t1, t2);
        if (studentList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        studentList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }
    public List<StudentDTO> getStudentEntityByGivenDate2(LocalDate date) {
        LocalDateTime t1 = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime t2 = LocalDateTime.of(date, LocalTime.MAX);
        List<StudentEntity> studentList = studentRepository.getStudentEntityByCreatedDateBetween(t1, t2);
        if (studentList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        studentList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }

    public List<StudentDTO> getStudentEntityByGivenDates(LocalDate from, LocalDate to) {
        LocalDateTime t1 = LocalDateTime.of(from, LocalTime.MIN);
        LocalDateTime t2 = LocalDateTime.of(to, LocalTime.MAX);
        List<StudentEntity> entityList = studentRepository.getStudentEntityByCreatedDate(t1, t2);
        if (entityList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        entityList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }
    public List<StudentDTO> getStudentEntityByGivenDates2(LocalDate from, LocalDate to) {
        LocalDateTime t1 = LocalDateTime.of(from, LocalTime.MIN);
        LocalDateTime t2 = LocalDateTime.of(to, LocalTime.MAX);
        List<StudentEntity> entityList = studentRepository.getStudentEntityByCreatedDate(t1, t2);
        if (entityList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        entityList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }

    public List<StudentDTO> studentPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentEntity> entityList = studentRepository.findAll(pageable);
        //System.out.println(entityList.getTotalElements());
        //System.out.println(entityList.getTotalPages());
        if (entityList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        entityList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }

    public List<StudentDTO> paginationByLevel(int page, int size, Integer level) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        List<StudentEntity> entityList = studentRepository.findByLevel(pageable, level);
        if (entityList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        entityList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }

    public List<StudentDTO> paginationByGender(int page, int size, Gender gender) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        List<StudentEntity> entityList = studentRepository.findByGender(pageable, gender);
        if (entityList.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<StudentDTO> dtoList = new LinkedList<>();
        entityList.forEach(st -> {
            dtoList.add(toDTO(st));
        });
        return dtoList;
    }
    public List<StudentDTO> filter(StudentFilterDTO filterDTO,int page,int size){
        if(filterDTO==null){
            throw new ItemNotFoundException("Not found");
        }
        List<StudentEntity> entityList=customStudentRepository.filter(filterDTO,page,size);
        List<StudentDTO> dtoList=new LinkedList<>();
        entityList.forEach(t->{
            dtoList.add(toDTO(t));
        });
        return dtoList;
    }


}
