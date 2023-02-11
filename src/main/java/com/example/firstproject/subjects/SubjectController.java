package com.example.firstproject.subjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/public/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;

    }

    @GetMapping("/")
    public List<SubjectDTO> getSubjects() {
        return this.subjectService.getSubjects();
    }

    @GetMapping("/{subject}")
    public SubjectDTO getSubject(@PathVariable("subject") String subject) {
        return this.subjectService.getSubject(subject);
    }

    ;


}
