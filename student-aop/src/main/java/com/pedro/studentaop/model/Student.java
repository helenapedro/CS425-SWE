package com.pedro.studentaop.model;

public class Student {
	private Long id;
    private String name;
    private double grade;

    public Student() {}
    public Student(Long id, String name, double grade) {
        this.id = id; this.name = name; this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', grade=" + grade + "}";
    }
    
 
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getGrade() { return grade; }
    public void setGrade(double grade) { this.grade = grade; }

}
