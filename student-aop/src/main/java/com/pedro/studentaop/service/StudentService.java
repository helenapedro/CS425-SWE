package com.pedro.studentaop.service;

import com.pedro.studentaop.model.Student;
import com.pedro.studentaop.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Student saveStudent(Student s) {
        return repo.save(s);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student findByStudentNumber(String sn) {
        return repo.findByStudentNumber(sn).orElse(null);
    }
}
