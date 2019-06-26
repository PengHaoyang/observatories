package salonika.obervatories.pylon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import salonika.obervatories.core.Beam;

/**
 * @Author: penghaoyang
 * @Date: 2019/6/26 23:17
 * @Description: TheController
 */
@RestController
public class TheController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("enter")
    public Beam enter(@RequestParam String id){
        Beam beam = new Beam();
        beam.setId(id);
        beam = proc(beam);
        return beam;
    }

    @PostMapping("reinforcement/v1")
    public Beam proc(@RequestBody Beam beam){
        beam.getVisitors().add("pylon");
        return beam;
    }

}
