package com.example.firstproject.gradebook;

import com.example.firstproject.grade.Grade;
import com.example.firstproject.student.Student;
import com.example.firstproject.studentgroup.StudentGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeBookService {

    GradeBookRepository gradeBookRepository;
    @Autowired
    public GradeBookService(GradeBookRepository gradeBookRepository){
    this.gradeBookRepository = gradeBookRepository;
}

public List<List<Grade>> getAllGradeBookGrades(Long gradeBookId){
    GradeBook gradeBook = this.gradeBookRepository.findById(gradeBookId)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No student group with this id"));

    return gradeBook
            .getStudentGroup()
            .getStudentList()
            .stream()
            .map(Student::getGrades)
            .toList();
    }


}
