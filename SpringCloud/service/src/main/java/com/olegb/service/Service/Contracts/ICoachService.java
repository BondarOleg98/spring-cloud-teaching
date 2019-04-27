package com.olegb.service.Service.Contracts;

import com.olegb.service.Model.Coach;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ICoachService {
    List<Coach> getAllCoaches();
    ResponseEntity<Object> createCoach(Coach coach);
    Coach OneCoach(Integer id);
    ResponseEntity<Object> replaceCoach(Coach newCoach, Integer id);
    Map<String, Boolean> deleteCoach(Integer id);
}
