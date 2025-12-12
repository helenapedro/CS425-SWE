package com.pedro.studentaop.controller;

import com.pedro.studentaop.model.Classroom;
import com.pedro.studentaop.model.Student;
import com.pedro.studentaop.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/classrooms")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomRepository classroomRepository;

    @PostMapping
    public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom classroom) {
        Classroom saved = classroomRepository.save(classroom);
        URI location = URI.create("/classrooms/" + saved.getClassroomId());
        return ResponseEntity.created(location).body(saved);           // 201
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroom(@PathVariable Long id) {
        return classroomRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getStudentsInClassroom(@PathVariable Long id) {
        return classroomRepository.findById(id)
                .map(c -> ResponseEntity.ok(c.getStudents()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
