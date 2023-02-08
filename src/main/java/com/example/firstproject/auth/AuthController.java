package com.example.firstproject.auth;

import com.example.firstproject.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/auth/")
public class AuthController {


    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){

        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterAccountDTO registerAccountDTO){

        return ResponseEntity.ok(authService.registerUser(registerAccountDTO));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenUserDTO> login(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authService.loginUser(authenticationRequest));
    }

    @PutMapping("/change/email")
    public void changeEmail(
            @RequestHeader("Authorization") String token,
            @RequestParam String email){

        authService.changeEmail(token, email);
    }


    @PutMapping("/change/password")
    public void changePassword(
            @RequestHeader("Authorization") String token,
            @RequestBody ChangePasswordDTO changePasswordDTO
    ){
        authService.changePassword(token ,changePasswordDTO);
    }
}
