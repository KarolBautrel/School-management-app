package com.example.firstproject.user;

import com.example.firstproject.config.EncodingService;
import com.example.firstproject.student.Student;
import com.example.firstproject.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


private final UserRepository userRepository;
private final StudentRepository studentRepository;
private final EncodingService encodingService;
    @Autowired
    public UserService(UserRepository userRepository, StudentRepository studentRepository, EncodingService encodingService){
    this.userRepository = userRepository;
    this.studentRepository = studentRepository;
    this.encodingService = encodingService;
}

public void registerUser( UserDTO userDTO){

    Optional<User> userOptional = userRepository.findUserByEmail(userDTO.email);
    Optional<User> registeredStudent = userRepository.
            checkIfStudentRegistered(Long.valueOf(userDTO.studentId));

    if (registeredStudent.isPresent()){
        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Student already singed in to account");
    }
    if (userOptional.isPresent()){
        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "email taken");
    }

    if (!userDTO.password.equals(userDTO.confirmPassword)){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords incorrect ");
    }

    Student student = this.studentRepository.findById(Long.valueOf(userDTO.studentId)).
            orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Student not found"));
    PasswordEncoder passwordEncoder = this.encodingService.encoder();
    String hashedPasword = passwordEncoder.encode(userDTO.password);
    User user = new User(userDTO.username, userDTO.email, hashedPasword, student);
    this.userRepository.save(user);
}

public void loginUser(LoginDTO loginDTO){
//        PasswordEncoder passwordEncoder = this.encodingService.encoder();
//        passwordEncoder.matches()
}



public List<User> listAllUsers(){
        return userRepository.findAll();
    }

public Optional<User> getUserById(Long userId){
       Optional<User> optionalUser = this.userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "There is no user with this id");
        }
        return optionalUser;
    }
}



