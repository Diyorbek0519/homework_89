package com.example.homework_89.controller;

import com.example.homework_89.dto.StudentCourseMarkDTO;
import com.example.homework_89.dto.StudentDTO;
import com.example.homework_89.exception.ItemNotFoundException;
import com.example.homework_89.repository.StudentCourseMarkRepocitory;
import com.example.homework_89.service.StudentCourseMarkService;
import org.apache.coyote.Response;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
  @GetMapping(value ="/get/{id}" )
  public ResponseEntity<?> getById(@PathVariable("id") Integer id){
    return ResponseEntity.ok(studentCourseMarkService.getById(id));
  }
  @GetMapping(value ="/getWithDetail/{id}" )
  public ResponseEntity<?> getByIdWithDetail(@PathVariable("id") Integer id){
    return ResponseEntity.ok(studentCourseMarkService.getByIdWithDetail(id));
  }
  @DeleteMapping(value = "/delete/{id}")
  public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
    return ResponseEntity.ok(studentCourseMarkService.delete(id));
  }
  @GetMapping(value = "/all")
  public ResponseEntity<?> getAll(){
    return ResponseEntity.ok(studentCourseMarkService.getAll());
  }
  @GetMapping(value = "/byIdAndDate")
  public ResponseEntity<?> getByIdAndDate(@RequestParam("id") Integer id,
                                          @RequestParam("from") LocalDate time)
                                          {
    return ResponseEntity.ok(studentCourseMarkService.getMarksByGivenDate(id,time));
  }
  @GetMapping(value = "/byIdAndDates")
  public ResponseEntity<?> getByIdAndDates(@RequestParam("id") Integer id,
                                          @RequestParam("from") LocalDate from,
                                           @RequestParam("to") LocalDate to)
  {
    return ResponseEntity.ok(studentCourseMarkService.getMarksByGivenDates(id,from, to));
  }
  @GetMapping(value = "/getAllSort/{id}")
  public ResponseEntity<?> getByIdAndOrderByCreatedDate(@PathVariable("id") Integer id){
    return ResponseEntity.ok(studentCourseMarkService.getByIdOrderByCreatedDate(id));
  }
  @GetMapping(value = "/getByStudentIdAndCourseId")
  public ResponseEntity<?> getByStudentIdAndCourseId(@RequestParam("id1") Integer id1,
                                                     @RequestParam("id2") Integer id2){
    return ResponseEntity.ok(studentCourseMarkService.getByStudentIdAndCourseId(id1,id2));
  }
  @GetMapping(value = "/getByIdAndCourseName/{id}")
  public ResponseEntity<?> getByStudentIdAndCourseName(@PathVariable("id") Integer id){
    return ResponseEntity.ok(studentCourseMarkService.getByStudentIdAndCourseName(id));
  }
  @GetMapping(value = "/getThreeMarks/{id}")
  public ResponseEntity<?> getThreeMarks(@PathVariable("id") Integer id){
    return ResponseEntity.ok(studentCourseMarkService.threeMarks(id));
  }
  @GetMapping(value = "/getFirstMark/{id}")
  public ResponseEntity<?> getFirstMark(@PathVariable("id") Integer id){
    return ResponseEntity.ok(studentCourseMarkService.getFirstMark(id));
  }
  @GetMapping(value = "/getMark")
  public ResponseEntity<?> getMark(@RequestParam("name") String name,
                                   @RequestParam("id") Integer id){
    return ResponseEntity.ok(studentCourseMarkService.getMark(name,id));
  }
  @GetMapping(value = "/getMarkByCourseName")
  public ResponseEntity<?> getMarkByCourseName(@RequestParam("name") String name,
                                   @RequestParam("id") Integer id){
    return ResponseEntity.ok(studentCourseMarkService.getMarkByCourseName(name,id));
  }
  @GetMapping(value = "/pagination")

  public ResponseEntity<?> pagination(@RequestParam("page") int page,
                                      @RequestParam("size") int size){
    return ResponseEntity.ok(studentCourseMarkService.pagination(page,size));
  }
  @GetMapping(value = "/paginationByStudentId")
  public ResponseEntity<?> paginationByStudentId(@RequestParam("page") int page,
                                      @RequestParam("size") int size,
                                                 @RequestParam("id") Integer id){
    return ResponseEntity.ok(studentCourseMarkService.paginationByStudentId(page,size,id));
  }
  @GetMapping(value = "/paginationByCourseId")
  public ResponseEntity<?> paginationByCourseId(@RequestParam("page") int page,
                                                 @RequestParam("size") int size,
                                                 @RequestParam("id") Integer id){
    return ResponseEntity.ok(studentCourseMarkService.paginationByCourseId(page,size,id));
  }










}
