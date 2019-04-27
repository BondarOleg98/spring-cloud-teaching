package com.olegb.client.Controller;

import com.olegb.client.Model.Student;
import com.olegb.client.Client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    private RestClient restClient;

    @GetMapping("rest/student-list")
    public List<Student> getAllStudents(){
        return restClient.getAllStudents();
    }
    @PostMapping("rest/student-create")
    public ResponseEntity<Object> createStudent(@Valid @RequestBody Student student){
        return restClient.createStudent(student);
    }
    @GetMapping("rest/student/{id}")
    public Student OneStudent(@PathVariable Integer id) {
        return restClient.OneStudent(id);
    }
    @PutMapping("rest/student/{id}")
    public ResponseEntity<Object> replaceStudent(@RequestBody Student newStudent, @PathVariable Integer id) {
        return restClient.replaceStudent(newStudent, id);
    }

    @DeleteMapping("rest/student/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable Integer id) {
        return restClient.deleteStudent(id);
    }

    @GetMapping("rest/student/message")
    public String getStudentMessage(){return restClient.getStudentMessage();}

}
