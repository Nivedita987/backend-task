package com.assignment.backend_task.repository;

// import com.assignment.backend_task.entity.Student;
import com.assignment.backend_task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByCourse_CourseId(Long courseId);
    List<User> findByNameContainingIgnoreCase(String name);

}
