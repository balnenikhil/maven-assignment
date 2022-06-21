package com.spring.app.mapper;

import com.spring.app.dto.CourseDTO;
import com.spring.app.entity.Course;
import org.modelmapper.ModelMapper;


public class CourseMapper {

    ModelMapper modelMapper;

    public CourseMapper(ModelMapper modelMapper){
        this.modelMapper= modelMapper;
    }

    public CourseDTO convertToCourseDTO(Course course){
        return modelMapper.map(course, CourseDTO.class);
    }

    public Course convertToCourse(CourseDTO courseDTO){
        return modelMapper.map(courseDTO, Course.class);
    }

}
