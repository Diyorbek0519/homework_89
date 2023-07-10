package com.example.homework_89.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarkMapper {
    private Double mark;
    private String courseName;

    public MarkMapper() {
    }

    public MarkMapper(Double mark, String courseName) {
        this.mark = mark;
        this.courseName = courseName;
    }
}
