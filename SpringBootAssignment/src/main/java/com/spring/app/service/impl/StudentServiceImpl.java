package com.spring.app.service.impl;


import com.spring.app.entity.Student;
import com.spring.app.exception.NotFoundException;
import com.spring.app.repository.StudentRepository;
import com.spring.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    @Transactional
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public Student findById(int studentId) {
        Optional<Student> result = studentRepository.findById(studentId);

        if(result.isPresent()){
            return result.get();
        }
        else {
            throw  new NotFoundException("Customer id not found - "+studentId);
        }
    }

    @Override
    @Transactional
    public void save(Student student) {

        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteById(int studentId) {

        studentRepository.deleteById(studentId);
    }

    @Override
    @Transactional
    public List<Student> getCourseStudents(int courseId) {
        return studentRepository.getCourseStudents(courseId);
    }

}
