package com.example.firstproject.subject;

import com.example.firstproject.teacher.Teacher;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Subject {
    @Id
    @GeneratedValue
    Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "subject")
    private List<Teacher> teachers;


    public Subject() {

    }

    public Subject(String name, List<Teacher> teachers) {
        this.name = name;
        this.teachers = teachers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Teacher> getTeachers() {
        return this.teachers;

    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teachers=" + teachers +
                '}';
    }

}
