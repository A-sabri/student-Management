package com.sabri.studentManagement.Repository;

import com.sabri.studentManagement.Model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
}
