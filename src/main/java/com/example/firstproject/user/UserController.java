package com.example.firstproject.user;

import com.example.firstproject.auth.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/protected/user/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserDTO> listAllUsers(){
       return this.userService.listAllUsers();
    };

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable("userId") Long userId){
        return this.userService.getUserById(userId);
    }

    @GetMapping("/loggedUser")
    public ResponseEntity<UserDTO> getLoggedUser(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.getLoggedUser(token));
    }


}
