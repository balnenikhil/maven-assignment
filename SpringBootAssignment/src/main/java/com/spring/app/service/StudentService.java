package com.spring.app.service;



import com.spring.app.entity.Student;



import java.util.List;

public interface StudentService {

    public List<Student> findAll();

    public Student findById(int studentId);

    public void save(Student student);

    public void deleteById(int studentId);

    public List<Student> getCourseStudents(int courseId);

}
