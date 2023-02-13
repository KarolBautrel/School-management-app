package com.example.firstproject.gradebook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeBookRepository extends JpaRepository<GradeBook,Long > {
}
