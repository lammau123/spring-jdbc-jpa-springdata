package com.jdbcjpa.springboot.repository;

import org.springframework.data.repository.ListCrudRepository;
import com.jdbcjpa.springboot.entity.*;

public interface CourseSpringDataRepository extends ListCrudRepository<CourseEntity, Long>{

}
