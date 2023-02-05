package com.example.firstproject.config;

import com.example.firstproject.grade.*;
import com.example.firstproject.student.*;
import com.example.firstproject.studentgroup.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository, StudentGroupRepository groupRepository, GradeRepository gradeRepository ){

        return args -> {
            Student karol =   new Student(
                    "Karol",
                    LocalDate.of(1995, Month.APRIL, 1),
                    "karol@karol.karol",
                    new ArrayList<>()

            );
            Student marian =   new Student(
                    "Marian",
                    LocalDate.of(1997, Month.APRIL, 1),
                    "marian@marian.marian",
                    new ArrayList<>()
            );
            StudentGroup groupA = new StudentGroup("Group A", 15, new ArrayList<>() );
            StudentGroup groupB = new StudentGroup("Group B", 18, new ArrayList<>());
            Grade gradeKarol = new Grade("history", 3, karol);
            Grade gradeKarol1 = new Grade("biology", 4,karol);
            Grade gradeKarol2 = new Grade("history", 3,karol);
            Grade gradeMarian = new Grade("biology", 3, marian);
            Grade gradeMarian1 = new Grade("biology", 3, marian);
            Grade gradeMarian2= new Grade("history", 3, marian);

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
