package com.example.homework_89.controller;

import com.example.homework_89.dto.StudentCourseMarkDTO;
import com.example.homework_89.dto.StudentDTO;
import com.example.homework_89.exception.ItemNotFoundException;
import com.example.homework_89.repository.StudentCourseMarkRepocitory;
import com.example.homework_89.service.StudentCourseMarkService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mark")
public class StudentCourseMarkController {
    @Autowired
    private StudentCourseMarkService studentCourseMarkService;
  @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentCourseMarkDTO studentCourseMarkDTO){
      StudentCourseMarkDTO response=studentCourseMarkService.add(studentCourseMarkDTO);
      return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@RequestBody StudentCourseMarkDTO studentCourseMarkDTO,
                                    @PathVariable("id") Integer id){
      return ResponseEntity.ok(studentCourseMarkService.update(studentCourseMarkDTO,id));
  }

}
