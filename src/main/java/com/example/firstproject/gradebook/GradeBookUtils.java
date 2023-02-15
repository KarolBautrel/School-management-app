package com.example.firstproject.gradebook;

import com.example.firstproject.grade.Grade;
import com.example.firstproject.student.Student;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeBookUtils {

    private final GradeBookRepository gradeBookRepository;

    public GradeBookUtils(
            GradeBookRepository gradeBookRepository
    ){
        this.gradeBookRepository = gradeBookRepository;
    }

    public List<Grade> getAllGradeBookGradesById(Long gradeBookId){
        GradeBook gradeBook = this.gradeBookRepository.findById(gradeBookId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No student group with this id"));

        List<List<Grade>> nestedGrades = gradeBook
                .getStudentGroup()
                .getStudentList()
                .stream()
                .map(Student::getGrades )
                .toList();
        List<Grade> gradeList = new ArrayList<>();
        for (List<Grade> grades: nestedGrades){
            for (Grade grade : grades) {
                {
                    gradeList.add(grade);
                }
            }
        }
        return gradeList;
    }




    public List<Grade> getGradeBookGradesBySubject(Long gradeBookId, String subject){
        GradeBook gradeBook = this.gradeBookRepository.findById(gradeBookId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No student group with this id"));
        List<List<Grade>> nestedGrades = gradeBook
                .getStudentGroup()
                .getStudentList()
                .stream()
                .map(student -> student.getGradesBySubject(subject) )
                .toList();

        List<Grade> gradeList = new ArrayList<>();

        for (List<Grade> grades: nestedGrades){
            for (Grade grade : grades) {
                if (grade != null && grade.getSubject().equals(subject)) {
                    gradeList.add(grade);
                }
            }
        }
        return gradeList;
    }

}
