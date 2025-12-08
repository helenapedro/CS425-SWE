package com.pedro.studentaop.service;

import java.util.Collection;

import com.pedro.studentaop.model.Student;

public interface StudentService {
    void addNewStudent(Student student);
    Collection<Student> getAllStudents();
}
