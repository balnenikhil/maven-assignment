package com.spring.app.dto;

import com.spring.app.entity.Student;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CourseDTO {


    private int courseId;

    @NotEmpty(message = "Title cant be empty")
    @Pattern(regexp = "[a-z ]{3,50}", message = "not a valid Name")
    private String courseName;

    @NotEmpty(message = "Title cant be empty")
    @Pattern(regexp = "[a-z ]{3,50}", message = "not a valid Title")
    private String courseTitle;

    private List<Student> students;

}
