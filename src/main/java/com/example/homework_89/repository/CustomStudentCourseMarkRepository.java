package com.example.homework_89.repository;

import com.example.homework_89.dto.StudentCourseMarkFilterDTO;
import com.example.homework_89.entity.StudentCourseMarkEntity;
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
public class CustomStudentCourseMarkRepository {
    @Autowired
    private EntityManager entityManager;
    public List<StudentCourseMarkEntity> filter(StudentCourseMarkFilterDTO filterDTO, int page, int size){
        StringBuilder stringBuilder = new StringBuilder("select s from StudentCourseMarkEntity as s where s.visible=true");
        Map<String,Object> params=new HashMap<>();
        if(filterDTO.getStudentId()!=null){
            stringBuilder.append(" and s.studentId=:studentId");
            params.put("studentId",filterDTO.getStudentId());
        }
        if(filterDTO.getCourseId()!=null){
            stringBuilder.append(" and s.courseId=:courseId");
            params.put("courseId",filterDTO.getCourseId());
        }
        if(filterDTO.getMarkFrom()!=null){
            stringBuilder.append(" and s.marl>=markFrom");
            params.put("markFrom",filterDTO.getMarkFrom());
        }
        if(filterDTO.getMarkTo()!=null){
            stringBuilder.append(" and s.mark<=:markTo");
            params.put("markTo",filterDTO.getMarkTo());
        }
        if(filterDTO.getCreatedDateFrom()!=null){
            stringBuilder.append(" and s.createdDate>=:from");
            params.put("from", LocalDateTime.of(filterDTO.getCreatedDateFrom(), LocalTime.MIN));
        }
        if(filterDTO.getCreatedDateTo()!=null){
            stringBuilder.append(" and s.createdDate<=to");
            params.put("to",LocalDateTime.of(filterDTO.getCreatedDateTo(),LocalTime.MAX));
        }
        Query query= entityManager.createQuery(stringBuilder.toString());
        query.setFirstResult((page-1)*size);
        query.setMaxResults(size);
        for (Map.Entry<String,Object> param: params.entrySet()){
            query.setParameter(param.getKey(),param.getValue());
        }
        List<StudentCourseMarkEntity> entityList =query.getResultList();
        return entityList;
    }
}
