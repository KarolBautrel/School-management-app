package com.example.firstproject.grade;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path="api/v1/grade")
public class GradeController {


    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService){
        this.gradeService = gradeService;
    }



    @GetMapping("/{subject}/{studentId}")
    public ResponseEntity getStudentSubjectGrades(
            @PathVariable("studentId") Long studentId,
            @PathVariable("subject") String subject
    ){
        return ResponseEntity.ok(gradeService.getStudentSubjectGrades(studentId, subject));
    };

    @GetMapping("/{studentId}")
    public ResponseEntity getStudentGrades(@PathVariable("studentId") Long studentId){
        return ResponseEntity.ok(this.gradeService.getStudentGrades(studentId));
    }

    @PostMapping("/{studentId}")
    public void addStudentGrade(
            @PathVariable("studentId")Long studentId,
            @RequestBody Map<String, Object> gradeJson) throws ParseException
    {
        gradeService.addGrade(studentId, gradeJson);
    }

    @PutMapping ("/{gradeId}")
    public void updateStudentGrade(
            @PathVariable("gradeId") Long gradeId,
            @RequestParam("grade") Integer grade
    ){
        gradeService.updateGrade(gradeId, grade);
    }
}
