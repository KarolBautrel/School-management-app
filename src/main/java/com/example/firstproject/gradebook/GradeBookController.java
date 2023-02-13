package com.example.firstproject.gradebook;

import com.example.firstproject.grade.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public  List<List<Grade>> getAllGradeBookGrades(@PathVariable("gradeBookId") Long gradeBookid){
        return this.gradeBookService.getAllGradeBookGrades(gradeBookid);
    }


}
