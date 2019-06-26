package salonika.obervatories.sentry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
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

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("enter")
    public Beam enter(@RequestParam String id){
        Beam beam = new Beam();
        beam.setId(id);
        beam = proc(beam);
        return beam;
    }

    @PostMapping("checker/v1")
    public Beam proc(@RequestBody Beam beam){
        beam.getVisitors().add("sentry");

        Beam result = null;
        int c = (int)(1+Math.random()*(4));

        if(c == 1){
            try {
                result = restTemplate.postForObject("http://probe/disposal/v1", beam, Beam.class);
            } catch (Exception e) {
                beam.getVisitors().add("probe err");
            }
        } else if(c == 2){
            try {
                result = restTemplate.postForObject("http://pylon/reinforcement/v1", beam, Beam.class);
            } catch (Exception e) {
                beam.getVisitors().add("pylon err");
            }
        } else /* if(beam.getVisitors().stream().filter(v -> StringUtils.contains(v, "prisma")).count() < 21) */ {
            try {
                result = restTemplate.postForObject("http://prisma/refraction/v1", beam, Beam.class);
            } catch (Exception e) {
                beam.getVisitors().add("prisma err");
            }
        }

        if(result == null){
            result = beam;
        }

        return result;
    }

}
