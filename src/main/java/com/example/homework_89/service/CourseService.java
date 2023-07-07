package com.example.homework_89.service;

import com.example.homework_89.dto.CourseDTO;
import com.example.homework_89.dto.StudentDTO;
import com.example.homework_89.entity.CourseEntity;
import com.example.homework_89.entity.StudentEntity;
import com.example.homework_89.exception.ItemNotFoundException;
import com.example.homework_89.repository.CourseRepocitory;
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
public class CourseService {

    @Autowired
    private CourseRepocitory courseRepocitory;

    public CourseDTO add(CourseDTO courseDTO) {
        CourseEntity entity =new CourseEntity();
        entity.setName(courseDTO.getName());
        entity.setDuration(courseDTO.getDuration());
        entity.setPrice(courseDTO.getPrice());
        entity.setCreatedDate(LocalDateTime.now());
        courseRepocitory.save(entity);
        courseDTO.setId(entity.getId());
        courseDTO.setCreatedDate(entity.getCreatedDate());
        return courseDTO;
    }

    public CourseDTO getById(Integer id) {
        Optional<CourseEntity> optional =courseRepocitory.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Not found");
        }
        return toDto(optional.get());
    }
    public List<CourseDTO> getAll(){
        Iterable<CourseEntity> iterable= courseRepocitory.findAll();
        List<CourseDTO> list = new LinkedList<>();
        iterable.forEach(st->{
            list.add(toDto(st));
        });
        return list;

    }


    public Boolean update(Integer id, CourseDTO courseDTO) {
        if(courseRepocitory.existsById(id)){
            Optional<CourseEntity> optional =courseRepocitory.findById(id);
            if(optional.isEmpty()){
                throw new ItemNotFoundException("Not found");
            }
            CourseEntity courseEntity =optional.get();
            courseEntity.setPrice(courseDTO.getPrice());
            courseEntity.setDuration(courseDTO.getDuration());
            courseRepocitory.save(courseEntity);
            return true;
        }
        return false;
    }

    public Boolean delete(Integer id) {
        Optional<CourseEntity> optional = courseRepocitory.findById(id);
        if (optional.isEmpty()) {
            throw  new ItemNotFoundException("Course not found");
        }
        courseRepocitory.deleteById(id);
        return true;
    }

    public List<CourseDTO> getByName(String name) {
        List<CourseEntity> entities=courseRepocitory.getByName(name);
        if(entities.isEmpty()){
            throw  new ItemNotFoundException("Not found");
        }
        List<CourseDTO> courseDTOList = new LinkedList<>();
        entities.forEach(temp->{
            courseDTOList.add(toDto(temp));
        });
        return courseDTOList;
    }
    public CourseDTO toDto(CourseEntity courseEntity){
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseEntity.getId());
            courseDTO.setName(courseEntity.getName());
            courseDTO.setDuration(courseEntity.getDuration());
            courseDTO.setPrice(courseEntity.getPrice());
            courseDTO.setCreatedDate(courseEntity.getCreatedDate());
            return courseDTO;
    }

    public List<CourseDTO> getByPrice(Double price) {
        List<CourseEntity> entities =courseRepocitory.getByPrice(price);
        if(entities.isEmpty()){
            throw  new ItemNotFoundException("Not found");
        }
        List<CourseDTO> courseDTOList = new LinkedList<>();
        entities.forEach(temp->{
            courseDTOList.add(toDto(temp));
        });
        return courseDTOList;
    }

    public List<CourseDTO> getByDuration(Integer duration) {
        List<CourseEntity> entities = courseRepocitory.getByDuration(duration);
        if(entities.isEmpty()){
            throw  new ItemNotFoundException("Not found");
        }
        List<CourseDTO> courseDTOList = new LinkedList<>();
        entities.forEach(temp->{
            courseDTOList.add(toDto(temp));
        });
        return courseDTOList;
    }

    public List<CourseDTO> getByPrices(Double pr1, Double pr2) {
        List<CourseEntity> entities =courseRepocitory.getByPriceBetween(pr1, pr2);
        if(entities.isEmpty()){
            throw new ItemNotFoundException("Not found");
        }
        List<CourseDTO> courseDTOList = new LinkedList<>();
        entities.forEach(temp->{
            courseDTOList.add(toDto(temp));
        });
        return courseDTOList;
    }

    public List<CourseDTO> getByDate(LocalDate time1, LocalDate time2) {
        LocalDateTime t1 =LocalDateTime.of(time1, LocalTime.MIN);
        LocalDateTime t2 =LocalDateTime.of(time2, LocalTime.MAX);
        List<CourseEntity> entityList =courseRepocitory.getByCreatedDateBetween(t1,t2);
        if(entityList.isEmpty()){
            throw new ItemNotFoundException("Not found");
        }
        List<CourseDTO> courseDTOList = new LinkedList<>();
        entityList.forEach(temp->{
            courseDTOList.add(toDto(temp));
        });
        return courseDTOList;
    }
    public List<CourseDTO> pagination(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<CourseEntity> entityList=courseRepocitory.findAll(pageable);
        if(entityList.isEmpty()){
            throw new ItemNotFoundException("Not found");
        }
        List<CourseDTO> courseDTOList = new LinkedList<>();
        entityList.forEach(temp->{
            courseDTOList.add(toDto(temp));
        });
        return courseDTOList;
    }
    public List<CourseDTO> paginationAndSortingByCreatedDate(int page, int size){
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdDate").descending());
        Page<CourseEntity> entityList=courseRepocitory.findAll(pageable);
        if(entityList.isEmpty()){
            throw new ItemNotFoundException("Not found");
        }
        List<CourseDTO> courseDTOList = new LinkedList<>();
        entityList.forEach(temp->{
            courseDTOList.add(toDto(temp));
        });
        return courseDTOList;
    }
    public List<CourseDTO> findByPriceAndPagination(int page, int size, Double price){
        Pageable pageable =PageRequest.of(page, size, Sort.by("createdDate").descending());
        List<CourseEntity> entityList=courseRepocitory.findByPrice(pageable,price);
        if(entityList.isEmpty()){
            throw new ItemNotFoundException("Not found");
        }
        List<CourseDTO> courseDTOList = new LinkedList<>();
        entityList.forEach(temp->{
            courseDTOList.add(toDto(temp));
        });
        return courseDTOList;
    }
    public List<CourseDTO> findByPriceBetweenAndPagination(int page, int size, Double price1, Double price2){
        Pageable pageable =PageRequest.of(page, size, Sort.by("createdDate").descending());
        List<CourseEntity> entityList=courseRepocitory.findByPriceBetween(pageable,price1,price2);
        if(entityList.isEmpty()){
            throw new ItemNotFoundException("Not found");
        }
        List<CourseDTO> courseDTOList = new LinkedList<>();
        entityList.forEach(temp->{
            courseDTOList.add(toDto(temp));
        });
        return courseDTOList;
    }
}
