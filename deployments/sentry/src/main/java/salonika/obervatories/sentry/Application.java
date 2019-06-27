package salonika.obervatories.sentry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: penghaoyang
 * @Date: 2019/6/26 23:10
 * @Description: Application
 */
@SpringBootApplication(scanBasePackages = "salonika.obervatories")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "salonika.obervatories")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
