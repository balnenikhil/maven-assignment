package com.spring.app.mapper;

import com.spring.app.dto.CourseDTO;
import com.spring.app.entity.Course;
import com.spring.app.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseMapperTest {

    @Mock
    ModelMapper modelMapper;

    @Mock
    Course course;

    @Mock
    CourseDTO courseDTO;

    @Mock
    List<Student> students;

    @InjectMocks
    CourseMapper courseMapper;

    @Test
    void convertToCourseDTO() {

        course.setCourseId(1);
        course.setCourseName("test");
        course.setCourseTitle("test");
        students = new ArrayList<>();
        course.setStudents(students);

        courseDTO.setCourseId(1);
        courseDTO.setCourseName("test");
        courseDTO.setCourseTitle("test");
        students = new ArrayList<>();
        courseDTO.setStudents(students);
        when(modelMapper.map(course, CourseDTO.class)).thenReturn(courseDTO);
        assertEquals(courseDTO, courseMapper.convertToCourseDTO(course));
    }

    @Test
    void convertToCourse() {

        course.setCourseId(1);
        course.setCourseName("test");
        course.setCourseTitle("test");
        students = new ArrayList<>();
        course.setStudents(students);

        courseDTO.setCourseId(1);
        courseDTO.setCourseName("test");
        courseDTO.setCourseTitle("test");
        students = new ArrayList<>();
        courseDTO.setStudents(students);
        when(modelMapper.map(courseDTO, Course.class)).thenReturn(course);
        assertEquals(course, courseMapper.convertToCourse(courseDTO));

    }
}