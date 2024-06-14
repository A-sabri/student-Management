package com.sabri.studentManagement.Service;

import com.sabri.studentManagement.Model.Course;
import com.sabri.studentManagement.Model.Student;
import com.sabri.studentManagement.Repository.CourseRepository;
import com.sabri.studentManagement.Repository.StudentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepo;


    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }


    public Student getStudentById(String id) {
        return studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }


    public Student addStudent(Student student){
        System.out.println("student register");
        return studentRepo.save(student);
    }


    public Student updateStudent(String id, Student student) {
        student.setId(id);
        return studentRepo.save(student);
    }


    public void removeStudent(String id){
        studentRepo.deleteById(id);
    }
}



