package com.example.firstproject.subject;

import com.example.firstproject.teacher.TeacherDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SubjectDTOMapper implements Function<Subject, SubjectDTO> {


    @Override
    public SubjectDTO apply(Subject subject) {

        return new SubjectDTO(subject.getName()
                , subject.getTeachers().stream()
                .map(teacher -> new TeacherDTO(
                        teacher.getName(),
                        teacher.getSubject().getName())).toList());
    }
}
