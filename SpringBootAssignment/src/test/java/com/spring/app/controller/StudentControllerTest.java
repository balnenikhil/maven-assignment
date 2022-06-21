package com.spring.app.controller;


import com.spring.app.dto.CourseDTO;
import com.spring.app.dto.StudentDTO;
import com.spring.app.entity.Course;
import com.spring.app.entity.Student;
import com.spring.app.mapper.CourseMapper;
import com.spring.app.repository.CourseRepository;
import com.spring.app.repository.StudentRepository;
import com.spring.app.service.CourseService;
import com.spring.app.service.StudentService;
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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@SpringBootTest
 class StudentControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Mock
    BindingResult bindingResult;

    @MockBean
    StudentService studentService;

    @MockBean
    CourseService courseService;

    @InjectMocks
    StudentController studentController;

    @MockBean
    CourseRepository courseRepository;

    @MockBean
    StudentRepository studentRepository;

    @Mock
    CourseDTO courseDTO;

    @Mock
    Model model;

    @Mock
    StudentDTO studentDTO;

    @Mock
    Course course;

    @Mock
    List<Course>courses;

    @Mock
    List<Student> students;

    @Mock
    Student student;

    @Mock
    CourseMapper courseMapper;
    private List<Student> getStudents(){

        courses = new ArrayList<>();
        students = new ArrayList<>();
        student = new Student(1, "testf", "testl", "test1@gmail.com", courses);
        students.add(student);

        return students;
    }

    @Test
     void getAllStudents() throws Exception {


        students=getStudents();
        when(studentService.findAll()).thenReturn(students);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/students/list")).andExpect(status().is(200));
    }

    @Test
     void showFormForAdd() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/students/studentForm")).andExpect(status().is(200));
    }

    @Test
    void saveStudent() throws Exception {

        course = new Course(1, "test", "testtile", getStudents());
        courses = new ArrayList<>();
        courses.add(course);
        studentDTO = new StudentDTO(1, "testf", "testl", "test1@gmail.com", courses);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(post("/students/save").flashAttr("student",studentDTO )).andExpect(status().is(302)).andExpect(view().name("redirect:/students/list"));
    }

    @Test
    void updateStudent() throws Exception {


        course = new Course(1, "test", "testtile", getStudents());
        courses = new ArrayList<>();
        courses.add(course);
        studentDTO = new StudentDTO(1, "testf", "testl", "test1@gmail.com", courses);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(post("/students/update").flashAttr("student",studentDTO )).andExpect(status().is(302)).andExpect(view().name("redirect:/students/list"));


    }

    @Test
    void updateStudentForm() throws Exception {



        students=getStudents();
        Mockito.when(studentService.findById(0)).thenReturn(students.get(0));
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/students/updateStudentForm").queryParam("studentId","0")).andExpect(status().is(200));

    }



    @Test
    void saveCourseToStudent() throws Exception {

        course = new Course(1, "test", "testtile");
        courses = new ArrayList<>();
        courses.add(course);
        //Student student1 = new Student(1, "testf", "testl", "test1@gmail.com", courses);
        Mockito.when(courseMapper.convertToCourse(courseDTO)).thenReturn(course);
        Mockito.when(studentService.findById(1)).thenReturn(student);
        Mockito.when(courseService.findById(1)).thenReturn(course);
        Mockito.when(student.getCourses()).thenReturn(null);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(post("/students/saveCourse").flashAttr("course",courseDTO ).queryParam("studentId", "1")).andExpect(status().is(302));
    }

    @Test
    void saveCourseToStudentNotNull() throws Exception {

        course = new Course(1, "test", "testtile");
        courses = new ArrayList<>();
        courses.add(course);
        //Student student1 = new Student(1, "testf", "testl", "test1@gmail.com", courses);
        Mockito.when(courseMapper.convertToCourse(courseDTO)).thenReturn(course);
        Mockito.when(studentService.findById(1)).thenReturn(student);
        Mockito.when(courseService.findById(1)).thenReturn(course);
        Mockito.when(student.getCourses()).thenReturn(courses);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(post("/students/saveCourse").flashAttr("course",courseDTO).queryParam("studentId", "1")).andExpect(status().is(302));
    }



    @Test
    void getCourses() throws Exception {

        course = new Course(1, "test", "testtile");
        courses = new ArrayList<>();
        courses.add(course);
        student = new Student(1, "testf", "testl", "test1@gmail.com", courses);
        Mockito.when(courseRepository.getStudentCourses(1)).thenReturn(student.getCourses());
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/students/getCourses").queryParam("studentId", "1")).andExpect(status().is(500)).andExpect(view().name("Exception"));


    }

    @Test
    void removeCourse() throws Exception {

        course = new Course(1, "test", "testtile");
        courses = new ArrayList<>();
        courses.add(course);
        student = new Student(1, "testf", "testl", "test1@gmail.com", courses);
        Mockito.when(studentService.findById(1)).thenReturn(student);
        Mockito.when(courseService.getStudentCourses(student.getStudentId())).thenReturn(courses);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/students/removeCourse").flashAttr("course", courseDTO).queryParam("studentId", "1")).andExpect(status().is(200)).andExpect(view().name("CourseRemoveForm"));


    }

}