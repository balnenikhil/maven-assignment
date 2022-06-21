package com.spring.app.repository;





import com.spring.app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "select s.student_id, s.first_name, s.last_name, s.email from student s, course c, student_course sc where s.student_id=sc.student_id and sc.course_id=c.course_id and c.course_id=:courseId", nativeQuery = true)
    List<Student> getCourseStudents(@Param("courseId") int courseId);

}
