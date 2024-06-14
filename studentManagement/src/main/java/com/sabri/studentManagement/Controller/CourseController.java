package com.sabri.studentManagement.Controller;

import com.sabri.studentManagement.Model.Course;
import com.sabri.studentManagement.Model.Student;
import com.sabri.studentManagement.Service.CourseService;
import com.sabri.studentManagement.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;


    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable String id){
        return courseService.getCourseById(id);
    }

    @PostMapping
    public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable String id, @RequestBody Course course){
        course.setId(id);
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public void removeCourse(@PathVariable String id){
        courseService.removeCourse(id);
    }

    @PutMapping("/{courseId}/students/{studentId}")
    public Course addStudentToCourse(@PathVariable String courseId, @PathVariable String studentId) {
        Course course = courseService.getCourseById(courseId);
        Student student = studentService.getStudentById(studentId);


        // Ajout de l'étudiant au cours
        course.getStudentIds().add(studentId);

        // Ajout du cours à l'étudiant
        student.getCourseIds().add(courseId);

        // Mettre à jour le nombre d'étudiants
        updateCourseStudentCount(course);

        // Sauvegarder les modifications
        studentService.updateStudent(studentId, student);
        return courseService.updateCourse(courseId, course);
    }

    @DeleteMapping("/{courseId}/students/{studentId}")
    public Course removeStudentFromCourse(@PathVariable String courseId, @PathVariable String studentId) {
        Course course = courseService.getCourseById(courseId);
        Student student = studentService.getStudentById(studentId);

        // Suppression de l'étudiant du cours
        course.getStudentIds().remove(studentId);

        // Suppression du cours de l'étudiant
        student.getCourseIds().remove(courseId);

        // Mettre à jour le nombre d'étudiants
        updateCourseStudentCount(course);

        // Sauvegarder les modifications
        studentService.updateStudent(studentId, student);
        return courseService.updateCourse(courseId, course);
    }

    private void updateCourseStudentCount(Course course) {
        course.setNbOfStudent((long) course.getStudentIds().size());
    }
}

