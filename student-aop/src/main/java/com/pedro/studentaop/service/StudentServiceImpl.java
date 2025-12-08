package com.pedro.studentaop.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pedro.studentaop.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

    private final List<Student> store = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public void addNewStudent(Student student) {
        store.add(student);
        log.info("Student added: {}", student); 
    }

    @Override
    public Collection<Student> getAllStudents() {
        return List.copyOf(store);
    }
}
