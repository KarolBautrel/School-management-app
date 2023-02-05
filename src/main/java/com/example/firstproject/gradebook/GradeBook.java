package com.example.firstproject.gradebook;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GradeBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;


    public GradeBook (){

    };

}
