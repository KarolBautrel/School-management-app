package com.example.firstproject.studentgroup;

import com.example.firstproject.student.StudentDTO;

import java.util.List;

public record StudentGroupDTO(
        String name,
        int size,
        List<StudentDTO>  studentList
) {
}
