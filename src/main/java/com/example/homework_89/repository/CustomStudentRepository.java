package com.example.homework_89.repository;

import com.example.homework_89.dto.StudentFilterDTO;
import com.example.homework_89.entity.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class CustomStudentRepository {
    @Autowired
    private EntityManager entityManager;

    public List<StudentEntity> filter(StudentFilterDTO filterDTO,int page, int size) {
        StringBuilder stringBuilder = new StringBuilder("select s from StudentEntity as s where s.visible = true");
        Map<String, Object> params = new HashMap<>();
        if (filterDTO.getId() != null) {
            stringBuilder.append(" and s.id=:id");
            params.put("id",filterDTO.getId());
        }
        if(filterDTO.getName()!=null){
            stringBuilder.append(" and s.name=:name");
            params.put("name",filterDTO.getName());
        }
        if(filterDTO.getSurname()!=null){
            stringBuilder.append(" and s.surname=:surname");
            params.put("surname",filterDTO.getSurname());
        }
        if(filterDTO.getLevel()!=null){
            stringBuilder.append(" and s.level=:level");
            params.put("level",filterDTO.getLevel());
        }
        if(filterDTO.getAge()!=null){
            stringBuilder.append(" and s.age=:age");
            params.put("age", filterDTO.getAge());
        }
        if(filterDTO.getGender()!=null){
            stringBuilder.append(" and s.gender=:gender");
            params.put("gender",filterDTO.getGender());
        }
        if(filterDTO.getCreatedDateFrom()!=null){
            stringBuilder.append(" and s.createdDate>=:from");
            params.put("from", LocalDateTime.of(filterDTO.getCreatedDateFrom(),LocalTime.MIN));
        }
        if(filterDTO.getCreatedDateTo()!=null){
            stringBuilder.append(" and s.createdDate<=:to");
            params.put("to", LocalDateTime.of(filterDTO.getCreatedDateTo(),LocalTime.MAX));
        }
        Query query = entityManager.createQuery(stringBuilder.toString());
        query.setFirstResult((page-1)*size);
        query.setMaxResults(size);
        for(Map.Entry<String,Object> param: params.entrySet()){
        query.setParameter(param.getKey(),param.getValue());
        }
        List<StudentEntity> entityList=query.getResultList();
        return entityList;
    }
}
