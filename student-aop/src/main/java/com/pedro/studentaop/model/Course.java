package com.pedro.studentaop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "students")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long courseId;

    private String courseCode;
    private String courseName;

    @ManyToMany(mappedBy = "courses")
    @Builder.Default
    private Set<Student> students = new HashSet<>();
}
