package com.example.homework_89.service;

import com.example.homework_89.dto.StudentCourseMarkDTO;
import com.example.homework_89.dto.StudentDTO;
import com.example.homework_89.entity.CourseEntity;
import com.example.homework_89.entity.StudentCourseMarkEntity;
import com.example.homework_89.entity.StudentEntity;
import com.example.homework_89.exception.ItemNotFoundException;
import com.example.homework_89.mapper.MarkMapper;
import com.example.homework_89.mapper.StudentMarkCourseMapper;
import com.example.homework_89.repository.StudentCourseMarkRepocitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepocitory studentCourseMarkRepocitory;

    public StudentCourseMarkDTO add(StudentCourseMarkDTO dto) {
        check(dto);
        StudentEntity student = new StudentEntity();
        student.setId(dto.getStudentId());
        CourseEntity course = new CourseEntity();
        course.setId(dto.getCourseId());

        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        entity.setStudent(student);
        entity.setCourse(course);
        entity.setMark(dto.getMark());
        entity.setCreatedDate(LocalDateTime.now());
        studentCourseMarkRepocitory.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public Boolean update(StudentCourseMarkDTO dto, Integer id) {
        check(dto);
        Optional<StudentCourseMarkEntity> optional = studentCourseMarkRepocitory.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        StudentCourseMarkEntity entity = optional.get();
        entity.setMark(dto.getMark());
        studentCourseMarkRepocitory.save(entity);
        return true;
    }


    public boolean check(StudentCourseMarkDTO studentCourseMarkDTO) {
        if (studentCourseMarkDTO.getMark() == null) {
            throw new ItemNotFoundException("Mark is null");
        }
        return true;
    }

    public StudentCourseMarkDTO getById(Integer id) {
        Optional<StudentCourseMarkEntity> optional = studentCourseMarkRepocitory.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        StudentCourseMarkEntity entity = optional.get();
        return toDTO(entity);
    }

    public StudentCourseMarkDTO toDTO(StudentCourseMarkEntity entity) {
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudent().getId());
        dto.setCourseId(entity.getCourse().getId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public StudentMarkCourseMapper getByIdWithDetail(Integer id) {
        Optional<StudentCourseMarkEntity> optional = studentCourseMarkRepocitory.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        StudentCourseMarkEntity entity = optional.get();
        StudentMarkCourseMapper mapper = new StudentMarkCourseMapper();
        mapper.setId(entity.getId());
        mapper.setStudentId(entity.getStudent().getId());
        mapper.setStudentName(entity.getStudent().getName());
        mapper.setStudentSurname(entity.getStudent().getSurname());
        mapper.setCourseId(entity.getCourse().getId());
        mapper.setCourseName(entity.getCourse().getName());
        mapper.setMark(entity.getMark());
        mapper.setCreatedDate(entity.getCreatedDate());
        return mapper;
    }

    public Boolean delete(Integer id) {
        Optional<StudentCourseMarkEntity> optional = studentCourseMarkRepocitory.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        studentCourseMarkRepocitory.deleteById(id);
        return true;
    }

    public List<StudentCourseMarkDTO> getAll() {
        Iterable<StudentCourseMarkEntity> iterable = studentCourseMarkRepocitory.findAll();
        if (!iterable.iterator().hasNext()) {
            throw new ItemNotFoundException("Item not found");
        }
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        iterable.forEach(temp -> {
            dtoList.add(toDTO(temp));
        });
        return dtoList;
    }

    public List<Double> getMarksByGivenDate(Integer id, LocalDate time) {
        LocalDateTime t1 = LocalDateTime.of(time, LocalTime.MIN);
        LocalDateTime t2 = LocalDateTime.of(time, LocalTime.MAX);
        List<StudentCourseMarkEntity> list = studentCourseMarkRepocitory.getByStudentIdAndCreatedDateBetween(id, t1, t2);
        if (list.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<Double> markList = new LinkedList<>();
        list.forEach(t -> {
            markList.add(t.getMark());
        });
        return markList;
    }

    public List<Double> getMarksByGivenDates(Integer id, LocalDate from, LocalDate to) {
        LocalDateTime t1 = LocalDateTime.of(from, LocalTime.MIN);
        LocalDateTime t2 = LocalDateTime.of(to, LocalTime.MAX);
        List<StudentCourseMarkEntity> list = studentCourseMarkRepocitory.getByStudentIdAndCreatedDateBetween(id, t1, t2);
        if (list.isEmpty()) {
            throw new ItemNotFoundException("Not found");
        }
        List<Double> markList = new LinkedList<>();
        list.forEach(t -> {
            markList.add(t.getMark());
        });
        return markList;
    }

    public List<StudentCourseMarkDTO> getByIdOrderByCreatedDate(Integer id) {
        List<StudentCourseMarkEntity> entities = studentCourseMarkRepocitory.getByStudentIdOrderByCreatedDateDesc(id);
        if (entities.isEmpty()) {
            throw new ItemNotFoundException("Item Not found");
        }
        List<StudentCourseMarkDTO> dto = new LinkedList<>();
        entities.forEach(t -> {
            dto.add(toDTO(t));
        });
        return dto;
    }

    public List<StudentCourseMarkDTO> getByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        List<StudentCourseMarkEntity> entities = studentCourseMarkRepocitory.getByStudentIdAndCourseIdOrderByCreatedDateDesc(studentId, courseId);
        if (entities.isEmpty()) {
            throw new ItemNotFoundException("Item Not found");
        }
        List<StudentCourseMarkDTO> dto = new LinkedList<>();
        entities.forEach(t -> {
            dto.add(toDTO(t));
        });
        return dto;
    }

    public MarkMapper getByStudentIdAndCourseName(Integer id) {
        List<StudentCourseMarkEntity> entities = studentCourseMarkRepocitory.getByStudentIdOrderByCreatedDateDesc(id);
        if (entities.isEmpty()) {
            throw new ItemNotFoundException("Item Not found");
        }
        StudentCourseMarkEntity entity = entities.get(0);
        return new MarkMapper(entity.getMark(), entity.getCourse().getName());
    }

    public List<StudentCourseMarkDTO> threeMarks(Integer id) {
        List<StudentCourseMarkEntity> entities = studentCourseMarkRepocitory.findTop3ByStudentIdOrderByMarkDesc(id);
        if (entities.isEmpty()) {
            throw new ItemNotFoundException("Item Not found");
        }
        List<StudentCourseMarkDTO> dto = new LinkedList<>();
        entities.forEach(t -> {
            dto.add(toDTO(t));
        });
        return dto;
    }

    public StudentCourseMarkDTO getFirstMark(Integer id) {
        StudentCourseMarkEntity entity = studentCourseMarkRepocitory.findFirstByStudentIdOrderByCreatedDateAsc(id);
        if (entity == null) {
            throw new ItemNotFoundException("Not found");
        }
        return toDTO(entity);
    }

    public StudentCourseMarkDTO getMark(String courseName, Integer studentId) {
        StudentCourseMarkEntity entity = studentCourseMarkRepocitory.findFirstByCourseNameAndStudentIdOrderByCreatedDateAsc(courseName, studentId);
        if (entity == null) {
            throw new ItemNotFoundException("Not found");
        }
        return toDTO(entity);
    }

    public StudentCourseMarkDTO getMarkByCourseName(String courseName, Integer studentId) {
        StudentCourseMarkEntity entity = studentCourseMarkRepocitory.findFirstByCourseNameAndStudentIdOrderByMarkDesc(courseName, studentId);
        if (entity == null) {
            throw new ItemNotFoundException("Not found");
        }
        return toDTO(entity);
    }

    public PageImpl<StudentCourseMarkDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepocitory.findAll(pageable);
        List<StudentCourseMarkEntity> entityList = pageObj.getContent();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        entityList.forEach(t -> {
            dtoList.add(toDTO(t));
        });
        Long totalElements = pageObj.getTotalElements();
        PageImpl<StudentCourseMarkDTO> dtoPage = new PageImpl<StudentCourseMarkDTO>(dtoList, pageable, totalElements);
        return dtoPage;

    }
    public PageImpl<StudentCourseMarkDTO> paginationByStudentId(int page, int size, Integer id){
        Pageable pageable =PageRequest.of(page,size,Sort.by("createdDate").descending());
        Page<StudentCourseMarkEntity> pageObj=studentCourseMarkRepocitory.findByStudentId(id,pageable);
        List<StudentCourseMarkEntity> entityList=pageObj.getContent();
        Long totalCount=pageObj.getTotalElements();
        List<StudentCourseMarkDTO> dtoList=new LinkedList<>();
        entityList.forEach(t->{
            dtoList.add(toDTO(t));
        });
        PageImpl<StudentCourseMarkDTO> dtoPage=new PageImpl<>(dtoList,pageable,totalCount);
        return dtoPage;
    }
    public PageImpl<StudentCourseMarkDTO> paginationByCourseId(int page, int size, Integer id){
        Pageable pageable =PageRequest.of(page,size,Sort.by("createdDate").descending());
        Page<StudentCourseMarkEntity> pageObj=studentCourseMarkRepocitory.findByCourseId(id,pageable);
        List<StudentCourseMarkEntity> entityList=pageObj.getContent();
        Long totalCount=pageObj.getTotalElements();
        List<StudentCourseMarkDTO> dtoList=new LinkedList<>();
        entityList.forEach(t->{
            dtoList.add(toDTO(t));
        });
        PageImpl<StudentCourseMarkDTO> dtoPage=new PageImpl<>(dtoList,pageable,totalCount);
        return dtoPage;
    }


}
