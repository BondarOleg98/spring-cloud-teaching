package com.olegb.service.Controller;

import com.olegb.service.Model.Coach;
import com.olegb.service.Service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
public class CoachController {

    @Autowired
    private CoachService coachService;

    @GetMapping("coach/list")
    public List<Coach> getAllCoaches(){

        return coachService.getAllCoaches();
    }

    @PostMapping("coach/create")
    public ResponseEntity<Object> createCoach(@Valid @RequestBody Coach coach){

        return coachService.createCoach(coach);
    }

    @GetMapping("coach/{id}")
    public Coach OneCoach(@PathVariable Integer id) {

        return coachService.OneCoach(id);
    }
    @PutMapping("coach/{id}")
    public ResponseEntity<Object> replaceCoach(@RequestBody Coach newCoach, @PathVariable Integer id) {

        return coachService.replaceCoach(newCoach, id);
    }

    @DeleteMapping("coach/{id}")
    public Map<String, Boolean> deleteCoach(@PathVariable Integer id) {

        return coachService.deleteCoach(id);
    }

}
