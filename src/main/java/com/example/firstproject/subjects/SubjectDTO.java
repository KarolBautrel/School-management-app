package com.example.firstproject.subjects;

import com.example.firstproject.teacher.TeacherDTO;

import java.util.List;

public record SubjectDTO(
        String name,
        List<TeacherDTO> teachers) {

}
