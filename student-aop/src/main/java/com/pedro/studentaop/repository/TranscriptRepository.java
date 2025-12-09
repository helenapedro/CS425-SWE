package com.pedro.studentaop.repository;

import com.pedro.studentaop.model.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscriptRepository extends JpaRepository<Transcript, Long> {}

