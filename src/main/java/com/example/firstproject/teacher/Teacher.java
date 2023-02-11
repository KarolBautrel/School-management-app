package com.example.firstproject.teacher;

import com.example.firstproject.subjects.Subject;
import jakarta.persistence.*;

@Entity
@Table
public class Teacher {


    @Id
    @GeneratedValue
    Long id;

    public String name;

    @ManyToOne
    @JoinColumn(name = "subject_fk")
    public Subject subject;


    public Teacher() {

    }

    public Teacher(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
