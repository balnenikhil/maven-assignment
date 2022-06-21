package com.spring.app.service.impl;



import com.spring.app.entity.Course;
import com.spring.app.entity.Student;
import com.spring.app.repository.StudentRepository;
import com.spring.app.service.StudentService;
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
class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Mock
    List<Student> students;

    @Mock
    Course course;

    @Mock
    List<Course> courses;

    @Mock
    Student student;

    @Test
    void findAll() {

        student = new Student(1, "f_test", "l_test", "test@gmail.com", courses);
        Mockito.when(studentRepository.findAll()).thenReturn(Stream.of(student).collect(Collectors.toList()));
        Assertions.assertEquals(1, studentService.findAll().size());

    }

    @Test
    void findById() {

        student = new Student(1, "f_test", "l_test", "test@gmail.com", courses);
        Mockito.when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        Assertions.assertEquals("test@gmail.com", studentRepository.findById(1).get().getEmail());
    }

    @Test
    void save() {

        student = new Student(1, "f_test", "l_test", "test@gmail.com", courses);
        studentService.save(student);
        Mockito.verify(studentRepository,Mockito.times(1)).save(student);
    }

    @Test
    void deleteById() {
        int id = 1;
        studentService.deleteById(id);
        Mockito.verify(studentRepository,Mockito.times(1)).deleteById(id);
    }


}