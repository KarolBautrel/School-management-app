package com.example.firstproject.studentgroup;
import com.example.firstproject.student.Student;
import jakarta.persistence.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class StudentGroup {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int size;

    @OneToMany(
     cascade = CascadeType.ALL
    )
    private List<Student> studentList = new ArrayList<>();


    public StudentGroup(){

    }
    public StudentGroup(String name, int size, List<Student> studentList) {
        this.name = name;
        this.size = size;
        this.studentList = studentList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }


    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void addToStudentList(Student student){
        List<Student> oldStudentList = this.getStudentList();
        oldStudentList.add(student);
        this.setStudentList(oldStudentList);
    }

    public void removeStudentFromList(Student student) throws ResponseStatusException{
        List<Student> oldStudentList = this.getStudentList();

        if(!oldStudentList.contains(student)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no student in this group");
        }
        oldStudentList.remove(student);
        this.setStudentList(oldStudentList);
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
