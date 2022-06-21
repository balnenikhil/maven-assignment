package com.spring.app.service;

import com.spring.app.entity.Course;


import java.util.List;

public interface CourseService {

    public List<Course> findAll();

    public Course findById(int courseId);

    public void save(Course course);

    public void deleteById(int courseId);


    public List<Course> getStudentCourses(int studentId);
}
