package com.example.firstproject.auth;

public class RegisterAccountDTO {
    public String username;
    public String email;
    public String password;
    public String confirmPassword;
    public Integer studentId;
    public RegisterAccountDTO(String username, String email, String password, String confirmPassword, Integer studentId){
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.studentId = studentId;
    }
}


