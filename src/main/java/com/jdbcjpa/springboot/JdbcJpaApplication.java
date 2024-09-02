package com.jdbcjpa.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.EmptyResultDataAccessException;

import com.jdbcjpa.springboot.model.Course;
import com.jdbcjpa.springboot.repository.jdbc.CourseJdbcRepository;

@SpringBootApplication
public class JdbcJpaApplication implements CommandLineRunner{

	@Autowired
	private CourseJdbcRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(JdbcJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.insert(new Course(1, "Learn Spring JDBC", "Spring Source"));
		repository.insert(new Course(2, "Learn Spring JPA", "Spring Source"));
		repository.insert(new Course(3, "Learn Spring Data", "Spring Source"));
		
		System.out.println("Find Course with id = 2:");
		System.out.println(repository.findById(2l));
		
		System.out.println("Find Course with id = 3:");
		System.out.println(repository.findById(3l));
		
		System.out.println("Find all Courses:");
		System.out.println(repository.findAll());
		
		var course = repository.findById(3l);
		var updateCourse = new Course(course.id(), course.name(), "Learning Spring Data Updated");
		repository.update(updateCourse);
		
		System.out.println("Update Course with id = 3:");
		System.out.println(repository.findById(3l));
		
		System.out.println("Delete Course with id = 3:");
		repository.delete(3l);
		try {
			System.out.println("Find Course with id = 3 after deleting it:");
			System.out.println(repository.findById(3l));		
		} catch(EmptyResultDataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	
}
