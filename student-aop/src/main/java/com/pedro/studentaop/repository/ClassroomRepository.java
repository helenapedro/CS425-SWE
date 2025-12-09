package com.pedro.studentaop.repository;

import com.pedro.studentaop.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {}
