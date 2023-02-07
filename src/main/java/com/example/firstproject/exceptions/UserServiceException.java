package com.example.firstproject.exceptions;

import com.example.firstproject.user.UserService;

public class UserServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;
    public UserServiceException(String message){
        super(message);
    }
}

