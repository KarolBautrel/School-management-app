package com.example.firstproject.user;

import com.example.firstproject.student.Student;

public record TokenUserDTO(
        String token,
        String username,
        String email,
        Object role

) {
}
