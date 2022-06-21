package com.spring.app.service.impl;

import com.spring.app.entity.Course;
import com.spring.app.exception.NotFoundException;
import com.spring.app.repository.CourseRepository;
import com.spring.app.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;


    @Override
    @Transactional
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional
    public Course findById(int courseId) {
        Optional<Course> result = courseRepository.findById(courseId);

        if(result.isPresent()){
            return result.get();
        }
        else {
            throw  new NotFoundException("Course id not found - "+courseId);
        }
    }

    @Override
    @Transactional
    public void save(Course course) {

        courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteById(int courseId) {

        courseRepository.deleteById(courseId);
    }


    @Override
    public List<Course> getStudentCourses(int studentId) {
        return courseRepository.getStudentCourses(studentId);
    }
}
