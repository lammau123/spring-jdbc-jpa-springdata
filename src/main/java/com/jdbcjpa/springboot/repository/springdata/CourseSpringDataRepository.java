package com.jdbcjpa.springboot.repository.springdata;

import org.springframework.data.repository.ListCrudRepository;
import com.jdbcjpa.springboot.entity.*;

interface CourseSpringDataRepository extends ListCrudRepository<CourseEntity, Long>{

}
