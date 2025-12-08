package com.pedro.studentaop;

import com.pedro.studentaop.model.Student;
import com.pedro.studentaop.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class StudentAopApplication implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(StudentAopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        studentService.addNewStudent(new Student(1L, "Alice", 8.5));
        studentService.addNewStudent(new Student(2L, "Bob", 7.2));
        studentService.addNewStudent(new Student(3L, "Carla", 9.1));

        System.out.println("ALL STUDENTS:");
        studentService.getAllStudents().forEach(System.out::println);
    }
}
