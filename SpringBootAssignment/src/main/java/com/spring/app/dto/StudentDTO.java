package com.spring.app.dto;

import com.spring.app.entity.Course;
import lombok.*;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class StudentDTO {

    private int studentId;



    @NotEmpty(message = "Name cant be empty")
    @Pattern(regexp = "[a-z ]{3,50}", message = "not a valid Name")
    private String firstName;


    @NotEmpty(message = "Name cant be empty")
    @Pattern(regexp = "[a-z ]{3,50}", message = "not a valid Name")
    private String lastName;


    @NotEmpty(message = "email cant be empty")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "not a valid email")
    private String email;

    private List<Course> courses;
}
