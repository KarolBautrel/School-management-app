package com.example.firstproject.user;

import com.example.firstproject.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {

    @Query("SELECT s FROM User s WHERE s.email = ?1")
    Optional<User> findUserByEmailQuery(String email);

    @Query("SELECT s FROM User s WHERE s.username = ?1")
    Optional<User> findUserByUsernameQuery(String username);

    Optional<User> findByEmail(String email);

    @Query("SELECT s FROM User s WHERE s.role.id =?1")
    Optional<User> checkIfStudentRegistered(Long id);
}
