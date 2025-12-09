package com.pedro.studentaop;

import com.pedro.studentaop.model.Classroom;
import com.pedro.studentaop.model.Course;
import com.pedro.studentaop.model.Student;
import com.pedro.studentaop.model.Transcript;
import com.pedro.studentaop.repository.ClassroomRepository;
import com.pedro.studentaop.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final ClassroomRepository classroomRepository;
    private final StudentService studentService;

    public DataLoader(ClassroomRepository classroomRepository, StudentService studentService) {
        this.classroomRepository = classroomRepository;
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) throws Exception {
        Transcript t = Transcript.builder()
                .degreeTitle("BS Computer Science")
                .build();

        Classroom classroom = Classroom.builder()
                .buildingName("McLaughlin Building")
                .roomNumber("M105")
                .build();

        Course c1 = Course.builder()
                .courseCode("CS401")
                .courseName("Modern Prog Practices")
                .build();

        Student s = Student.builder()
                .studentNumber("000-61-0001")
                .firstName("Anna")
                .middleName("Lynn")
                .lastName("Smith")
                .cgpa(3.45)
                .dateOfEnrollment(LocalDate.of(2019, 5, 24))
                .build();

        s.setTranscriptSafe(t);
        classroom.addStudent(s);
        s.addCourse(c1);

        classroomRepository.save(classroom);

        studentService.getAllStudents().forEach(System.out::println);
    }
}
