package com.spring.app.controller;

import com.spring.app.dto.CourseDTO;
import com.spring.app.entity.Course;
import com.spring.app.entity.Student;
import com.spring.app.exception.NotFoundException;
import com.spring.app.mapper.CourseMapper;
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
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseMapper courseMapper;

    private static final String COURSES_LIST = "redirect:/courses/list";

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("/list")
    public String getAllCourses(Model model){

        List<Course> courses = courseService.findAll();

        model.addAttribute("courses",courses);

        return "CoursesList";
    }

    @GetMapping("/courseForm")
    public String showFormForAdd(Model model) {

        Course course = new Course();

        model.addAttribute("course", courseMapper.convertToCourseDTO(course));

        return "CourseForm";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") @Valid CourseDTO course, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){

            return "CourseForm";
        }
        else {
        courseService.save(courseMapper.convertToCourse(course));}

        return COURSES_LIST;
    }


    @PostMapping("/update")
    public String updateCourse(@ModelAttribute("course") @Valid CourseDTO course, BindingResult bindingResult ){

       Course courseEntity =  courseMapper.convertToCourse(course);

        if (bindingResult.hasErrors()){

            return "CourseUpdateForm";
        }
        else {

            List<Student> students = studentService.getCourseStudents(course.getCourseId());
            courseEntity.setStudents(students);
            courseService.save(courseEntity);
        }
        //change
        return COURSES_LIST;
    }

    @GetMapping("/updateCourseForm")
    public String updateCourseForm(@RequestParam("courseId") int courseId, Model model){

        Course course = courseService.findById(courseId);

        model.addAttribute("course", courseMapper.convertToCourseDTO(course));

        return "CourseUpdateForm";
    }

    @GetMapping("/delete")
    public String deleteCourse(@RequestParam("courseId") int courseId) {

        courseService.deleteById(courseId);

        return COURSES_LIST;
    }

    @GetMapping("getStudents")
    public String getStudents(@RequestParam("courseId") int courseId, Model model) {

        Course course = courseService.findById(courseId);
        if(course == null)
        {
            throw  new NotFoundException("course not found");
        }

        List<Student> students = studentService.getCourseStudents(courseId);

        model.addAttribute("students", students);

        return "CourseStudents";
    }
}
