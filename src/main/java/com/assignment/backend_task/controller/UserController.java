package com.assignment.backend_task.controller;

import com.assignment.backend_task.entity.Course;
// import com.assignment.backend_task.entity.Student;
import com.assignment.backend_task.entity.User;
// import com.assignment.backend_task.exception.StudentDeletionException;
import com.assignment.backend_task.repository.CourseRepository;
import com.assignment.backend_task.repository.UserRepository;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class UserController {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public UserController(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    // Add student with course
    @PostMapping
    public User addStudent(@RequestBody User user) {
        long courseId = user.getCourse().getCourseId();
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        user.setCourse(course);
        return userRepository.save(user);
    }

    // Get all students with course
    @GetMapping
    public List<User> getAllStudents() {
        return userRepository.findAll();
    }

    // Get students by course
    @GetMapping("/course/{courseId}")
    public List<User> getStudentsByCourse(@PathVariable long courseId) {
        return userRepository.findByCourse_CourseId(courseId);

    }

    // Update student + change course
    @PutMapping("/{id}")
        public User updateStudent(
                @PathVariable long id,
                @RequestBody User updatedUser) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());

        
        if (updatedUser.getCourse() == null) {
            existingUser.setCourse(null);
        } else {
            long courseId = updatedUser.getCourse().getCourseId();
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            existingUser.setCourse(course);
        }

        return userRepository.save(existingUser);
    }


    // Delete student
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteStudent(@PathVariable long id) {

    //     User student = userRepository.findById(id)
    //             .orElseThrow(() ->
    //                     new StudentDeletionException("Student not found with id: " + id)
    //             );

    //     // Restrict delete if enrolled in course
    //     if (student.getCourse() != null) {
    //         throw new StudentDeletionException(
    //                 "Cannot delete student: student is enrolled in a course"
    //         );
    //     }

    //     userRepository.delete(student);
    //     return ResponseEntity.noContent().build(); 
    // }
    @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
    userRepository.deleteById(id);
    return ResponseEntity.noContent().build();
}

    @GetMapping("/search")
    public List<User> searchByName(@RequestParam String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

//     @DeleteMapping("/api/students/force/{id}")
//     @PreAuthorize("hasRole('ADMIN')")
//     public void deleteStudentfr(@PathVariable long id) {
//         userRepository.deleteById(id);
// }

}




