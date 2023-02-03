package com.example.firstproject.student;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
          Student karol =   new Student(
                    "Karol",
                    LocalDate.of(1995, Month.APRIL, 1),
                    "karol@karol.karol"
            );
            Student marian =   new Student(
                    "Marian",
                    LocalDate.of(1997, Month.APRIL, 1),
                    "marian@marian.marian"
            );

            repository.saveAll(
                    List.of(karol, marian)
            );
        };
    }

}
