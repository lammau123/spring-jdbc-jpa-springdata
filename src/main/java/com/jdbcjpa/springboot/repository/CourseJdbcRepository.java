package com.jdbcjpa.springboot.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jdbcjpa.springboot.entity.CourseEntity;
import com.jdbcjpa.springboot.model.Course;

@Repository
public class CourseJdbcRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	final static String FIND_ALL_COURSES = "SELECT * FROM course";
	final static String FIND_BY_ID = "SELECT * FROM course WHERE id = ?";
	final static String INSERT = "INSERT INTO course(id, name, author) VALUES(?, ?, ?)";
	final static String DELETE = "DELETE FROM course WHERE id = ?";
	final static String UPDATE = "UPDATE Course SET name = ?, author = ? WHERE id = ?";
	final static String DELETE_ALL = "DELETE FROM Course";
			
	public List<Course> findAll(){
		return jdbcTemplate.queryForStream(FIND_ALL_COURSES, new BeanPropertyRowMapper<>(CourseEntity.class))
				.map(e -> new Course(e.getId(), e.getName(), e.getAuthor())).toList();
	}
	
	public Course findById(long id) {
		var entity = jdbcTemplate.queryForObject(FIND_BY_ID, new BeanPropertyRowMapper<>(CourseEntity.class), id);
		return new Course(entity.getId(), entity.getName(), entity.getAuthor());
	}
	
	public void insert(final Course course) {
		jdbcTemplate.update(INSERT, course.id(), course.name(), course.author());
	}
	
	public void delete(long id) {
		jdbcTemplate.update(DELETE, id);
	}
	
	public void update(final Course course) {
		var entity = findById(course.id());
		jdbcTemplate.update(UPDATE, course.name(), course.author(), entity.id());
	}
	
	public void deleteAll() {
		jdbcTemplate.execute(DELETE_ALL);
	}
}
