package com.example.firstproject.user;

public class UserDTO {
    String username;
    String email;
    String password;
    String confirmPassword;
    Integer studentId;
    UserDTO(String username, String email, String password, String confirmPassword, Integer studentId){
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.studentId = studentId;
    }
}
