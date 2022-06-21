package com.spring.app.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int courseId;

    @NotEmpty(message = "Name cant be empty")
    @Pattern(regexp = "[a-z ]{3,20}", message = "not a valid name")
    @Column(name = "course_name")
    private String courseName;

    @NotEmpty(message = "Title cant be empty")
    @Pattern(regexp = "[a-z ]{3,50}", message = "not a valid Title")
    @Column(name = "course_title")
    private String courseTitle;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "student_course", joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    public Course(int courseId, String courseName, String courseTitle) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseTitle = courseTitle;
    }
}
