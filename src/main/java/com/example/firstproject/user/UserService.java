package com.example.firstproject.user;

import com.example.firstproject.config.JwtService;
import com.example.firstproject.student.Student;
import com.example.firstproject.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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

public AuthenticationResponse registerUser( RegisterAccountDTO registerAccountDTO) {

    Optional<User> userOptional = userRepository.findByEmail(registerAccountDTO.email);
    Optional<User> registeredStudent = userRepository.
            checkIfStudentRegistered(Long.valueOf(registerAccountDTO.studentId));

    if (registeredStudent.isPresent()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Student already singed in to account");
    }
    if (userOptional.isPresent()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email taken");
    }

    if (!registerAccountDTO.password.equals(registerAccountDTO.confirmPassword)) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords incorrect ");
    }
    Student student = this.studentRepository.findById(Long.valueOf(registerAccountDTO.studentId)).
            orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Student not found"));
    User user = new User(registerAccountDTO.username, registerAccountDTO.email,registerAccountDTO.password , student);

    this.userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);

    return new AuthenticationResponse(jwtToken);
}

public TokenUserDTO loginUser(LoginDTO loginDTO){



    System.out.println("WITAM");
    User optionalUser = this.userRepository.findByEmail(loginDTO.email).
            orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"no user"));

    var jwtToken = jwtService.generateToken(optionalUser);

    return new TokenUserDTO(jwtToken, optionalUser.getUsername(), optionalUser.getEmail(), optionalUser.getRole());
}



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


       // return optionalUser;
    }
}



