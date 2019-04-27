package com.olegb.service.Service;

import com.olegb.service.Controller.NotFoundException;
import com.olegb.service.Model.Coach;
import com.olegb.service.Model.Student;
import com.olegb.service.Repository.CoachRepository;
import com.olegb.service.Service.Contracts.ICoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoachService implements ICoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public List<Coach> getAllCoaches(){
        List<Coach> Coaches = coachRepository.findAll();
        for (Coach coach:Coaches) {
            if(coach.getFlag()==1){
                Coaches.remove(coach);
            }
            for (Student student:coach.getStudents()) {
                if(student.getFlag()==1){
                    (coach.getStudents()).remove(student);
                }
            }
        }
        return Coaches;
    }

    @Override
    public ResponseEntity<Object> createCoach(Coach coach){
        if(coach.getName()==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: name is not valid");
        } else if(coach.getSurname() ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: surname is not valid");
        } else if(coach.getBelt() ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: belt is not valid");
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(coachRepository.save(coach));
        }
    }

    @Override
    public Coach OneCoach(Integer id) {
        coachRepository.findById(id).map(coach -> {
            if (coach.getFlag() == 1) {
                return new NotFoundException("coach", id);
            }
            return coachRepository.findById(id);
        });

        return  coachRepository.findById(id).orElseThrow(() -> new NotFoundException("coach", id));
    }

    @Override
    public ResponseEntity<Object> replaceCoach(Coach newCoach, Integer id) {
        if(newCoach.getName()==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: name is not valid");
        }
        else if(newCoach.getSurname() ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: surname is not valid");
        }
        else if(newCoach.getBelt() ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: belt is not valid");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(coachRepository.findById(id)
                    .map(coach -> {
                        if(coach.getFlag()==0){
                            coach.setName(newCoach.getName());
                            coach.setSurname(newCoach.getSurname());
                            coach.setBelt(newCoach.getBelt());
                            return coachRepository.save(coach);
                        }
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404: coach not found");
                    })
                    .orElseGet(() -> {
                        newCoach.setId(id);
                        return coachRepository.save(newCoach);
                    }));
        }

    }

    @Override
    public Map<String, Boolean> deleteCoach(Integer id) {
        Map<String, Boolean>response = new HashMap<>();
        if(!coachRepository.findById(id).isPresent()){
            response.put("Error 404: coach not found",Boolean.FALSE);
            return response;
        } else if(coachRepository.findById(id).isPresent()){
            coachRepository.findById(id).map(coach-> {
                if (coach.getFlag() == 0){
                    coach.setFlag(1);
                    coachRepository.save(coach);
                    response.put("Delete",Boolean.TRUE);
                } else {
                    response.put("Error 404: coach not found", Boolean.FALSE);

                }
                return response;
            });
        }
        return response;
    }
}
