package salonika.obervatories.core;

import org.springframework.beans.factory.annotation.Autowired;
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
    private SentryClient sentryClient;

    @Autowired(required = false)
    private ProbeClient probeClient;

    @Autowired(required = false)
    private PylonClient pylonClient;

    @Autowired(required = false)
    private PrismaClient prismaClient;

    @Autowired
    private RestTemplate restTemplate;

    @FeignClient("sentry")
    public interface SentryClient {
        @PostMapping("checker/v1")
        Beam callSentry(@RequestBody Beam beam);
    }
    @FeignClient("probe")
    public interface ProbeClient {
        @PostMapping("disposal/v1")
        Beam callProbe(@RequestBody Beam beam);
    }
    @FeignClient("pylon")
    public interface PylonClient {
        @PostMapping("reinforcement/v1")
        Beam callPylon(@RequestBody Beam beam);
    }
    @FeignClient("prisma")
    public interface PrismaClient {
        @PostMapping("refraction/v1")
        Beam callPrisma(@RequestBody Beam beam);
    }

    @Bean
    public IService feignClientService(){
        return new IService() {
            @Override
            public Beam callPrisma(Beam beam) {
                return prismaClient.callPrisma(beam);
            }

            @Override
            public Beam callProbe(Beam beam) {
                return probeClient.callProbe(beam);
            }

            @Override
            public Beam callPylon(Beam beam) {
                return pylonClient.callPylon(beam);
            }

            @Override
            public Beam callSentry(Beam beam) {
                return sentryClient.callSentry(beam);
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
