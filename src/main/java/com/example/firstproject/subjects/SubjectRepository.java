package com.example.firstproject.subjects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long > {

@Query("SELECT s FROM Subject s WHERE s.name =?1")
    Optional<Subject>findSubjectByName(String name);
}