package com.spring.app.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;


    @NotEmpty(message = "Name cant be empty")
    @Pattern(regexp = "[a-z ]{3,50}", message = "not a valid Name")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Name cant be empty")
    @Pattern(regexp = "[a-z ]{3,50}", message = "not a valid Name")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "email cant be empty")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "not a valid email")
    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "student_course", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    public Student(int studentId, String firstName, String lastName, String email) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void addCourse(Course course){
        if(courses == null){
            courses = new ArrayList<>();
        }
        courses.add(course);
    }
}
