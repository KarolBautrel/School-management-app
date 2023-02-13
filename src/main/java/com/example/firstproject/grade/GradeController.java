package com.example.firstproject.grade;

import com.example.firstproject.subjects.SubjectDTO;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/public/grade")
public class GradeController {
    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }


    @GetMapping("/{subject}/{studentId}")
    public List<Grade> getStudentSubjectGrades(
            @PathVariable("studentId") Long studentId,
            @PathVariable("subject") String subject
    ) {
        return gradeService.getStudentSubjectGrades(studentId, subject);
    }

    ;

    @GetMapping("/{studentId}")
    public List<Grade> getStudentGrades(@PathVariable("studentId") Long studentId) {
        return this.gradeService.getStudentGrades(studentId);
    }

    @PostMapping("/{studentId}")
    public void addStudentGrade(
            @PathVariable("studentId") Long studentId,
            @RequestBody Map<String, Object> gradeJson) throws ParseException {
        gradeService.addGrade(studentId, gradeJson);
    }

    @PutMapping("/{gradeId}")
    public void updateStudentGrade(
            @PathVariable("gradeId") Long gradeId,
            @RequestParam("grade") Integer grade
    ) {
        gradeService.updateGrade(gradeId, grade);
    }

    @DeleteMapping("/{gradeId}")
    public void updateStudentGrade(
            @PathVariable("gradeId") Long gradeId

    ) {
        gradeService.deleteGrade(gradeId);
    }
}
