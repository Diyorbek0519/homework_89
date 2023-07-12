package com.example.homework_89.controller;

import com.example.homework_89.dto.CourseDTO;
import com.example.homework_89.dto.CourseFilterDTO;
import com.example.homework_89.dto.StudentDTO;
import com.example.homework_89.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CourseDTO courseDTO) {
        CourseDTO response = courseService.add(courseDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseService.getById(id));
    }
    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<?> getById2(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseService.getById2(id));
    }

    @GetMapping(value = "/all")
    public List<CourseDTO> all() {
        return courseService.getAll();
    }
    @GetMapping(value = "/all2")
    public List<CourseDTO> all2() {
        return courseService.getAll2();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody CourseDTO courseDTO,
                                 @PathVariable("id") Integer id) {
       courseService.update(id, courseDTO);
        return ResponseEntity.ok(true);
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> put2(@RequestBody CourseDTO courseDTO,
                                 @PathVariable("id") Integer id) {
        courseService.update2(id, courseDTO);
        return ResponseEntity.ok(true);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        Boolean response=courseService.delete(id);
        if (response) {
            return ResponseEntity.ok("Course deleted");
        }
        return ResponseEntity.badRequest().body("Course Not Found");
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete2(@PathVariable("id") Integer id){
        Boolean response=courseService.delete(id);
        if (response) {
            return ResponseEntity.ok("Course deleted");
        }
        return ResponseEntity.badRequest().body("Course Not Found");
    }
    @GetMapping(value = "/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(courseService.getByName(name));
    }
    @GetMapping(value = "/name2/{name}")
    public ResponseEntity<?> getByName2(@PathVariable("name") String name) {
        return ResponseEntity.ok(courseService.getByName2(name));
    }
    @GetMapping(value = "/price/{price}")
    public ResponseEntity<?> getByPrice(@PathVariable("price") Double price) {
        return ResponseEntity.ok(courseService.getByPrice(price));
    }
    @GetMapping(value = "/price2/{price}")
    public ResponseEntity<?> getByPrice2(@PathVariable("price") Double price) {
        return ResponseEntity.ok(courseService.getByPrice2(price));
    }
    @GetMapping(value = "/duration/{duration}")
    public ResponseEntity<?> getByDuration(@PathVariable("duration") Integer duration) {
        return ResponseEntity.ok(courseService.getByDuration(duration));
    }
    @GetMapping(value = "/duration2/{duration}")
    public ResponseEntity<?> getByDuration2(@PathVariable("duration") Integer duration) {
        return ResponseEntity.ok(courseService.getByDuration(duration));
    }
    @GetMapping(value = "/prices")
    public ResponseEntity<?> getByprices(@RequestParam("param1") Double pr1,
                                                @RequestParam("param2") Double pr2){
        List<CourseDTO> courseDTOList =courseService.getByPrices(pr1, pr2);
        return ResponseEntity.ok(courseDTOList);
    }
    @GetMapping(value = "/prices2")
    public ResponseEntity<?> getByprices2(@RequestParam("param1") Double pr1,
                                         @RequestParam("param2") Double pr2){
        List<CourseDTO> courseDTOList =courseService.getByPrices2(pr1, pr2);
        return ResponseEntity.ok(courseDTOList);
    }
    @GetMapping(value = "/getByDate")
    public ResponseEntity<?> getByDate(@RequestParam("time1") LocalDate time1,
                                       @RequestParam("time2") LocalDate time2){
        return ResponseEntity.ok(courseService.getByDate(time1, time2));
    }
    @GetMapping(value = "/getByDate2")
    public ResponseEntity<?> getByDate2(@RequestParam("time1") LocalDate time1,
                                       @RequestParam("time2") LocalDate time2){
        return ResponseEntity.ok(courseService.getByDate2(time1, time2));
    }
    @GetMapping(value = "/pagination")
    public ResponseEntity<?> pagination(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        return ResponseEntity.ok(courseService.pagination(page,size));
    }
    @GetMapping(value = "/paginationAndSorting")
    public ResponseEntity<?> paginationAndSortingByCreatedDate(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        return ResponseEntity.ok(courseService.paginationAndSortingByCreatedDate(page,size));
    }
    @GetMapping(value = "/findByPriceAndPagination")
    public ResponseEntity<?> findByPriceAndPagination(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("price") Double price
    ){
        return ResponseEntity.ok(courseService.findByPriceAndPagination(page,size,price));
    }
    @GetMapping(value = "/findByPriceBetweenAndPagination")
    public ResponseEntity<?> findByPriceBetweenAndPagination(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("price1") Double price1,
            @RequestParam("price2") Double price2
    ){
        return ResponseEntity.ok(courseService.findByPriceBetweenAndPagination(page,size,price1,price2));
    }
    @PostMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody CourseFilterDTO filterDTO,
                                    @RequestParam("page") int page,
                                    @RequestParam("size") int size){
        return ResponseEntity.ok(courseService.filter(filterDTO,page,size));
    }






}
