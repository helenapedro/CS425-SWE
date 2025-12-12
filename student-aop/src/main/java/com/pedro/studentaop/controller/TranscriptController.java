package com.pedro.studentaop.controller;

import com.pedro.studentaop.model.Transcript;
import com.pedro.studentaop.repository.TranscriptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/transcripts")
@RequiredArgsConstructor
public class TranscriptController {
    private final TranscriptRepository transcriptRepository;

    @PostMapping
    public ResponseEntity<Transcript> createTranscript(@RequestBody Transcript transcript) {
        Transcript saved = transcriptRepository.save(transcript);
        URI location = URI.create("/transcripts/" + saved.getTranscriptId());
        return ResponseEntity.created(location).body(saved);           // 201
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transcript> getTranscript(@PathVariable Long id) {
        return transcriptRepository.findById(id)
                .map(ResponseEntity::ok)                               // 200
                .orElseGet(() -> ResponseEntity.notFound().build());  // 404
    }
}
