//package com.example.firstproject.student;
//
//import com.example.firstproject.studentgroup.StudentGroup;
//import com.example.firstproject.studentgroup.StudentGroupRepository;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class StudentConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(StudentRepository repository, StudentGroupRepository groupRepository ){
//        return args -> {
//          Student karol =   new Student(
//                    "Karol",
//                    LocalDate.of(1995, Month.APRIL, 1),
//                    "karol@karol.karol"
//            );
//            Student marian =   new Student(
//                    "Marian",
//                    LocalDate.of(1997, Month.APRIL, 1),
//                    "marian@marian.marian"
//            );
//            StudentGroup groupA = new StudentGroup("Group A", 15, new ArrayList<>() );
//            StudentGroup groupB = new StudentGroup("Group B", 18, new ArrayList<>());
//
//            repository.saveAll(
//                    List.of(karol, marian)
//            );
//            groupRepository.saveAll(
//                    List.of(groupA, groupB)
//            );
//
//
//        };
//    }
//
//}
