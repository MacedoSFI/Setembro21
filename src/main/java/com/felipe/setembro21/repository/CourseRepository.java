package com.felipe.setembro21.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.felipe.setembro21.model.Course;

@Repository
@Transactional
public interface CourseRepository extends CrudRepository<Course, Long> {

}
