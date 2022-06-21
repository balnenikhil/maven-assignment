package com.spring.app.repository;

import com.spring.app.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(value = "select c.course_id, c.course_name, c.course_title from course c, student_course sc, student s where c.course_id=sc.course_id and sc.student_id=s.student_id and s.student_id=:studentId", nativeQuery = true)
    List<Course> getStudentCourses(@Param("studentId") int studentId);
}

