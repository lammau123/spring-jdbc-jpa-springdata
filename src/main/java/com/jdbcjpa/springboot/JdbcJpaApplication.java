package com.jdbcjpa.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jdbcjpa.springboot.entity.CourseEntity;
import com.jdbcjpa.springboot.model.Course;
import com.jdbcjpa.springboot.repository.*;

@SpringBootApplication
public class JdbcJpaApplication implements CommandLineRunner{

	@Autowired
	private CourseJdbcRepository jdbcRepository;
	
	@Autowired
	private CourseJpaRepository jpaRepository;
	
	@Autowired
	private CourseSpringDataRepository dataRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JdbcJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		runJDBC();
		runJpa();
		runSpringData();
	}

	private void runSpringData() {
		dataRepository.deleteAll();
		dataRepository.save(new CourseEntity(1, "Learn Spring JDBC", "Spring Source"));
		dataRepository.save(new CourseEntity(2, "Learn Spring JPA", "Spring Source"));
		dataRepository.save(new CourseEntity(3, "Learn Spring Data", "Spring Source"));
		
		System.out.println("Find Course with id = 2:");
		System.out.println(dataRepository.findById(2l));
		
		System.out.println("Find Course with id = 3:");
		System.out.println(dataRepository.findById(3l));
		
		System.out.println("Find all Courses:");
		System.out.println(dataRepository.findAll());
		
		var course = dataRepository.findById(3l).get();
		course.setName("Learning Spring Data Updated");
		dataRepository.save(course);
		
		System.out.println("Update Course with id = 3:");
		System.out.println(dataRepository.findById(3l));
		
		System.out.println("Delete Course with id = 3:");
		dataRepository.deleteById(3l);
		
		//Test Course does not exist in db
		System.out.println("Find Course with id = 3 after deleting it:");
		System.out.println(dataRepository.findById(3l));	
			
		var course1 = dataRepository.findById(3l);
		if(course1.isEmpty()) {
			System.out.println("Course with id = 3l not found.");
		}
	}
	
	private void runJpa() {
		jpaRepository.deleteAll();
		jpaRepository.insert(new Course(1, "Learn Spring JDBC", "Spring Source"));
		jpaRepository.insert(new Course(2, "Learn Spring JPA", "Spring Source"));
		jpaRepository.insert(new Course(3, "Learn Spring Data", "Spring Source"));
		
		System.out.println("Find Course with id = 2:");
		System.out.println(jpaRepository.findById(2l));
		
		System.out.println("Find Course with id = 3:");
		System.out.println(jpaRepository.findById(3l));
		
		System.out.println("Find all Courses:");
		System.out.println(jpaRepository.findAll());
		
		var course = jpaRepository.findById(3l);
		var updateCourse = new Course(course.id(), course.name(), "Learning Spring Data Updated");
		jpaRepository.update(updateCourse);
		
		System.out.println("Update Course with id = 3:");
		System.out.println(jpaRepository.findById(3l));
		
		System.out.println("Delete Course with id = 3:");
		jpaRepository.delete(3l);
		
		//Test Course does not exist in db
		try {
			System.out.println("Find Course with id = 3 after deleting it:");
			System.out.println(jpaRepository.findById(3l));	
			
			var course1 = jpaRepository.findById(3l);
			var updateCourse1 = new Course(0l, course1.name(), "Learning Spring Data Updated");
			jpaRepository.update(updateCourse1);
			System.out.println("Update Course with id = 0l successfully");
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void runJDBC() {
		jdbcRepository.deleteAll();
		jdbcRepository.insert(new Course(1, "Learn Spring JDBC", "Spring Source"));
		jdbcRepository.insert(new Course(2, "Learn Spring JPA", "Spring Source"));
		jdbcRepository.insert(new Course(3, "Learn Spring Data", "Spring Source"));
		
		System.out.println("Find Course with id = 2:");
		System.out.println(jdbcRepository.findById(2l));
		
		System.out.println("Find Course with id = 3:");
		System.out.println(jdbcRepository.findById(3l));
		
		System.out.println("Find all Courses:");
		System.out.println(jdbcRepository.findAll());
		
		var course = jdbcRepository.findById(3l);
		var updateCourse = new Course(course.id(), course.name(), "Learning Spring Data Updated");
		jdbcRepository.update(updateCourse);
		
		System.out.println("Update Course with id = 3:");
		System.out.println(jdbcRepository.findById(3l));
		
		System.out.println("Delete Course with id = 3:");
		jdbcRepository.delete(3l);
		try {
			System.out.println("Find Course with id = 3 after deleting it:");
			System.out.println(jdbcRepository.findById(3l));		
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
