package com.example.firstproject.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/public/teacher")
public class TeacherController {

    TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }


    @GetMapping("/")
    public List<TeacherDTO> getTeachers(){
        return this.teacherService.getTeachers();
    }

    @GetMapping("/{subject}")
    public List<TeacherDTO> getTeachersBySubject(@PathVariable("subject")
                                                    String subject){
        return this.teacherService.getTeachersBySubject(subject);
    }

}
