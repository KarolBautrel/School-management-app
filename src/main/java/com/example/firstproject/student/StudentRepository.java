package com.example.firstproject.student;

import com.example.firstproject.grade.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // SELECT * FROM student WHERE email = ?
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student>findStudentByEmail(String email);
    @Query("SELECT s FROM Grade s WHERE s.subject = ?1")
    List<Grade>findGradesBySubject(String subject);
}

