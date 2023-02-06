package com.example.firstproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/")
    public void registerUser(@RequestBody UserDTO userDTO){
        this.userService.registerUser(userDTO);
    }

    @GetMapping("/")
    public List<User> listAllUsers(){
       return this.userService.listAllUsers();
    };

    @GetMapping("/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Long userId){
        return this.userService.getUserById(userId);
    }
}
