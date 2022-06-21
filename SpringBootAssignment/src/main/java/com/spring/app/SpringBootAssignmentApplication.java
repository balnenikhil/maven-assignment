package com.spring.app;

import com.spring.app.mapper.CourseMapper;
import com.spring.app.mapper.StudentMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootAssignmentApplication {

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	@Bean
	public CourseMapper courseMapper(){

		return new CourseMapper(modelMapper());
	}

	@Bean
	public StudentMapper studentMapper(){

		return new StudentMapper(modelMapper());
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAssignmentApplication.class, args);
	}

}
