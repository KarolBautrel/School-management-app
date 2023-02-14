package com.example.firstproject.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SubjectService {

    SubjectRepository subjectRepository;
    SubjectDTOMapper subjectDTOMapper;

    @Autowired
    SubjectService(SubjectRepository subjectRepository,
                   SubjectDTOMapper subjectDTOMapper
    ) {
        this.subjectRepository = subjectRepository;
        this.subjectDTOMapper = subjectDTOMapper;
    }

    public List<SubjectDTO> getSubjects() {
        return this.subjectRepository
                .findAll()
                .stream()
                .map(subjectDTOMapper)
                .toList();
    }


    public SubjectDTO getSubject(String subject) {
        System.out.println(subject);
        return this.subjectRepository
                .findSubjectByName(subject)
                .map(subjectDTOMapper)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No subject"));
    }
}
