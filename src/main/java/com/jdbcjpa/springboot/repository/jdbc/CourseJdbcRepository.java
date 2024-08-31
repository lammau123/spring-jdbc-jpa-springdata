package com.jdbcjpa.springboot.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jdbcjpa.springboot.model.jdbc.Course;

@Repository
public class CourseJdbcRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	final static String FIND_ALL_COURSES = "SELECT * FROM course";
	final static String FIND_BY_ID = "SELECT * FROM course WHERE id = ?";
	final static String INSERT = "INSERT INTO course(id, name, author) VALUES(?, ?, ?)";
	final static String DELETE = "DELETE FROM course WHERE id = ?";
			
	public List<Course> findAll(){
		return jdbcTemplate.queryForStream(FIND_ALL_COURSES, new DataClassRowMapper<>(Course.class)).toList();
	}
	
	public Course findById(long id) {
		return jdbcTemplate.queryForObject(FIND_BY_ID, new DataClassRowMapper<>(Course.class), id);
	}
	
	public void insert(final Course course) {
		jdbcTemplate.update(INSERT, course.id(), course.name(), course.author());
	}
	
	public void delete(long id) {
		jdbcTemplate.update(DELETE, id);
	}
}
