package com.olegb.service.Service;

import com.olegb.service.Controller.NotFoundException;
import com.olegb.service.Model.Coach;
import com.olegb.service.Model.Log;
import com.olegb.service.Model.Student;
import com.olegb.service.Repository.StudentRepository;
import com.olegb.service.Service.Contracts.IStudentService;
import org.jboss.logging.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService implements IStudentService {
    Logger logger = Logger.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Override
    public List<Student> getAllStudents(){
        List<Student> Students = studentRepository.findAll();
        List<Student> newStudents = studentRepository.findAll();
        for (Student item:Students) {
            if(item.getFlag()==1){
                newStudents.remove(item);
            }
        }
        amqpTemplate.convertAndSend("GetStudent",new Log("Get"));
        return newStudents;
    }

    @Override
    public ResponseEntity<Object> createStudent(Student student){
        if(student.getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: name is not valid");
        } else if(student.getSurname() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: surname is not valid");
        } else if(student.getAge()<= 0 ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: age is not valid");
        } else if(student.getWeight()<= 0 ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: weight is not valid");
        } else {
            amqpTemplate.convertAndSend("CreateStudent",new Log("Create"));
            return ResponseEntity.status(HttpStatus.OK).body(studentRepository.save(student));
        }

    }

    @Override
    public Student OneStudent(Integer id) {
            studentRepository.findById(id).map(student -> {
                if (student.getFlag() == 1) {
                    return new NotFoundException("student", id);
                }
                return studentRepository.findById(id);
            });

        return  studentRepository.findById(id).orElseThrow(() -> new NotFoundException("student", id));
    }

    @Override
    public ResponseEntity<Object> replaceStudent(Student newStudent, Integer id) {
        if(newStudent.getName() == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: name is not valid");
        }
        else if(newStudent.getSurname() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: surname is not valid");
        }
        else if(newStudent.getAge()<= 0 ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: age is not valid");
        }
        else if(newStudent.getWeight()<= 0 ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: weight is not valid");
        }

        else{
            return ResponseEntity.status(HttpStatus.OK).body(studentRepository.findById(id)
                    .map(student -> {
                        if(student.getFlag()==0){
                            student.setName(newStudent.getName());
                            student.setSurname(newStudent.getSurname());
                            student.setAge(newStudent.getAge());
                            student.setWeight(newStudent.getWeight());
                            return studentRepository.save(student);
                        }
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404: student not found");
                    })
                    .orElseGet(() -> {
                        newStudent.setId(id);
                        return studentRepository.save(newStudent);
                    }));
        }
    }

    @Override
    public Map<String, Boolean> deleteStudent(Integer id) {
        Map<String, Boolean>response = new HashMap<>();
        Coach coach = new Coach();
        coach.setFlag(1);
        if(!studentRepository.findById(id).isPresent()){
            response.put("Error 404: student not found",Boolean.FALSE);
            return response;
        } else {
            studentRepository.findById(id).map(student -> {
                if(student.getFlag()==0){
                    student.setFlag(1);
                    studentRepository.save(student);
                    amqpTemplate.convertAndSend("RemoveStudent",new Log("Remove"));
                    return response.put("Delete",Boolean.TRUE);
                }else {
                    response.put("Error 404: student not found", Boolean.FALSE);
                }
                return response;
            });
        }
        return response;
    }
}
