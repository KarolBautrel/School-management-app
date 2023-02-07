package com.example.firstproject.user;

import com.example.firstproject.auth.AuthenticationResponse;
import com.example.firstproject.config.JwtService;
import com.example.firstproject.student.Student;
import com.example.firstproject.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {


private final UserRepository userRepository;
private final StudentRepository studentRepository;
private final UserDTOMapper userDTOMapper;
private final PasswordEncoder passwordEncoder;
private final JwtService jwtService;


    @Autowired
    public UserService(UserRepository userRepository,
                       StudentRepository studentRepository,
                        PasswordEncoder passwordEncoder,
                       UserDTOMapper userDTOMapper,
                       JwtService jwtService
                        ){
    this.userRepository = userRepository;
    this.studentRepository = studentRepository;
    this.passwordEncoder = passwordEncoder;
    this.userDTOMapper = userDTOMapper;
    this.jwtService = jwtService;
}



//public UserDTO getLoggedUser(){
//    return new UserDTO();
//}



public List<UserDTO> listAllUsers(){
        return userRepository.findAll().
                stream().
                map(userDTOMapper).
                collect(Collectors.toList());
    }

public UserDTO getUserById(Long userId){
       return this.userRepository.findById(userId)
               .map(userDTOMapper).
               orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No student like this"));

    }

public UserDTO getLoggedUser(String token){
        String username = jwtService.extractUsername(token.substring(7));// We are taking care of "Bearer "
        User user = this.userRepository.findUserByUsernameQuery(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new UserDTO(user.getUsername(), user.getEmail(), user.getRole());
}
}



