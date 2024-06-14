package com.sabri.studentManagement.Service;

import com.sabri.studentManagement.Model.Course;
import com.sabri.studentManagement.Model.Student;
import com.sabri.studentManagement.Repository.CourseRepository;
import com.sabri.studentManagement.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class CourseService {


    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private StudentRepository studentRepo;



    public List<Course> getAllCourses(){
        return courseRepo.findAll();
    }

    public Course getCourseById(String id){
        return courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }


    public Course addCourse(Course course){
        return courseRepo.save(course);
    }


    public Course updateCourse(String id, Course course){
        course.setId(id);
        return courseRepo.save(course);
    }


    public void removeCourse(String id){
        courseRepo.deleteById(id);
    }


    public Course addStudentToCourse(String courseId, String studentId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));

        // Ajout de l'étudiant au cours
        course.getStudentIds().add(studentId);

        // Ajout du cours à l'étudiant
        student.getCourseIds().add(courseId);

        // Mettre à jour le nombre d'étudiants
        updateCourseStudentCount(course);

        // Sauvegarder les modifications
        studentRepo.save(student);
        return courseRepo.save(course);
    }


    public Course removeStudentFromCourse(String courseId, String studentId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));

        // Suppression de l'étudiant du cours
        course.getStudentIds().remove(studentId);

        // Suppression du cours de l'étudiant
        student.getCourseIds().remove(courseId);

        // Mettre à jour le nombre d'étudiants
        updateCourseStudentCount(course);

        // Sauvegarder les modifications
        studentRepo.save(student);
        return courseRepo.save(course);
    }

    private void updateCourseStudentCount(Course course) {
        course.setNbOfStudent((long) course.getStudentIds().size());
    }


}
