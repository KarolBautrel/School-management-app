package com.example.firstproject.auth;

import com.example.firstproject.config.JwtService;
import com.example.firstproject.exceptions.UserServiceException;
import com.example.firstproject.student.Student;
import com.example.firstproject.student.StudentRepository;
import com.example.firstproject.user.*;
import jakarta.transaction.Transactional;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@Service
public class AuthService {

    private final UserRepository userRepository;

    private final StudentRepository studentRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       StudentRepository studentRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse registerUser(RegisterAccountDTO registerAccountDTO) throws ResponseStatusException {

        Optional<User> userOptional = userRepository.findUserByEmailQuery(registerAccountDTO.email);
        Optional<User> registeredStudent = userRepository.
                checkIfStudentRegistered(Long.valueOf(registerAccountDTO.studentId));
        if (userOptional.isPresent()) {
            System.out.println("---------------------PRESENT----------------");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Email exists");
        }
        if (registeredStudent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Student already singed in to account");
        }


        if (!registerAccountDTO.password.equals(registerAccountDTO.confirmPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords incorrect ");
        }
        Student student = this.studentRepository.findById(Long.valueOf(registerAccountDTO.studentId)).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Student not found"));
        String encodedPassword = passwordEncoder.encode(registerAccountDTO.password);
        User user = new User(registerAccountDTO.username,
                registerAccountDTO.email,
                encodedPassword,
                student);

        this.userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    public TokenUserDTO loginUser(AuthenticationRequest authenticationRequest){


        User optionalUser = this.userRepository.findByEmail(authenticationRequest.email).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"no user"));
        if(!passwordEncoder.matches(authenticationRequest.password, optionalUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Wrong password");
        }
        var jwtToken = jwtService.generateToken(optionalUser);

        return new TokenUserDTO(jwtToken, optionalUser.getUsername(), optionalUser.getEmail(), optionalUser.getRole());
    }
    @Transactional
    public void changeEmail(String token, String email){
        String username = jwtService.extractUsername(token.substring(7));//
        User user = this.userRepository
                .findUserByUsernameQuery(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (this.userRepository.findUserByEmailQuery(email).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is taken by another user");
        }
        user.setEmail(email);



    }
    @Transactional
    public void changePassword(String token, ChangePasswordDTO changePasswordDTO){
        String username = jwtService.extractUsername(token.substring(7));//
        User user = this.userRepository
                .findUserByUsernameQuery(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        System.out.println(passwordEncoder.matches(changePasswordDTO.oldPassword, user.getPassword()));
        System.out.println(changePasswordDTO.newPassword);
        System.out.println(changePasswordDTO.reNewPassword);

        if(!passwordEncoder.matches(changePasswordDTO.oldPassword, user.getPassword())){
            throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong password");
        }
        if (!changePasswordDTO.newPassword.equals(changePasswordDTO.reNewPassword)){
            throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords doesnt match");
        }
        String encodedNewPassword = passwordEncoder.encode(changePasswordDTO.newPassword);
        user.setPassword(encodedNewPassword);
    }
}
