package com.example.homework_89.controller;

import com.example.homework_89.dto.CourseDTO;
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

    @GetMapping(value = "/all")
    public List<CourseDTO> all() {
        return courseService.getAll();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody CourseDTO courseDTO,
                                 @PathVariable("id") Integer id) {
       courseService.update(id, courseDTO);
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
    @GetMapping(value = "/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(courseService.getByName(name));
    }
    @GetMapping(value = "/price/{price}")
    public ResponseEntity<?> getByPrice(@PathVariable("price") Double price) {
        return ResponseEntity.ok(courseService.getByPrice(price));
    }
    @GetMapping(value = "/duration/{duration}")
    public ResponseEntity<?> getByDuration(@PathVariable("duration") Integer duration) {
        return ResponseEntity.ok(courseService.getByDuration(duration));
    }
    @GetMapping(value = "/prices")
    public ResponseEntity<?> getByprices(@RequestParam("param1") Double pr1,
                                                @RequestParam("param2") Double pr2){
        List<CourseDTO> courseDTOList =courseService.getByPrices(pr1, pr2);
        return ResponseEntity.ok(courseDTOList);
    }
    @GetMapping(value = "/getByDate")
    public ResponseEntity<?> getByDate(@RequestParam("time1") LocalDate time1,
                                       @RequestParam("time2") LocalDate time2){
        return ResponseEntity.ok(courseService.getByDate(time1, time2));
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
    @GetMapping(value = "/findByPriceBerweenAndPagination")
    public ResponseEntity<?> findByPriceBetweenAndPagination(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("price1") Double price1,
            @RequestParam("price2") Double price2
    ){
        return ResponseEntity.ok(courseService.findByPriceBetweenAndPagination(page,size,price1,price2));
    }





}
