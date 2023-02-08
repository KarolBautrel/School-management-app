package com.example.firstproject.config;

import com.example.firstproject.grade.*;
import com.example.firstproject.student.*;
import com.example.firstproject.studentgroup.*;
import com.example.firstproject.subjects.Subject;
import com.example.firstproject.subjects.SubjectRepository;
import com.example.firstproject.teacher.Teacher;
import com.example.firstproject.teacher.TeacherRepository;
import com.example.firstproject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class Config {

    private final UserRepository userRepository;
    @Bean
    public UserDetailsService userDetailsService(){
        return username ->userRepository.findUserByUsernameQuery(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong username token error"));

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository
            , StudentGroupRepository groupRepository
            , GradeRepository gradeRepository
            , UserRepository userRepository
            , SubjectRepository subjectRepository
            ,TeacherRepository teacherRepository){

        return args -> {
            Subject physics = new Subject("physics", new ArrayList<>());
            Subject biology = new Subject("physics", new ArrayList<>());
            Subject history = new Subject("history", new ArrayList<>());
            Teacher phyTeacher = new Teacher("Dorota",physics);
            Teacher bioTeacher = new Teacher("Agnieszka",biology);
            Teacher histTeacher = new Teacher("Romek", history);
            StudentGroup groupA = new StudentGroup("Group A", 15, new ArrayList<>() );
            StudentGroup groupB = new StudentGroup("Group B", 18, new ArrayList<>());
            Student karol =   new Student(
                    "Karol",
                    LocalDate.of(1995, Month.APRIL, 1),
                    "karol@karol.karol",
                    new ArrayList<>(),
                    groupA

            );
            Student marian =   new Student(
                    "Marian",
                    LocalDate.of(1997, Month.APRIL, 1),
                    "marian@marian.marian",
                    new ArrayList<>(),
                    groupB
            );


            Grade gradeKarol = new Grade("history", 3, karol);
            Grade gradeKarol1 = new Grade("biology", 4,karol);
            Grade gradeKarol2 = new Grade("history", 3,karol);
            Grade gradeMarian = new Grade("biology", 3, marian);
            Grade gradeMarian1 = new Grade("biology", 3, marian);
            Grade gradeMarian2= new Grade("history", 3, marian);
            subjectRepository.saveAll(List.of(physics, history, biology));
            teacherRepository.saveAll(List.of(phyTeacher, bioTeacher, histTeacher));
            repository.saveAll(
                    List.of(karol, marian)
            );
            groupRepository.saveAll(
                    List.of(groupA, groupB)
            );
            gradeRepository.saveAll(List.of(
                    gradeKarol,
                    gradeKarol1,
                    gradeKarol2,
                    gradeMarian1,
                    gradeMarian2,
                    gradeMarian));


        };
    }

}
