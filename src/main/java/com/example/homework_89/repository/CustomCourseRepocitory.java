package com.example.homework_89.repository;

import com.example.homework_89.dto.CourseFilterDTO;
import com.example.homework_89.entity.CourseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class CustomCourseRepocitory {
    @Autowired
    private EntityManager entityManager;
    public List<CourseEntity> filter(CourseFilterDTO filterDTO, int page, int size){
        StringBuilder stringBuilder=new StringBuilder("select s from CourseEntity as s where visible = true");
        Map<String,Object> params=new HashMap<>();
        if(filterDTO.getId()!=null){
            stringBuilder.append(" and s.id=:id");
            params.put("id",filterDTO.getId());
        }
        if(filterDTO.getName()!=null){
            stringBuilder.append(" and s.name=:name");
            params.put("name", filterDTO.getName());
        }
        if(filterDTO.getPrice()!=null){
            stringBuilder.append(" and s.price=:price");
            params.put("price",filterDTO.getPrice());
        }
        if(filterDTO.getDuration()!=null){
            stringBuilder.append(" and s.duration=:duration");
            params.put("duration",filterDTO.getDuration());
        }
        if(filterDTO.getCreatedDateFrom()!=null){
            stringBuilder.append(" and s.createdDate>=:from");
            params.put("from", LocalDateTime.of(filterDTO.getCreatedDateFrom(), LocalTime.MIN));
        }
        if(filterDTO.getCreatedDateTo()!=null){
            stringBuilder.append(" and s.createdDate<=:to");
            params.put("from",LocalDateTime.of(filterDTO.getCreatedDateTo(),LocalTime.MAX));
        }
        Query query = entityManager.createQuery(stringBuilder.toString());
        query.setFirstResult((page-1)*size);
        query.setMaxResults(size);
        for(Map.Entry<String,Object> param: params.entrySet()){
            query.setParameter(param.getKey(),param.getValue());
        }
        List<CourseEntity> entityList=query.getResultList();
        return entityList;
    }
}
