package com.example.firstproject.studentgroup;
import com.example.firstproject.student.Student;
import com.example.firstproject.student.StudentDTO;
import com.example.firstproject.student.StudentDTOMapper;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
public class StudentGroup {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int size;

    @OneToMany(
     cascade = CascadeType.ALL, mappedBy = "studentGroup")
    @JsonManagedReference
    private List<Student> studentList = new ArrayList<>();


    public StudentGroup(){

    }
    public StudentGroup(String name, int size, List<Student> studentList) {
        this.name = name;
        this.size = size;
        this.studentList = studentList;

    }

    public List<Student> getStudentList() {


        return this.studentList;

    }


    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void addToStudentList(Student student){
        this.studentList.add(student);
        student.setStudentGroup(this);

    }

    public void removeStudentFromList(Student student) throws ResponseStatusException{


        if(!this.studentList.contains(student)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no student in this group");
        }
        this.studentList.remove(student);
        student.setStudentGroup(null);
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
