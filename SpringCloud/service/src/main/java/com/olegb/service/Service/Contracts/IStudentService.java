package com.olegb.service.Service.Contracts;
import com.olegb.service.Model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IStudentService {
    List<Student> getAllStudents();
    ResponseEntity<Object> createStudent(Student student);
    Student OneStudent(Integer id);
    ResponseEntity<Object> replaceStudent(Student newStudent, Integer id);
    Map<String, Boolean> deleteStudent(Integer id);
}
