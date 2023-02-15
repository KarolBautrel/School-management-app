package com.example.firstproject.auth;

import com.example.firstproject.user.User;
import com.example.firstproject.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthUtils {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    AuthUtils(UserRepository userRepository,
              PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void checkRegisterCredentials(RegisterAccountDTO registerAccountDTO) {
        Optional<User> userOptional = userRepository.findUserByEmailQuery(registerAccountDTO.email);
        if (userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Email exists");
        }
        Optional<User> optionalUserStudent = userRepository
                .checkIfStudentRegistered((long) registerAccountDTO.roleId);
        Optional<User> optionalUserTeacher = userRepository
                .checkIfTeacherRegistered((long) registerAccountDTO.roleId);

        if (optionalUserStudent.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "There is already user with this student");
        }

        if (optionalUserTeacher.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "There is already teacher with this student");
        }
        if (!registerAccountDTO.password.equals(registerAccountDTO.confirmPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords incorrect ");
        }

    }

    public User checkLoginCredentials(AuthenticationRequest authenticationRequest) {
        User user = this.userRepository.findByEmail(authenticationRequest.email).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "no user"));
        if (!passwordEncoder.matches(authenticationRequest.password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong password");
        }
        return user;
    }
}
