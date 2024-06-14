package com.sabri.studentManagement.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "courses")
public class Course {

    @Id
    private String id;
    private String name;
    private String description;
    private Long nbOfStudent;

    @Field("studentIds")
    private List<String> studentIds = new ArrayList<>();

    // Constructor

    public Course(String id, String name, String description, Long nbOfStudent, List<String> studentIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nbOfStudent = nbOfStudent;
        this.studentIds = studentIds;
    }

    // getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<String> studentIds) {
        this.studentIds = studentIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNbOfStudent() {
        return nbOfStudent;
    }

    public void setNbOfStudent(Long nbOfStudent) {
        this.nbOfStudent = nbOfStudent;
    }
}

