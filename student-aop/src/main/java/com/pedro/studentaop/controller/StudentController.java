package com.pedro.studentaop.controller;

import com.pedro.studentaop.model.Student;
import com.pedro.studentaop.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student saved = studentRepository.save(student);
        URI location = URI.create("/students/" + saved.getStudentId());
        return ResponseEntity.created(location).body(saved);   // HTTP 201
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();                    // HTTP 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(ResponseEntity::ok)                       // 200
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                 @RequestBody Student changes) {
        return studentRepository.findById(id)
                .map(existing -> {
                    existing.setStudentNumber(changes.getStudentNumber());
                    existing.setFirstName(changes.getFirstName());
                    existing.setMiddleName(changes.getMiddleName());
                    existing.setLastName(changes.getLastName());
                    existing.setCgpa(changes.getCgpa());
                    existing.setDateOfEnrollment(changes.getDateOfEnrollment());
                    Student updated = studentRepository.save(existing);
                    return ResponseEntity.ok(updated);         // 200
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();         // 404
        }
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();            // 204
    }
}
