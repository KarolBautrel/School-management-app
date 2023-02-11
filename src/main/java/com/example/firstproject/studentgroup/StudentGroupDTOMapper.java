package com.example.firstproject.studentgroup;

import com.example.firstproject.student.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudentGroupDTOMapper implements Function<StudentGroup, StudentGroupDTO> {
    @Override
    public StudentGroupDTO apply(StudentGroup studentGroup) {
        return new StudentGroupDTO(studentGroup.getName(),
                studentGroup.getSize(),
                studentGroup.getStudentList()
                        .stream()
                        .map(student -> new StudentDTO(student.getName(),
                                student.getAge(),
                                student.getEmail())).
                        toList());
    }
}
