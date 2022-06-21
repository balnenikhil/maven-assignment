package com.spring.app.dto;


import com.spring.app.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CourseDTOTest {

    @InjectMocks
    CourseDTO course;

    @Test
    void courseTest(){
        course.setCourseId(1);
        course.setCourseName("test");
        course.setCourseTitle("test");
        List<Student> students = new ArrayList<>();
        course.setStudents(students);
        assertEquals("test",course.getCourseTitle() );
        assertEquals("test", course.getCourseName());
        assertEquals(1, course.getCourseId());
        assertEquals( new ArrayList<>(), course.getStudents());
        assertEquals("CourseDTO(courseId=1, courseName=test, courseTitle=test, students=[])",course.toString());
    }

}