package com.spring.app.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class StudentTest {

    @InjectMocks
    Student student;

    @Mock
    Course course;

    @Test
    void studentTest(){

        student.setStudentId(1);
        student.setFirstName("test");
        student.setLastName("test");
        student.setEmail("test@gmail.com");
        List<Course> courses= new ArrayList<>();
        student.setCourses(courses);
        assertEquals(1,student.getStudentId());
        assertEquals("test", student.getFirstName());
        assertEquals("test", student.getLastName());
        assertEquals("test@gmail.com", student.getEmail());
        assertEquals(new ArrayList<>(), student.getCourses());
        assertEquals("Student(studentId=1, firstName=test, lastName=test, email=test@gmail.com, courses=[])",student.toString());
        course.setCourseId(1);
        course.setCourseName("test");
        course.setCourseTitle("test");
        student.addCourse(course);

    }
}