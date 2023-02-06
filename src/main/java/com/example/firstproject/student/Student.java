package com.example.firstproject.student;
import com.example.firstproject.grade.Grade;
import com.example.firstproject.studentgroup.StudentGroup;
import com.example.firstproject.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table
public class Student {


    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Transient
    private Integer age;
    private LocalDate dateOfBirth;
    private String email;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Grade> grades = new ArrayList<>();

    public Student(){

    }



    public List<Grade> getGradesBySubject(String subject){
        List<Grade> studentGrades = this.getGrades();
        List<Grade>subjectGrades = new ArrayList<>();
        for (Grade grade : studentGrades){
            if (grade.getSubject().equals(subject)){
                subjectGrades.add(grade);
            }
        }
        return subjectGrades;
    }


    public Student( String name, LocalDate dateOfBirth, String email, List<Grade> grades) {

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.grades = grades;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();

    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                '}';
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
