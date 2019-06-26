package salonika.obervatories.prisma;

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

    @PostMapping("refraction/v1")
    public Beam proc(@RequestBody Beam beam){
        beam.getVisitors().add("prisma");

        Beam result;
        Integer i = beam.getMess().getInteger("__indicators__");
        Integer max = beam.getMess().getInteger("__max_indicators__");
        if(max == null){
            max = 3;
            beam.getMess().put("__max_indicators__", max);
        }
        if(i == null){
            i = max;
            beam.getMess().put("__indicators__", i);
        }
        if(i > 0) {
            i--;
            beam.getMess().put("__indicators__", i);
            result = restTemplate.postForObject("http://prisma/refraction/v1", beam, Beam.class);
        } else {
            i = max;
            beam.getMess().put("__indicators__", i);
            result = restTemplate.postForObject("http://sentry/checker/v1", beam, Beam.class);
        }
        return result;
    }

}
