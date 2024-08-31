package com.jdbcjpa.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jdbcjpa.springboot.model.jdbc.Course;
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
		
		System.out.println(repository.findById(2l));
		System.out.println(repository.findById(3l));
		
		System.out.println(repository.findAll());
		//System.out.println(repository.count());
		
		//System.out.println(repository.findByAuthor("Spring Source"));
		//System.out.println(repository.findByAuthor(""));

		//System.out.println(repository.findByName("Learn Spring JPA"));
		//System.out.println(repository.findByName("Learn Spring Data!"));

	}

	
}
