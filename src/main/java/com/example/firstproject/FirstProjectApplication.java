package com.example.firstproject;

import com.example.firstproject.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class FirstProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstProjectApplication.class, args);
	}
	@GetMapping("/")
	public List<Student> hello(){
		return List.of(
				new Student(1L,
						"Karol",
						27,
						LocalDate.of(1995,  4, 1),
						"karol.bautrel@gmail.com" )
		);
	}
}
