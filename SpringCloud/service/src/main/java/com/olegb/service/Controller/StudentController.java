package com.olegb.service.Controller;

import com.olegb.service.Model.Student;
import com.olegb.service.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {


    @Autowired
    private StudentService studentService;

    @GetMapping("student/list")
    public List<Student> getAllStudents(){

        return studentService.getAllStudents();
    }

    @PostMapping("student/create")
    public ResponseEntity<Object> createStudent(@Valid @RequestBody Student student){

        return studentService.createStudent(student);

    }
    @GetMapping("student/{id}")
    public Student OneStudent(@PathVariable Integer id) {

        return studentService.OneStudent(id);
    }
    @PutMapping("student/{id}")
    public ResponseEntity<Object> replaceStudent(@RequestBody Student newStudent, @PathVariable Integer id) {

        return studentService.replaceStudent(newStudent, id);
    }

    @DeleteMapping("student/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable Integer id) {
       
        return studentService.deleteStudent(id);
    }


}
