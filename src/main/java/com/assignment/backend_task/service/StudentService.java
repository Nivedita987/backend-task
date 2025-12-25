package com.assignment.backend_task.service;

import com.assignment.backend_task.entity.Student;
import java.util.List;

public interface StudentService {

    Student addStudent(Student student);

    List<Student> getAllStudents();

    List<Student> getStudentsByCourse(Long courseId);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);
}
