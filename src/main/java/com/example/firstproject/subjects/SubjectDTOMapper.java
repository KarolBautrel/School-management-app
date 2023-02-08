package com.example.firstproject.subjects;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SubjectDTOMapper implements Function<Subject, SubjectDTO> {


    @Override
    public SubjectDTO apply(Subject subject) {
        return new SubjectDTO(subject.getName(), subject.getTeachers());
    }
}
