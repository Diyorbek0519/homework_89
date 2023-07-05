package com.example.homework_89.controller;

import com.example.homework_89.dto.StudentDTO;
import com.example.homework_89.entity.StudentEntity;
import com.example.homework_89.enums.Gender;
import com.example.homework_89.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO student) {
        StudentDTO response = studentService.add(student);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/all")
    public List<StudentDTO> all() {
        return studentService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.getById(id));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody StudentDTO student,
                                 @PathVariable("id") Integer id) {
        studentService.update(id, student);
        return ResponseEntity.ok(true);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        Boolean response = studentService.delete(id);
        if (response) {
            return ResponseEntity.ok("Student deleted");
        }
        return ResponseEntity.badRequest().body("Student Not Found");
    }
    @GetMapping(value = "/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name){
        return ResponseEntity.ok(studentService.getByName(name));
    }
    @GetMapping(value = "/surname/{surname}")
    public ResponseEntity<?> getBySurname(@PathVariable("surname") String surname){
        return ResponseEntity.ok(studentService.getBySurname(surname));
    }

    @GetMapping(value = "/level/{level}")
    public ResponseEntity<?> getByLevel(@PathVariable("level") Integer level){
        return ResponseEntity.ok(studentService.getByLevel(level));
    }
    @GetMapping(value = "/age/{age}")
    public ResponseEntity<?> getByAge(@PathVariable("age") Integer age){
        return ResponseEntity.ok(studentService.getByAge(age));
    }

    @GetMapping(value = "/gender/{gender}")
    public ResponseEntity<?> getByGender(@PathVariable("gender") Gender gender){
        return ResponseEntity.ok(studentService.getByGender(gender));
    }
    @GetMapping(value = "/created_date")
    public ResponseEntity<?> getStudentEntityByCreatedDateBetween(@RequestParam("created_date") LocalDate date){
        return ResponseEntity.ok(studentService.getStudentEntityByCreatedDateBetween(date));
    }
    @GetMapping("/date-range")
    public ResponseEntity<List<StudentEntity>> getStudentsBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<StudentEntity> studentList = studentService.getStudentsBetweenDates(startDate, endDate);
        return ResponseEntity.ok(studentList);
    }







}
