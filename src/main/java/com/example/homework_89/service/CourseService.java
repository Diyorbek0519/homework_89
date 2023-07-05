package com.example.homework_89.service;

import com.example.homework_89.dto.CourseDTO;
import com.example.homework_89.entity.CourseEntity;
import com.example.homework_89.entity.StudentEntity;
import com.example.homework_89.exception.ItemNotFoundException;
import com.example.homework_89.repository.CourseRepocitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
}
