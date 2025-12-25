package com.assignment.backend_task.repository;

import com.assignment.backend_task.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
