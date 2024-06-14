package com.sabri.studentManagement.Controller;

import com.sabri.studentManagement.Model.Student;
import com.sabri.studentManagement.Service.StudentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        System.out.println("student register");
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable String id, @RequestBody @NotNull Student student) {
        student.setId(id);
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void removeStudent(@PathVariable String id){
        studentService.removeStudent(id);
    }

}
