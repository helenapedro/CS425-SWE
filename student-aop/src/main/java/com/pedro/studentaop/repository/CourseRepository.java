package com.pedro.studentaop.repository;

import com.pedro.studentaop.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}

