package com.example.firstproject.teacher;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TeacherDTOMapper implements Function<Teacher, TeacherDTO> {

@Override
public TeacherDTO apply(Teacher teacher){
    return new TeacherDTO(teacher.name);
}
}
