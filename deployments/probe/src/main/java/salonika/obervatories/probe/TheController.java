package salonika.obervatories.probe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.alibaba.nacos.registry.NacosRegistration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import salonika.obervatories.core.Beam;
import salonika.obervatories.core.JSONs;

import java.time.Instant;

/**
 * @Author: penghaoyang
 * @Date: 2019/6/26 23:17
 * @Description: TheController
 */
@RestController
public class TheController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NacosRegistration serviceInstance;

    @GetMapping("enter")
    public Beam enter(@RequestParam String id) {
        Beam beam = new Beam();
        beam.setId(id);
        beam = proc(beam);
        return beam;
    }

    @PostMapping("disposal/v1")
    public Beam proc(@RequestBody Beam beam) {
        beam.getMess().getVisitor().add(JSONs.gen(json -> {
            json.put("name", "probe");
            json.put("uri", serviceInstance.getUri());
            json.put("start", Instant.now().toString());
        }));

        Beam result = restTemplate.postForObject("http://pylon/reinforcement/v1", beam, Beam.class);
        return result;
    }

}
