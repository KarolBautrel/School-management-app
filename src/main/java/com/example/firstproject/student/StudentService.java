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
    private final StudentDTOMapper studentDTOMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository,StudentDTOMapper studentDTOMapper) {

        this.studentRepository = studentRepository;
        this.studentDTOMapper = studentDTOMapper;
    }

    public List<StudentDTO> getStudents() {

        List<Student> studentList =  this.studentRepository.findAll();
        return studentList.stream().map(studentDTOMapper).toList();

    }

    public StudentDTO getStudent(Long studentId) throws ResponseStatusException {
       Student student = this.studentRepository.findById(studentId)
               .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        return new StudentDTO(student.getName(), student.getAge(), student.getEmail());
    }

    public void addNewStudent(Student student) throws ResponseStatusException {
     Student studentOptional = studentRepository.findStudentByEmail(student.getEmail())
             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No student with this email"));
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) throws ResponseStatusException {
        Student student = studentRepository.findById(studentId)
                        .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "No student with this id"));
        studentRepository.deleteById(studentId);
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
