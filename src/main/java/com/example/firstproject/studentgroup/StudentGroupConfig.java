//package com.example.firstproject.studentgroup;
//import com.example.firstproject.student.Student;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//@Configuration
//public class StudentGroupConfig {
//    //ArrayList<Student> students = new
//    @Bean
//    CommandLineRunner commandLineRunner(StudentGroupRepository repository){
//        return args -> {
//            StudentGroup groupA = new StudentGroup("Group A", 15, new ArrayList<>() );
//            StudentGroup groupB = new StudentGroup("Group B", 18, new ArrayList<>());
//            repository.saveAll(
//                    List.of(groupA, groupB)
//            );
//        };
//    }
//}
