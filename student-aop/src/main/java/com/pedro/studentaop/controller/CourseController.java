package com.pedro.studentaop.controller;

import com.pedro.studentaop.model.Course;
import com.pedro.studentaop.model.Student;
import com.pedro.studentaop.repository.CourseRepository;
import com.pedro.studentaop.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course saved = courseRepository.save(course);
        URI location = URI.create("/courses/" + saved.getCourseId());
        return ResponseEntity.created(location).body(saved);           // 201
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();                             // 200
    }

    @PostMapping("/students/{id}/courses")
    public ResponseEntity<Student> assignCoursesToStudent(@PathVariable Long id,
                                                          @RequestBody List<Long> courseIds) {
        return studentRepository.findById(id)
                .map(student -> {
                    List<Course> courses = courseRepository.findAllById(courseIds);
                    courses.forEach(student::addCourse);               // helper method na entidade
                    Student updated = studentRepository.save(student);
                    return ResponseEntity.ok(updated);                 // 200
                })
                .orElseGet(() -> ResponseEntity.notFound().build());  // 404
    }
}
