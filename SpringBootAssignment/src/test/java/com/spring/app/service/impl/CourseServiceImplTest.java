package com.spring.app.service.impl;



import com.spring.app.entity.Course;
import com.spring.app.entity.Student;
import com.spring.app.repository.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;



@SpringBootTest
class CourseServiceImplTest {

    @Autowired
    CourseServiceImpl courseService;

    @MockBean
    CourseRepository courseRepository;

    @Mock
    List<Student> students;

    @Mock
    Course course;

    @Mock
    List<Course> courses;

    @Test
    void findAll() {

        course = new Course(1,"test", "test_title", students);
        Mockito.when(courseRepository.findAll()).thenReturn(Stream.of(course).collect(Collectors.toList()));
        Assertions.assertEquals(1, courseService.findAll().size());

    }

    @Test
    void findById() {
        course = new Course(1,"test", "test_title", students);
        Mockito.when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        Assertions.assertEquals("test_title", courseRepository.findById(1).get().getCourseTitle());
    }

    @Test
    void save() {

        course = new Course(1,"test", "test_title", students);
        courseService.save(course);
        Mockito.verify(courseRepository,Mockito.times(1)).save(course);
    }

    @Test
    void deleteById() {

        int id=1;
        courseService.deleteById(id);
        Mockito.verify(courseRepository,Mockito.times(1)).deleteById(id);

    }


}