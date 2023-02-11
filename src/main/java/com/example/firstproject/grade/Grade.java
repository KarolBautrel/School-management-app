package com.example.firstproject.grade;

import com.example.firstproject.student.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private Integer grade;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_fk")
    @JsonBackReference
    private Student student;


    public Grade() {

    }


    public Grade(String subject, Integer grade, Student student) {
        this.subject = subject;
        this.grade = grade;
        this.student = student;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Grade{" + "id=" + id + ", subject='" + subject + '\'' + ", grade=" + grade + ", student=" + student + '}';
    }
}
