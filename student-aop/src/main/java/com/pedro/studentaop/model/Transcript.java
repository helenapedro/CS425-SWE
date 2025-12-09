package com.pedro.studentaop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transcripts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transcript {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long transcriptId;

    private String degreeTitle;

    @OneToOne(mappedBy = "transcript")
    @ToString.Exclude
    private Student student;
}
