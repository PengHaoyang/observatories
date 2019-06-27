package salonika.obervatories.sentry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
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

    /**
     * 也是 ServiceInstance
     */
    @Autowired
    private NacosRegistration serviceInstance;

    @GetMapping("enter")
    public String enter(@RequestParam String id) {
        Beam beam = new Beam();
        beam.setId(id);
        beam = proc(beam);
        return JSON.toJSONString(beam, SerializerFeature.PrettyFormat);
    }

    @PostMapping("checker/v1")
    public Beam proc(@RequestBody Beam beam) {
        beam.getMess().getVisitor().add(JSONs.gen(json -> {
            json.put("name", "sentry");
            json.put("uri", serviceInstance.getUri());
            json.put("start", Instant.now().toString());
        }));

        Beam result = null;
        int c = (int) (1 + Math.random() * (4));

        if (c == 1) {
            try {
                result = restTemplate.postForObject("http://probe/disposal/v1", beam, Beam.class);
            } catch (Exception e) {
                beam.getErrStack().add("probe err");
                e.printStackTrace();
            }
        } else if (c == 2) {
            try {
                result = restTemplate.postForObject("http://pylon/reinforcement/v1", beam, Beam.class);
            } catch (Exception e) {
                beam.getErrStack().add("pylon err");
                e.printStackTrace();
            }
        } else {
            try {
                result = restTemplate.postForObject("http://prisma/refraction/v1", beam, Beam.class);
            } catch (Exception e) {
                beam.getErrStack().add("prisma err");
                e.printStackTrace();
            }
        }

        if (result == null) {
            result = beam;
        }

        return result;
    }

}
