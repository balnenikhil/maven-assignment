package com.spring.app.controller;

import com.spring.app.dto.CourseDTO;

import com.spring.app.entity.Course;
import com.spring.app.entity.Student;

import com.spring.app.mapper.CourseMapper;
import com.spring.app.repository.CourseRepository;
import com.spring.app.service.CourseService;


import com.spring.app.service.StudentService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
class CourseControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Mock
    BindingResult bindingResult;


    @MockBean
    CourseService courseService;


    @MockBean
    StudentService studentService;

    @Mock
    Course course;

    @Mock
    Student student;

    @MockBean
    CourseRepository courseRepository;

    @Mock
    List<Student> students;


    @Mock
    List<Course> courses;

    @Mock
    CourseDTO courseDTO;

    private List<Course> getCourses(){

        Course course1 = new Course(1,"test", "test_title", students);

        List<Course> courses1= new ArrayList<>();
        courses1.add(course1);

        return courses1;
    }





    @Test
    void getAllCourses() throws Exception {

       courses=getCourses();
       when(courseService.findAll()).thenReturn(courses);

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/courses/list")).andExpect(status().is(200));
    }

    @Test
    void showFormForAdd() throws Exception {


        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/courses/courseForm")).andExpect(status().is(200));

    }

    @Test
    void saveCourse() throws Exception {

        student = new Student(1, "testf", "testl", "test1@gmail.com", getCourses());
        students = new ArrayList<>();
        students.add(student);
        courseDTO = new CourseDTO(1, "test", "testtitle", students);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(post("/courses/save").flashAttr("course",courseDTO )).andExpect(status().is(302)).andExpect(view().name("redirect:/courses/list"));
    }

    @Test
    void updateCourse() throws Exception {
        student = new Student(1, "testf", "testl", "test1@gmail.com", getCourses());
        students = new ArrayList<>();
        students.add(student);
        courseDTO = new CourseDTO(1, "test", "testtitle", students);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(post("/courses/update").flashAttr("course",courseDTO )).andExpect(status().is(302)).andExpect(view().name("redirect:/courses/list"));
    }

    @Test
    void saveCourseBindingErrors() throws  Exception{

        student = new Student(1, "testf", "testl", "test1@gmail.com", getCourses());
        students = new ArrayList<>();
        students.add(student);
        courseDTO = new CourseDTO(1, "Test", "testtitle", students);
        when(bindingResult.hasErrors()).thenReturn(true);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(post("/courses/save").flashAttr("course",courseDTO )).andExpect(status().is(200)).andExpect(view().name("CourseForm"));
    }

    @Test
    void updateCourseBindingErrors() throws  Exception{

        student = new Student(1, "testf", "Testl", "test1@gmail.com", getCourses());
        students = new ArrayList<>();
        students.add(student);
        courseDTO = new CourseDTO(1, "Test", "testtitle", students);
        when(bindingResult.hasErrors()).thenReturn(true);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(post("/courses/update").flashAttr("course",courseDTO )).andExpect(status().is(200)).andExpect(view().name("CourseUpdateForm"));
    }


    @Test
    void updateCourseForm() throws Exception {


        courses = getCourses();
        Mockito.when(courseRepository.findById(0)).thenReturn(Optional.of(courses.get(0)));
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/courses/updateCourseForm").queryParam("courseId","0")).andExpect(status().is(500));
    }

    @Test
    void getStudents() throws Exception {

        students = studentService.getCourseStudents(1);

        course = new Course(1,"test", "testtitle", students);

        Mockito.when(studentService.getCourseStudents(1)).thenReturn(course.getStudents());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        mockMvc.perform(get("/courses/getStudents").queryParam("courseId","1")).andExpect(status().is(500));


    }
}