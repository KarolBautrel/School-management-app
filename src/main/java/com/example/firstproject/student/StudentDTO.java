package com.example.firstproject.student;

import com.example.firstproject.grade.Grade;
import com.example.firstproject.studentgroup.StudentGroup;

import java.util.List;

public record StudentDTO(
        String name,
        int age,
        String email

) {
}
