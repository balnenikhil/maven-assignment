package com.spring.app.entity;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class CourseTest  {

    @InjectMocks
    Course course;


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
        assertEquals("Course(courseId=1, courseName=test, courseTitle=test, students=[])",course.toString());
    }
}