package com.example.homework_89.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "mark")
public class StudentCourseMarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity studentId;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity courseId;
    @Column(name = "mark")
    private Double mark;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
