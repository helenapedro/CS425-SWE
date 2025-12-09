package com.pedro.studentaop;

import com.pedro.studentaop.model.Student;
import com.pedro.studentaop.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class StudentAopApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentAopApplication.class, args);
    }
}
