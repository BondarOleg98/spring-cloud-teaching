package com.olegb.client.Controller;

import com.olegb.client.Model.Coach;
import com.olegb.client.Client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class CoachController {

    @Autowired
    private RestClient restClient;

    @GetMapping("rest/coach-list")
    public List<Coach> getAllCoach(){
        return restClient.getAllCoaches();
    }
    @PostMapping("rest/coach-create")
    public ResponseEntity<Object> createCoach(@Valid @RequestBody Coach coach){
        return restClient.createCoach(coach);
    }
    @GetMapping("rest/coach/{id}")
    public Coach OneCoach(@PathVariable Integer id) {
        return restClient.OneCoach(id);
    }
    @PutMapping("rest/coach/{id}")
    public ResponseEntity<Object> replaceCoach(@RequestBody Coach newCoach, @PathVariable Integer id) {
        return restClient.replaceCoach(newCoach, id);
    }

    @DeleteMapping("rest/coach/{id}")
    public Map<String, Boolean> deleteCoach(@PathVariable Integer id) {
        return restClient.deleteCoach(id);
    }

    @GetMapping("rest/coach/message")
    public String getCoachMessage(){return restClient.getCoachMessage();}
}
