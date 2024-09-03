package com.jdbcjpa.springboot.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jdbcjpa.springboot.entity.CourseEntity;
import com.jdbcjpa.springboot.model.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CourseJpaRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Course> findAll(){
		return entityManager.createQuery("SELECT c FROM CourseEntity c", CourseEntity.class)
				.getResultStream().map(e -> new Course(e.getId(), e.getName(), e.getAuthor()))
				.toList();
	}
	
	public Course findById(long id) {
		var entity = _findById(id);
		return new Course(entity.getId(), entity.getName(), entity.getAuthor());
	}
	
	public void insert(final Course course) {
		entityManager.merge(new CourseEntity(course.id(), course.name(), course.author()));
	}
	
	public void delete(long id) {
		var entity = _findById(id);
		entityManager.remove(entity);
	}
	
	public void update(final Course course) {
		var entity = _findById(course.id());
		entity.setName(course.name());
		entity.setAuthor(course.author());
		entityManager.merge(entity);
	}
	
	private CourseEntity _findById(long id) {
		var entity = entityManager.find(CourseEntity.class, id);
		if(entity == null) {
			throw new IllegalArgumentException("Course not found: " + id);
		}
		return entity;
	}
	
	public void deleteAll() {
		entityManager.createNativeQuery("DELETE FROM Course").executeUpdate();
	}
}
