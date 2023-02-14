package com.example.firstproject.auth;

public class RegisterAccountDTO {
    public String username;

    public String email;
    public String password;
    public String confirmPassword;
    public String role;
    public Integer roleId;
    public RegisterAccountDTO(String username, String email, String password,String role, String confirmPassword, Integer roleId){
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
        this.roleId = roleId;
    }
}


