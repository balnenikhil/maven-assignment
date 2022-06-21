package com.spring.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        String admin = "ADMIN";
        String user = "USER";
        http.authorizeRequests().
                antMatchers("/").hasAnyRole(admin, user).
                antMatchers("/students/list").hasAnyRole(admin, user).
                antMatchers("/students/getCourses").hasAnyRole(admin, user).
                antMatchers("/students/studentForm").hasRole(admin).
                antMatchers("/students/save").hasRole(admin).
                antMatchers("/students/update").hasRole(admin).
                antMatchers("/students/updateStudentForm").hasRole(admin).
                antMatchers("/students/delete").hasRole(admin).
                antMatchers("/students/addCourse").hasRole(admin).
                antMatchers("/students/saveCourse").hasRole(admin).
                antMatchers("/students/removeCourse").hasRole(admin).
                antMatchers("/students/deleteCourse").hasRole(admin).
                antMatchers("/courses/list").hasAnyRole(admin, user).
                antMatchers("/courses/getStudents").hasAnyRole(admin, user).
                antMatchers("/courses/courseForm").hasRole(admin).
                antMatchers("/courses/save").hasRole(admin).
                antMatchers("/courses/update").hasRole(admin).
                antMatchers("/courses/updateCourseForm").hasRole(admin).
                antMatchers("/courses/delete").hasRole(admin).
                and().formLogin().
                loginPage("/customLoginPage").loginProcessingUrl("/authenticateTheUser").
                permitAll().and().logout().permitAll().
                and().exceptionHandling().accessDeniedPage("/accessDeniedPage");
    }
}
