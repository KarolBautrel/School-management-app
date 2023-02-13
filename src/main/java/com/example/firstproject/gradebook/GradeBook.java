package com.example.firstproject.gradebook;

import com.example.firstproject.grade.Grade;
import com.example.firstproject.student.Student;
import com.example.firstproject.studentgroup.StudentGroup;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class GradeBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @OneToOne
    StudentGroup studentGroup;

    public GradeBook (){

    };
    public GradeBook(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }


}
