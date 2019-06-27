package salonika.obervatories.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: penghaoyang
 * @Date: 2019/6/27 14:42
 * @Description: IServiceConfiguration
 */
@Configuration
public class IServiceConfiguration {

    @Autowired(required = false)
    private INFClient infClient;

    @Autowired
    private RestTemplate restTemplate;

    @FeignClient
    @RequestMapping
    public interface INFClient {

        @PostMapping("refraction/v1")
        Beam callPrisma(@RequestBody Beam beam);

        @PostMapping("disposal/v1")
        Beam callProbe(@RequestBody Beam beam);

        @PostMapping("reinforcement/v1")
        Beam callPylon(@RequestBody Beam beam);

        @PostMapping("checker/v1")
        Beam callSentry(@RequestBody Beam beam);

    }

    @Bean
    public IService feignClientService(){
        return new IService() {
            @Override
            public Beam callPrisma(Beam beam) {
                return infClient.callPrisma(beam);
            }

            @Override
            public Beam callProbe(Beam beam) {
                return infClient.callProbe(beam);
            }

            @Override
            public Beam callPylon(Beam beam) {
                return infClient.callPylon(beam);
            }

            @Override
            public Beam callSentry(Beam beam) {
                return infClient.callSentry(beam);
            }
        };
    }

//    @Bean
    public IService restClientService(){
        return new IService() {
            @Override
            public Beam callPrisma(Beam beam) {
                return restTemplate.postForObject("http://prisma/refraction/v1", beam, Beam.class);
            }

            @Override
            public Beam callProbe(Beam beam) {
                return restTemplate.postForObject("http://probe/refraction/v1", beam, Beam.class);
            }

            @Override
            public Beam callPylon(Beam beam) {
                return restTemplate.postForObject("http://pylon/refraction/v1", beam, Beam.class);
            }

            @Override
            public Beam callSentry(Beam beam) {
                return restTemplate.postForObject("http://sentry/refraction/v1", beam, Beam.class);
            }
        };
    }

}
