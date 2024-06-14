package com.sabri.studentManagement.Repository;

import com.sabri.studentManagement.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

}
