package com.example.homework_89.controller;

import com.example.homework_89.dto.StudentDTO;
import com.example.homework_89.dto.StudentFilterDTO;
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
    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<?> getById2(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.getById2(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody StudentDTO student,
                                 @PathVariable("id") Integer id) {
        studentService.update(id, student);
        return ResponseEntity.ok(true);
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@RequestBody StudentDTO student,
                                 @PathVariable("id") Integer id) {
        studentService.update2(id, student);
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
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete2(@PathVariable("id") Integer id) {
        Boolean response = studentService.delete2(id);
        if (response) {
            return ResponseEntity.ok("Student deleted");
        }
        return ResponseEntity.badRequest().body("Student Not Found");
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(studentService.getByName(name));
    }
    @GetMapping(value = "/name2/{name}")
    public ResponseEntity<?> getByName2(@PathVariable("name") String name) {
        return ResponseEntity.ok(studentService.getByName2(name));
    }

    @GetMapping(value = "/surname/{surname}")
    public ResponseEntity<?> getBySurname(@PathVariable("surname") String surname) {
        return ResponseEntity.ok(studentService.getBySurname(surname));
    }
    @GetMapping(value = "/surname2/{surname}")
    public ResponseEntity<?> getBySurname2(@PathVariable("surname") String surname) {
        return ResponseEntity.ok(studentService.getBySurname2(surname));
    }

    @GetMapping(value = "/level/{level}")
    public ResponseEntity<?> getByLevel(@PathVariable("level") Integer level) {
        return ResponseEntity.ok(studentService.getByLevel(level));
    }
    @GetMapping(value = "/level2/{level}")
    public ResponseEntity<?> getByLevel2(@PathVariable("level") Integer level) {
        return ResponseEntity.ok(studentService.getByLevel2(level));
    }

    @GetMapping(value = "/age/{age}")
    public ResponseEntity<?> getByAge(@PathVariable("age") Integer age) {
        return ResponseEntity.ok(studentService.getByAge(age));
    }
    @GetMapping(value = "/age2/{age}")
    public ResponseEntity<?> getByAge2(@PathVariable("age") Integer age) {
        return ResponseEntity.ok(studentService.getByAge2(age));
    }

    @GetMapping(value = "/gender/{gender}")
    public ResponseEntity<?> getByGender(@PathVariable("gender") Gender gender) {
        return ResponseEntity.ok(studentService.getByGender(gender));
    }
    @GetMapping(value = "/gender2/{gender}")
    public ResponseEntity<?> getByGender2(@PathVariable("gender") Gender gender) {
        return ResponseEntity.ok(studentService.getByGender2(gender));
    }

    @GetMapping(value = "/byGivenDate")
    public ResponseEntity<?> getStudentEntityByGivenDate(@RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(studentService.getStudentEntityByGivenDate(date));
    }
    @GetMapping(value = "/byGivenDate2")
    public ResponseEntity<?> getStudentEntityByGivenDate2(@RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(studentService.getStudentEntityByGivenDate2(date));
    }

    @GetMapping("/byGivenDates")
    public ResponseEntity<?> getStudentEntityByGivenDates(
            @RequestParam("from") LocalDate startDate,
            @RequestParam("to") LocalDate endDate) {
        List<StudentDTO> studentList = studentService.getStudentEntityByGivenDates(startDate, endDate);
        return ResponseEntity.ok(studentList);
    }
    @GetMapping("/byGivenDates2")
    public ResponseEntity<?> getStudentEntityByGivenDates2(
            @RequestParam("from") LocalDate startDate,
            @RequestParam("to") LocalDate endDate) {
        List<StudentDTO> studentList = studentService.getStudentEntityByGivenDates2(startDate, endDate);
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> getByPagination(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
       return ResponseEntity.ok(studentService.studentPagination(page,size));
    }
    @GetMapping("/paginationByLevel")
    public ResponseEntity<?> getByLevelAndPagination(
            @RequestParam("page") int page,
            @RequestParam("size" ) int size,
            @RequestParam("level") Integer level
    ){
        return ResponseEntity.ok(studentService.paginationByLevel(page,size,level));
    }
    @GetMapping("/paginationByGender")
    public ResponseEntity<?> paginationByGender(
            @RequestParam("page") int page,
            @RequestParam("size" ) int size,
            @RequestParam("gender") Gender gender
    ){
        return ResponseEntity.ok(studentService.paginationByGender(page,size,gender));
    }
    @PostMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody StudentFilterDTO filterDTO,
                                    @RequestParam("page") int page,
                                    @RequestParam("size") int size){
        return ResponseEntity.ok(studentService.filter(filterDTO,page,size));
    }



}
