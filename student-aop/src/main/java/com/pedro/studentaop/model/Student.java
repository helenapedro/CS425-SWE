package com.pedro.studentaop.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students", uniqueConstraints = @UniqueConstraint(columnNames = "student_number"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long studentId;

    @Column(name = "student_number", nullable = false, unique = true)
    private String studentNumber;

    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(nullable = false)
    private String lastName;

    private Double cgpa;

    private LocalDate dateOfEnrollment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transcript_id", referencedColumnName = "transcriptId")
    private Transcript transcript;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @Builder.Default
    private Set<Course> courses = new HashSet<>();

    // helper methods to keep associations consistent
    public void addCourse(Course course) {
        courses.add(course);
        course.getStudents().add(this);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.getStudents().remove(this);
    }

    public void setTranscriptSafe(Transcript t) {
        this.transcript = t;
        t.setStudent(this);
    }

    public void setClassroomSafe(Classroom c) {
        this.classroom = c;
    }
}
