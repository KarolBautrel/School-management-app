package com.example.firstproject.user;

import com.example.firstproject.roles.SchoolRoles;
import com.example.firstproject.student.Student;
import com.example.firstproject.teacher.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;


@Builder
@AllArgsConstructor
@Entity
@Table(name = "\"User\"") // Postgres and JPA dont allow to table named user
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Student  studentRole;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher teacherRole;

    private SchoolRoles role;

    public User() {

    }

    public User(String username, String email, String password, Student studentRole) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.studentRole = studentRole;
        this.role = SchoolRoles.STUDENT;
    }

    public User(String username, String email, String password, Teacher teacherRole) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.teacherRole = teacherRole;
        this.role = SchoolRoles.TEACHER;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public SchoolRoles getRole() {
        return this.role;
    }

    public void setRole(SchoolRoles role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public Student getStudentRole() {
        return studentRole;
    }

    public void setStudentRole(Student studentRole) {
        this.studentRole = studentRole;
    }

    public Teacher getTeacherRole() {
        return teacherRole;
    }

    public void setTeacherRole(Teacher teacherRole) {
        this.teacherRole = teacherRole;
    }
}
