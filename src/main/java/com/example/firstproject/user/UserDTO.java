package com.example.firstproject.user;

import com.example.firstproject.student.Student;

public record UserDTO(
        String username,
        String email,
        Student student
) {
}
