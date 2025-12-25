package com.assignment.backend_task.repository;

import com.assignment.backend_task.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
