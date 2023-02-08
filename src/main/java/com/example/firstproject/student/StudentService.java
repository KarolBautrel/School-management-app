package com.example.firstproject.student;

import com.example.firstproject.auth.ChangePasswordDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){

        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return this.studentRepository.findAll();
    }

    public Optional<Student> getStudent(Long studentId) throws ResponseStatusException{
        Optional<Student> studentOptional= this.studentRepository.findById(studentId);
        if(studentOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No student with those id");
        }
        return studentOptional;
    }
    public void addNewStudent(Student student) throws ResponseStatusException {
       Optional<Student> studentOptional =  studentRepository.findStudentByEmail(student.getEmail());
       if (studentOptional.isPresent()){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email is already taken");
        }
       studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) throws ResponseStatusException{
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No student with this id");
        }
        studentRepository.deleteById(studentId);
        System.out.println("DELETED");
    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email) throws ResponseStatusException {
        Student student = this.studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No student with this id"));
        if (name != null && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email taken");
            }
            student.setEmail(email);
        }
    }




}
