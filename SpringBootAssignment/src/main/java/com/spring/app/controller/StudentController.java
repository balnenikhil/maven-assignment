package com.spring.app.controller;



import com.spring.app.dto.CourseDTO;
import com.spring.app.dto.StudentDTO;
import com.spring.app.entity.Course;
import com.spring.app.entity.Student;
import com.spring.app.exception.NotFoundException;
import com.spring.app.mapper.CourseMapper;
import com.spring.app.mapper.StudentMapper;
import com.spring.app.service.CourseService;
import com.spring.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    private static final String STUDENTS_LIST = "redirect:/students/list";

    private static final String  STUDENT = "student";

    private static final String COURSES =  "courses";

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("/list")
    public String getAllStudents(Model model){

        List<Student> students = studentService.findAll();

        model.addAttribute("students",students);

        return "StudentsList";
    }

    @GetMapping("/studentForm")
    public String showFormForAdd(Model model) {

        Student student = new Student();

        model.addAttribute(STUDENT, studentMapper.convertToStudentDTO(student));

        return "StudentForm";

    }


    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") @Valid StudentDTO student, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return "StudentForm";
        }
        else {
        studentService.save(studentMapper.convertToStudent(student));}

        return STUDENTS_LIST;
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") @Valid StudentDTO student, BindingResult bindingResult){

        Student studentEntity = studentMapper.convertToStudent(student);

        if(bindingResult.hasErrors()){
            return "StudentUpdateForm";
        }
        else{
        List<Course> courses = courseService.getStudentCourses(student.getStudentId());
        studentEntity.setCourses(courses);
        studentService.save(studentEntity);
        return STUDENTS_LIST;}
    }

    @GetMapping("/updateStudentForm")
    public String updateStudentForm(@RequestParam("studentId") int studentId, Model model){

        Student student = studentService.findById(studentId);

        model.addAttribute(STUDENT, student);

        return "StudentUpdateForm";
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("studentId") int studentId) {

        studentService.deleteById(studentId);

        return STUDENTS_LIST;
    }

    @GetMapping("/addCourse")
    public String addCourseToStudent(@RequestParam("studentId") int studentId, @ModelAttribute("course") CourseDTO course, Model model){

        Student student = studentService.findById(studentId);

        model.addAttribute(STUDENT, student);

        List<Course> courses = courseService.findAll();

        model.addAttribute(COURSES, courses);


       return "StudentCourseForm";
    }

    @PostMapping("/saveCourse")
    public String saveCourseToStudent(@ModelAttribute("course")  CourseDTO course,  @RequestParam("studentId") int studentId){


        Course courseEntity = courseMapper.convertToCourse(course);
        Student student = studentService.findById(studentId);
        Course course1 = courseService.findById(courseEntity.getCourseId());
        if(student.getCourses() == null){
            student.addCourse(course1);
            studentService.save(student);
        }
        else if(student.getCourses() != null){

            for (Course tempCourse : student.getCourses()) {
                if (tempCourse.equals(course1)) {

                    return "CourseAlreadyExists";
                }
            }
                student.addCourse(course1);
                studentService.save(student);
        }


        return STUDENTS_LIST;
    }

    @GetMapping("/getCourses")
    public String getCourses(@RequestParam("studentId") int studentId, Model model) {

        Student student = studentService.findById(studentId);
        if(student == null){

            throw new NotFoundException("student not found");
        }

        List<Course> courses = courseService.getStudentCourses(studentId);

        model.addAttribute(COURSES, courses);

        return "StudentCourses";
    }

    @GetMapping("/removeCourse")
    public String removeCourse(@RequestParam("studentId") int studentId, Model model, @ModelAttribute("course") CourseDTO course){

        Student student = studentService.findById(studentId);

        model.addAttribute(STUDENT, student);

        List<Course> courses = courseService.getStudentCourses(studentId);

        model.addAttribute(COURSES, courses);

        return "CourseRemoveForm";
    }

    @PostMapping("/deleteCourse")
    public String removeCourseToStudent(@ModelAttribute("course") CourseDTO course, @RequestParam("studentId") int studentId){

        Course courseEntity = courseMapper.convertToCourse(course);

        Student student = studentService.findById(studentId);

        List<Course> courses = courseService.getStudentCourses(studentId);

        Course course1 = courseService.findById(courseEntity.getCourseId());

        courses.remove(course1);

        student.setCourses(courses);

        studentService.save(student);

        return "redirect:/students/getCourses?studentId="+studentId;
    }


}
