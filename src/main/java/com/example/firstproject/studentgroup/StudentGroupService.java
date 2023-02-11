package com.example.firstproject.studentgroup;

import com.example.firstproject.student.Student;
import com.example.firstproject.student.StudentDTO;
import com.example.firstproject.student.StudentDTOMapper;
import com.example.firstproject.student.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudentGroupService {

    private final StudentGroupRepository studentGroupRepository;
    private final StudentRepository studentRepository;

    private final StudentGroupDTOMapper studentGroupDTOMapper;

    @Autowired
    public StudentGroupService(StudentGroupRepository studentGroupRepository,
                               StudentRepository studentRepository,
                               StudentGroupDTOMapper studentGroupDTOMapper) {
        this.studentGroupRepository = studentGroupRepository;
        this.studentRepository = studentRepository;
        this.studentGroupDTOMapper = studentGroupDTOMapper;
    }

    public List<StudentGroupDTO> getStudentGroups() {
        List<StudentGroup> rawStudentGroups = this.studentGroupRepository.findAll();
        return rawStudentGroups.stream()
                .map(studentGroupDTOMapper)
                .toList();
    }

    public Optional<StudentGroup> getStudentGroup(Long groupId) throws ResponseStatusException {
        Optional<StudentGroup> possibleStudentGroup = this.studentGroupRepository.findById(groupId);
        if (possibleStudentGroup.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no group with those id");
        }
        return possibleStudentGroup;
    }

    @Transactional
    public void addStudentToGroup(Long studentId, Long groupId) {
        StudentGroup studentGroup = studentGroupRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No student group with this id"));
        Student possibleStudent = studentRepository.findById(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No student with this id"));
        studentGroup.addToStudentList(possibleStudent);


    }

    @Transactional
    public void removeStudentFromGroup(Long studentId, Long groupId) {
        StudentGroup studentGroup = studentGroupRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No student group with this id"));
        Student possibleStudent = studentRepository.findById(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No student with this id"));

        studentGroup.removeStudentFromList(possibleStudent);
    }
}
