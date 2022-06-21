package com.spring.app.mapper;


import com.spring.app.dto.StudentDTO;
import com.spring.app.entity.Course;
import com.spring.app.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentMapperTest {

    @InjectMocks
    StudentMapper studentMapper;

    @Mock
    Student student;

    @Mock
    StudentDTO studentDTO;

    @Mock
    ModelMapper modelMapper;

    @Mock
    List<Course> courses;

    @Test
    void convertToStudentDTO() {

        student.setStudentId(1);
        student.setFirstName("test");
        student.setLastName("test");
        student.setEmail("test@gmail.com");
        courses= new ArrayList<>();
        student.setCourses(courses);

        studentDTO.setStudentId(1);
        studentDTO.setFirstName("test");
        studentDTO.setLastName("test");
        studentDTO.setEmail("test@gmail.com");
        courses= new ArrayList<>();
        student.setCourses(courses);

        when(modelMapper.map(student, StudentDTO.class)).thenReturn(studentDTO);
        assertEquals(studentDTO, studentMapper.convertToStudentDTO(student));
    }

    @Test
    void convertToStudent() {

        student.setStudentId(1);
        student.setFirstName("test");
        student.setLastName("test");
        student.setEmail("test@gmail.com");
        courses= new ArrayList<>();
        student.setCourses(courses);

        studentDTO.setStudentId(1);
        studentDTO.setFirstName("test");
        studentDTO.setLastName("test");
        studentDTO.setEmail("test@gmail.com");
        courses= new ArrayList<>();
        student.setCourses(courses);

        when(modelMapper.map(studentDTO, Student.class)).thenReturn(student);
        assertEquals(student, studentMapper.convertToStudent(studentDTO));
    }
}