package com.olegb.client.Client;

import com.olegb.client.Model.Coach;
import com.olegb.client.Model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("service")
public interface RestClient {

    @GetMapping("coach/list")
    List<Coach> getAllCoaches();

    @PostMapping("coach/create")
    ResponseEntity<Object> createCoach(@RequestBody Coach coach);

    @GetMapping("coach/{id}")
    Coach OneCoach(@PathVariable Integer id);

    @PutMapping("coach/{id}")
    ResponseEntity<Object> replaceCoach(@RequestBody Coach newCoach, @PathVariable Integer id);

    @DeleteMapping("coach/{id}")
    Map<String, Boolean> deleteCoach(@PathVariable Integer id);

    @GetMapping("student/list")
    List<Student> getAllStudents();

    @PostMapping("student/create")
    ResponseEntity<Object> createStudent(@RequestBody Student student);

    @GetMapping("student/{id}")
    Student OneStudent(@PathVariable Integer id);

    @PutMapping("student/{id}")
    ResponseEntity<Object> replaceStudent(@RequestBody Student newStudent, @PathVariable Integer id);

    @DeleteMapping("student/{id}")
    Map<String, Boolean> deleteStudent(@PathVariable Integer id);

    @GetMapping("student/message")
    String getStudentMessage();

    @GetMapping("coach/message")
    String getCoachMessage();
}
