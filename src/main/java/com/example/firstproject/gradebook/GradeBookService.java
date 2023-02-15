package com.example.firstproject.gradebook;

import com.example.firstproject.grade.Grade;
import com.example.firstproject.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeBookService {
    GradeBookUtils gradeBookUtils;
    @Autowired
    public GradeBookService(GradeBookRepository gradeBookRepository,
                            GradeBookUtils gradeBookUtils){
    this.gradeBookUtils = gradeBookUtils;
}

public List<Grade> getAllGradeBookGradesById(Long gradeBookId){

    return this.gradeBookUtils.getAllGradeBookGradesById(gradeBookId);
    }

public List<Grade> getGradeBookGradesBySubject(Long gradeBookId, String subject){

    return  this.gradeBookUtils.getGradeBookGradesBySubject(gradeBookId, subject);
}
}
