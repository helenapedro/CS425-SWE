package com.pedro.studentaop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classrooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "students")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classroomId;

    private String buildingName;
    private String roomNumber;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.PERSIST)
    @Builder.Default
    @JsonIgnore
    private List<Student> students = new ArrayList<>();


    public void addStudent(Student s) {
        students.add(s);
        s.setClassroom(this);
    }
}
