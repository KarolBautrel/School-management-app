package com.example.firstproject.auth;

import com.example.firstproject.config.JwtService;
import com.example.firstproject.exceptions.UserServiceException;
import com.example.firstproject.roles.SchoolRoles;
import com.example.firstproject.student.Student;
import com.example.firstproject.student.StudentRepository;
import com.example.firstproject.teacher.Teacher;
import com.example.firstproject.teacher.TeacherRepository;
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
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthUtils authUtils;

    public AuthService(UserRepository userRepository,
                       StudentRepository studentRepository,
                       PasswordEncoder passwordEncoder,
                       TeacherRepository teacherRepository,
                       AuthUtils authUtils,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.teacherRepository = teacherRepository;
        this.authUtils = authUtils;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse registerUser(RegisterAccountDTO registerAccountDTO) throws ResponseStatusException {

        Student studentRole;
        Teacher teacherRole;
        User user;

        authUtils.checkRegisterCredentials(registerAccountDTO);
        String encodedPassword = passwordEncoder.encode(registerAccountDTO.password);

        if (registerAccountDTO.role.equals("STUDENT")) {
            studentRole = this.studentRepository.findById(Long.valueOf(registerAccountDTO.roleId)).
                    orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Student not found"));

            user = new User(registerAccountDTO.username,
                    registerAccountDTO.email,
                    encodedPassword,
                    studentRole);
        } else {
            teacherRole = this.teacherRepository.findById(Long.valueOf(registerAccountDTO.roleId)).
                    orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Student not found"));

            user = new User(registerAccountDTO.username,
                    registerAccountDTO.email,
                    encodedPassword,
                    teacherRole);


        }
        this.userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    public TokenUserDTO loginUser(AuthenticationRequest authenticationRequest) {

        User user = authUtils.checkLoginCredentials(authenticationRequest);

        var jwtToken = jwtService.generateToken(user);

        return new TokenUserDTO(jwtToken,
                user.getUsername(),
                user.getEmail(),
                user.getRole());
    }

    @Transactional
    public void changeEmail(String token, String email) {
        String username = jwtService.extractUsername(token.substring(7));//
        User user = this.userRepository
                .findUserByUsernameQuery(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));

        if (this.userRepository.findUserByEmailQuery(email).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Email is taken by another user");
        }
        user.setEmail(email);


    }

    @Transactional
    public void changePassword(String token, ChangePasswordDTO changePasswordDTO) {
        String username = jwtService.extractUsername(token.substring(7));//
        User user = this.userRepository
                .findUserByUsernameQuery(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        System.out.println(passwordEncoder.matches(changePasswordDTO.oldPassword, user.getPassword()));
        System.out.println(changePasswordDTO.newPassword);
        System.out.println(changePasswordDTO.reNewPassword);

        if (!passwordEncoder.matches(changePasswordDTO.oldPassword, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong password");
        }
        if (!changePasswordDTO.newPassword.equals(changePasswordDTO.reNewPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords doesnt match");
        }
        String encodedNewPassword = passwordEncoder.encode(changePasswordDTO.newPassword);
        user.setPassword(encodedNewPassword);
    }
}
