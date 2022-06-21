package com.spring.app.mapper;

import com.spring.app.dto.StudentDTO;
import com.spring.app.entity.Student;
import org.modelmapper.ModelMapper;

public class StudentMapper {

    ModelMapper modelMapper;

    public StudentMapper(ModelMapper modelMapper){
        this.modelMapper= modelMapper;
    }

    public StudentDTO convertToStudentDTO(Student student){
        return modelMapper.map(student, StudentDTO.class);
    }

    public Student convertToStudent(StudentDTO studentDTO){
        return modelMapper.map(studentDTO, Student.class);
    }
}