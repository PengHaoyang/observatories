package salonika.observatories.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import salonika.observatories.core.bean.Beam;
import salonika.observatories.core.feign.PrismaFeignConfiguration;
import salonika.observatories.core.feign.ProbeFeignConfiguration;
import salonika.observatories.core.feign.PylonFeignConfiguration;
import salonika.observatories.core.feign.SentryFeignConfiguration;

/**
 * @Author: penghaoyang
 * @Date: 2019/6/27 14:42
 * @Description: IServiceConfiguration
 */
@Configuration
public class ServiceConfiguration {

    @Autowired(required = false)
    private SentryFeignConfiguration.ISentryClient sentryClient;

    @Autowired(required = false)
    private ProbeFeignConfiguration.IProbeClient probeClient;

    @Autowired(required = false)
    private PylonFeignConfiguration.IPylonClient pylonClient;

    @Autowired(required = false)
    private PrismaFeignConfiguration.IPrismaClient prismaClient;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public IService feignClientService() {
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
    public IService restClientService() {
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
