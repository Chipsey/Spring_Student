package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student Udana = new Student(
                    "Udana",
                    "udana@gmail.com",
                    LocalDate.of(2000, Month.MAY,25)

            );
            Student Malithi = new Student(
                    "Malithi",
                    "malithi@gmail.com",
                    LocalDate.of(1998, Month.NOVEMBER,21)
            );

            repository.saveAll(
                    List.of(Udana, Malithi)
            );
        };
    }
}
