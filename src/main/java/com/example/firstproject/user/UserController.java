package com.example.firstproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/public/auth/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterAccountDTO registerAccountDTO){
        return ResponseEntity.ok(userService.registerUser(registerAccountDTO));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenUserDTO> login(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(userService.loginUser(loginDTO));
    }

    @GetMapping("/")
    public List<UserDTO> listAllUsers(){
       return this.userService.listAllUsers();
    };

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable("userId") Long userId){
        return this.userService.getUserById(userId);
    }
}
