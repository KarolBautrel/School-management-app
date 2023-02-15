package com.example.firstproject.gradebook;

import com.example.firstproject.grade.Grade;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/public/gradebook")
public class GradeBookController {

    GradeBookService gradeBookService;
    @Autowired
    public GradeBookController( GradeBookService gradeBookService){
    this.gradeBookService = gradeBookService;
    }

    @GetMapping("/{gradeBookId}")
    public  List<Grade> getAllGradeBookGrades(@PathVariable("gradeBookId") Long gradeBookId){
        return this.gradeBookService.getAllGradeBookGradesById(gradeBookId);
    }

    @GetMapping("/{subject}/{gradeBookId}")
    public List<Grade> getGradeBookGradesBySubject(
            @PathVariable("gradeBookId")Long gradeBookId, @PathVariable("subject") String subject){
        return this.gradeBookService.getGradeBookGradesBySubject(gradeBookId, subject);
    }
}
