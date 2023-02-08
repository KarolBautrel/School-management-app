package com.example.firstproject.auth;

public class ChangePasswordDTO {
    String oldPassword;
    String newPassword;
    String reNewPassword;

public ChangePasswordDTO(String newPassword, String reNewPassword, String oldPassword){
    this.newPassword = newPassword;
    this.reNewPassword = reNewPassword;
    this.oldPassword = oldPassword;
}
}


