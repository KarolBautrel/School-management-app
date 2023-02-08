package com.example.firstproject.gradebook;

import com.example.firstproject.studentgroup.StudentGroup;
import jakarta.persistence.*;

@Entity
public class GradeBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @OneToOne
    StudentGroup studentGroup;




    public GradeBook (){

    };

}
